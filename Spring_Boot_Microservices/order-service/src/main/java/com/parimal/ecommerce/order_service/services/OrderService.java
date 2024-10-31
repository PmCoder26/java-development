package com.parimal.ecommerce.order_service.services;


import com.parimal.ecommerce.order_service.dtos.OrderRequestDTO;
import com.parimal.ecommerce.order_service.entities.OrderEntity;
import com.parimal.ecommerce.order_service.exceptions.ResourceNotFoundException;
import com.parimal.ecommerce.order_service.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

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

}
