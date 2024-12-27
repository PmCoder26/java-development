package com.parimal.ecommerce.order_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {

    private Long id;
    private List<OrderItemRequestDTO> orderItems;
    private Double totalPrice;

}
