package com.parimal.ecommerce.inventory_service.services;


import com.parimal.ecommerce.inventory_service.dtos.ProductDTO;
import com.parimal.ecommerce.inventory_service.entities.ProductEntity;
import com.parimal.ecommerce.inventory_service.exceptions.ResourceNotFoundException;
import com.parimal.ecommerce.inventory_service.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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


}
