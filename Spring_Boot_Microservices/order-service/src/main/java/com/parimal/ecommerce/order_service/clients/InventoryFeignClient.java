package com.parimal.ecommerce.order_service.clients;


import com.parimal.ecommerce.order_service.advices.ApiResponse;
import com.parimal.ecommerce.order_service.dtos.OrderRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "inventory-service", path = "/products")
public interface InventoryFeignClient {

    @PutMapping(path = "/core/reduce-stocks")
    ApiResponse reduceStocks(@RequestBody OrderRequestDTO orderRequestDTO);

}
