package com.parimal.ecommerce.inventory_service.clients;

import com.parimal.ecommerce.inventory_service.advices.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


// also use the @EnableFeignClients in the SpringBootApplication class(Main class) of our application.
@FeignClient(name = "order-service", path = "/orders")
public interface OrderFeignClient {

    @GetMapping(path = "/core/helloOrders")
    ApiResponse helloOrders();

}
