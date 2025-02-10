package com.parimal.ecommerce.inventory_service.controllers;

import com.parimal.ecommerce.inventory_service.advices.ApiResponse;
import com.parimal.ecommerce.inventory_service.clients.OrderFeignClient;
import com.parimal.ecommerce.inventory_service.dtos.DataDTO;
import com.parimal.ecommerce.inventory_service.dtos.MessageDTO;
import com.parimal.ecommerce.inventory_service.dtos.OrderRequestDTO;
import com.parimal.ecommerce.inventory_service.dtos.ProductDTO;
import com.parimal.ecommerce.inventory_service.services.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
@RequestMapping(path = "/core")
@AllArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;
    private final DiscoveryClient discoveryClient;
    private final RestClient restClient;
    private final ModelMapper modelMapper;
    private final OrderFeignClient orderFeignClient;

    @GetMapping(path = "/getAllProducts")
    public List<ProductDTO> getAllProducts(){
        return productService.getAllInventory();
    }

    @GetMapping(path = "/getProductById/{productId}")
    public ProductDTO getProductById(
            @PathVariable
            Long productId
    ){
        return productService.getProductById(productId);
    }

    @GetMapping(path = "/fetchOrders")
    public MessageDTO fetchFromOrderService(){
        ServiceInstance orderService = discoveryClient.getInstances("order-service").getFirst();
        ApiResponse responseData = restClient
                .get()
                .uri(orderService.getUri() + "/orders/core/helloOrders")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
        log.info("Uri: {}", orderService.getUri().toString());
        return modelMapper.map(responseData.getData(), MessageDTO.class);
    }

    @GetMapping(path = "/fetchOrdersFromFeignClient")
    public MessageDTO fetchFromOrdersFeignClient(){
        ApiResponse orderResponse = orderFeignClient.helloOrders();
        return modelMapper.map(orderResponse.getData(), MessageDTO.class);
    }

    @PutMapping(path = "/reduce-stocks")
    public DataDTO<Double> reduceStocks(
            @RequestBody
            OrderRequestDTO orderRequestDTO
    ){
        return productService.reduceStocks(orderRequestDTO);
    }


}
