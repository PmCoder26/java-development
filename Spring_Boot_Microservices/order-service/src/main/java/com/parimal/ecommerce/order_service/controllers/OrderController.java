package com.parimal.ecommerce.order_service.controllers;


import com.parimal.ecommerce.order_service.dtos.MessageDTO;
import com.parimal.ecommerce.order_service.dtos.OrderRequestDTO;
import com.parimal.ecommerce.order_service.services.OrderService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.kafka.core.KafkaTemplate;
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

    private final KafkaTemplate<String, String> kafkaTemplate;


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

    @GetMapping(path = "/hello-orders-with-kafka-message")
    public MessageDTO helloOrdersWithKafka() {
//        kafkaTemplate.send("parimal-events", "Hello parimal event");
        // if the topic is not created initially or not present then the kafka creates automatically.
//        kafkaTemplate.send("new-topic-events", "Hello from new topic event");
//        kafkaTemplate.send("my-new-topic", "Message from my-new-topic");
        // now sending thousands of messages.
        for(int x = 0; x < 1000; x++) {
            kafkaTemplate.send("my-new-topic", "Message from my-new-topic with offset: " + x);
        }
        return new MessageDTO("Hello from orders with kafka");
    }

    @PostMapping(path = "/create-order")
    public OrderRequestDTO createOrder(
            @RequestBody
            OrderRequestDTO orderRequestDTO
    ){
        return orderService.createOrder(orderRequestDTO);
    }

}
