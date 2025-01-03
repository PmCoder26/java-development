package com.parimal.ecommerce.order_service.controllers;


import com.parimal.ecommerce.order_service.dtos.MessageDTO;
import com.parimal.ecommerce.order_service.dtos.OrderRequestDTO;
import com.parimal.ecommerce.order_service.services.OrderService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/core")
@RefreshScope
public class OrderController {

    private final OrderService orderService;

    @Value(value = "${my.variable}")
    private String myVariable;


    @GetMapping(path = "/getAllOrders")
    public List<OrderRequestDTO> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping(path = "/getOrderById/{orderId}")
    public OrderRequestDTO getOrderById(
            @PathVariable
            Long orderId
    ){
        return orderService.getOrderById(orderId);
    }

    @GetMapping(path = "/helloOrders")
    public MessageDTO helloOrders(
            @RequestHeader(value = "X-User-Id")
            Long userId
    ){
        return new MessageDTO("Hello from Order-Service with user id: " + userId);
    }

    @GetMapping(path = "/helloOrdersWithVariable")
    public MessageDTO helloOrders() {
        return new MessageDTO("Hello from order-service with my variable: " + myVariable);
    }

    @PostMapping(path = "/create-order")
    public OrderRequestDTO createOrder(
            @RequestBody
            OrderRequestDTO orderRequestDTO
    ){
        return orderService.createOrder(orderRequestDTO);
    }

}
