package com.parimal.ecommerce.order_service.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemAddedEvent {

    private Long id;

    private Long productId;

    private Integer quantity;

}
