package com.parimal.ecommerce.inventory_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {

    private Long id;
    private List<OrderRequestItemDTO> orderItems;
    private Double totalPrice;

}
