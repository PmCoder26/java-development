package com.parimal.ecommerce.inventory_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestItemDTO {

    private Long id;
    private Long productId;
    private Integer quantity;

}
