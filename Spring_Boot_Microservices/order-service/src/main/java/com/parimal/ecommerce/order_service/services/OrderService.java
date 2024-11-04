package com.parimal.ecommerce.order_service.services;


import com.parimal.ecommerce.order_service.advices.ApiResponse;
import com.parimal.ecommerce.order_service.clients.InventoryFeignClient;
import com.parimal.ecommerce.order_service.dtos.DataDTO;
import com.parimal.ecommerce.order_service.dtos.OrderRequestDTO;
import com.parimal.ecommerce.order_service.entities.OrderEntity;
import com.parimal.ecommerce.order_service.entities.OrderItemEntity;
import com.parimal.ecommerce.order_service.exceptions.ResourceNotFoundException;
import com.parimal.ecommerce.order_service.repositories.OrderRepository;
import com.parimal.ecommerce.order_service.utils.OrderStatus;
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

    @Transactional()
    public OrderRequestDTO createOrder(OrderRequestDTO orderRequestDTO) {
        try {
            ApiResponse<DataDTO> response = inventoryFeignClient.reduceStocks(orderRequestDTO);
            DataDTO<Double> dataDTO = modelMapper.map(response.getData(), DataDTO.class);
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

}
