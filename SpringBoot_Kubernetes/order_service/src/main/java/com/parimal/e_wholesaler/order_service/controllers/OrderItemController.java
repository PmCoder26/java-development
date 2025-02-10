package com.parimal.e_wholesaler.order_service.controllers;

import com.parimal.e_wholesaler.order_service.dtos.OrderItemRequestDTO;
import com.parimal.e_wholesaler.order_service.dtos.OrderItemResponseDTO;
import com.parimal.e_wholesaler.order_service.services.OrderItemService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/order-item")
@AllArgsConstructor
public class OrderItemController {

    private final OrderItemService itemService;


    @PostMapping
    public OrderItemResponseDTO addItem(
            @RequestBody
            OrderItemRequestDTO requestDTO
    ) {
        return itemService.addOrderItem(requestDTO);
    }

}
