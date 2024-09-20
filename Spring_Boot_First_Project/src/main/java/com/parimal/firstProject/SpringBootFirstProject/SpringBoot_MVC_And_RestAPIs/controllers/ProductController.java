package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.controllers;


import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.dto.ProductDTO;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.entities.ProductEntity;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final int PAGE_SIZE = 5;

    public ProductController(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping(path = "/getAllProducts")
    public List<ProductDTO> getAllProducts(){
        List<ProductEntity> entities = productRepository.findAll();
        List<ProductDTO> dtos = entities.stream()
                .map( entity -> modelMapper.map(entity, ProductDTO.class))
                .toList();
        return dtos;
    }

    // using the concept of Pageable and Paging.
    // Note - in paging the page no. starts form '0'.
    @GetMapping(path = "/getAllProductsByPaging")
    public List<ProductDTO> getAllProductsByPaging(
            @RequestParam(defaultValue = "id")
            String sortBy,
            @RequestParam(defaultValue = "1")
            Integer pageNumber
    ){
        Pageable pageable = PageRequest.of(pageNumber, PAGE_SIZE, Sort.by(Sort.Direction.ASC, sortBy));
        List<ProductEntity> entities = productRepository.findAll(pageable).toList();
        List<ProductDTO> dtos = entities.stream()
                .map( entity -> modelMapper.map(entity, ProductDTO.class))
                .toList();
        return dtos;
    }

    @GetMapping(path = "/getAllProductsByPagingIgnoreCase")
    public List<ProductDTO> getAllProductsByPagingIgnoreCase(
            @RequestParam(defaultValue = "")
            String title,
            @RequestParam(defaultValue = "id")
            String sortBy,
            @RequestParam(defaultValue = "0")
            Integer pageNumber
    ){
        Pageable pageable = PageRequest.of(pageNumber, PAGE_SIZE, Sort.by(sortBy));
        List<ProductEntity> entities = productRepository.findByTitleContainingIgnoreCase(title, pageable);
        List<ProductDTO> dtos = entities.stream()
                .map( entity -> modelMapper.map(entity, ProductDTO.class))
                .toList();
        return dtos;
    }

    @GetMapping(path = "/getProductsByPrice")
    public List<ProductDTO> getProductsByPrice(){
        List<ProductEntity> entities = productRepository.findByOrderByPrice();
        List<ProductDTO> dtos = entities.stream()
                .map( entity -> modelMapper.map(entity, ProductDTO.class))
                .toList();
        return dtos;
    }

    @GetMapping(path = "/getProductsOrderedBy")
    public List<ProductDTO> getProductsOrderedBy(
            @RequestParam(defaultValue = "id")
            String sortBy
    ){
//        List<ProductEntity> entities = productRepository.findBy(Sort.by(Sort.Direction.DESC, sortBy));    // or
        List<ProductEntity> entities = productRepository.findBy(
                Sort.by(Sort.Order.desc(sortBy),
                        // if more than one product have the same quantity(of sort order string) then they will be sorted in asc, by "title"
                        Sort.Order.asc("title"))
        );
        List<ProductDTO> dtos = entities.stream()
                .map( entity -> modelMapper.map(entity, ProductDTO.class))
                .toList();
        return dtos;
    }

}
