package com.parimal.ecommerce.inventory_service.services;


import com.parimal.ecommerce.inventory_service.dtos.DataDTO;
import com.parimal.ecommerce.inventory_service.dtos.OrderRequestDTO;
import com.parimal.ecommerce.inventory_service.dtos.OrderRequestItemDTO;
import com.parimal.ecommerce.inventory_service.dtos.ProductDTO;
import com.parimal.ecommerce.inventory_service.entities.ProductEntity;
import com.parimal.ecommerce.inventory_service.exceptions.ResourceNotFoundException;
import com.parimal.ecommerce.inventory_service.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public List<ProductDTO> getAllInventory(){
        log.info("Fetching all the inventory items...");
        List<ProductEntity> products = productRepository.findAll();
        return products.stream()
                .map(productEntity -> modelMapper.map(productEntity, ProductDTO.class))
                .toList();
    }

    public ProductDTO getProductById(Long id){
        log.info("Fetching the product with id: {}", id);
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No such product with id: " + id));
        return modelMapper.map(product, ProductDTO.class);
    }

    @Transactional()
    public DataDTO<Double> reduceStocks(OrderRequestDTO orderRequestDTO) {
        try {
            log.info("Reducing the stocks...");
            Double totalPrice = 0.0;
            for (OrderRequestItemDTO requestItemDTO : orderRequestDTO.getOrderItems()) {
                Long productId = requestItemDTO.getProductId();
                Integer quantity = requestItemDTO.getQuantity();
                ProductEntity product = productRepository.findById(productId)
                        .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));
                product.setStock(product.getStock() - quantity);
                productRepository.save(product);
                totalPrice += quantity * product.getPrice();
            }
            return new DataDTO<>(totalPrice);
        } catch (Exception e){
            log.info("reduce stocks error {}", e.getMessage());
            return new DataDTO<>(0.0);
        }
    }
}
