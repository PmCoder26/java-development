package com.parimal.e_wholesaler.order_service.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemRequestDTO {

    private Long id;
    private Long productId;
    private Integer quantity;

}
