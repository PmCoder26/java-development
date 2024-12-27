package com.parimal.ecommerce.order_service.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataDTO<T> {

    private T data;

}
