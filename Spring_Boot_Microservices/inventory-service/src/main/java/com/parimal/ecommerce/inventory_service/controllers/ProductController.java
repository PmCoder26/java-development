package com.parimal.ecommerce.inventory_service.controllers;


import com.parimal.ecommerce.inventory_service.dtos.ProductDTO;
import com.parimal.ecommerce.inventory_service.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping(path = "getAllProducts")
    public List<ProductDTO> getAllProducts(){
        return productService.getAllInventory();
    }

    @GetMapping(path = "getProductById/{productId}")
    public ProductDTO getProductById(
            @PathVariable
            Long productId
    ){
        return productService.getProductById(productId);
    }
}
