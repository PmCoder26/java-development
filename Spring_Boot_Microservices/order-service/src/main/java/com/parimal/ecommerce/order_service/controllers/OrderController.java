package com.parimal.ecommerce.order_service.controllers;


import com.parimal.ecommerce.order_service.dtos.MessageDTO;
import com.parimal.ecommerce.order_service.dtos.OrderRequestDTO;
import com.parimal.ecommerce.order_service.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/core")
public class OrderController {

    private final OrderService orderService;

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

    @PostMapping(path = "/create-order")
    public OrderRequestDTO createOrder(
            @RequestBody
            OrderRequestDTO orderRequestDTO
    ){
        return orderService.createOrder(orderRequestDTO);
    }

}
