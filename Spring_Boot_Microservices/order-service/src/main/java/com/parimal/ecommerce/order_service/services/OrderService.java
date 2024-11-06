package com.parimal.ecommerce.order_service.services;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.parimal.ecommerce.order_service.advices.ApiResponse;
import com.parimal.ecommerce.order_service.clients.InventoryFeignClient;
import com.parimal.ecommerce.order_service.dtos.DataDTO;
import com.parimal.ecommerce.order_service.dtos.OrderRequestDTO;
import com.parimal.ecommerce.order_service.entities.OrderEntity;
import com.parimal.ecommerce.order_service.entities.OrderItemEntity;
import com.parimal.ecommerce.order_service.exceptions.ResourceNotFoundException;
import com.parimal.ecommerce.order_service.repositories.OrderRepository;
import com.parimal.ecommerce.order_service.utils.OrderStatus;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;
    private final InventoryFeignClient inventoryFeignClient;

    public List<OrderRequestDTO> getAllOrders(){
        log.info("Fetching all orders");
        List<OrderEntity> orders = orderRepository.findAll();
        return orders.stream()
                .map(orderEntity -> modelMapper.map(orderEntity, OrderRequestDTO.class))
                .toList();
    }

    public OrderRequestDTO getOrderById(Long orderId){
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with orderId: " + orderId));
        return modelMapper.map(order, OrderRequestDTO.class);
    }

    // create the createOrderFallBack() method, when the @Retry's retry fails then this method is called.
    // also configure the further properties in the application.yml file
//    @Retry(name = "inventoryRetry", fallbackMethod = "createOrderFallBack")
//    RateLimiter - if you make more than specified/configured than the specified api calls within the certain time.
//    Hence, then the method below(createOrder()) won't be called and we will be redirected to the createOrderFallBack() method.
    @RateLimiter(name = "inventoryRateLimiter", fallbackMethod = " createOrderFallBack")
    @CircuitBreaker(name = "inventoryCircuitBreaker", fallbackMethod = "createOrderFallBack")
    @Transactional()
    public OrderRequestDTO createOrder(OrderRequestDTO orderRequestDTO) {
        try {
            log.info("Calling the reduceStocks from inventoryFeignClient");
            ApiResponse response = inventoryFeignClient.reduceStocks(orderRequestDTO);
            DataDTO<Double> dataDTO = objectMapper.convertValue(response.getData(), DataDTO.class);
            OrderEntity order = modelMapper.map(orderRequestDTO, OrderEntity.class);
            Double totalPrice = dataDTO.getData();
            for(OrderItemEntity item : order.getOrderItems()){
                item.setOrder(order);
            }
            order.setTotalPrice(totalPrice);
            order.setOrderStatus(OrderStatus.ORDERED);
            OrderEntity saved = orderRepository.save(order);
            return modelMapper.map(saved, OrderRequestDTO.class);
        } catch (Exception e){
            log.error("Create order error {}", e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    public OrderRequestDTO createOrderFallBack(OrderRequestDTO orderRequestDTO, Throwable throwable){
        log.error("Fallback occurred due to: {}", throwable.getMessage());
        return new OrderRequestDTO();
    }

}
