package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.repositories;

import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.entities.ProductEntity;
import com.parimal.firstProject.SpringBootFirstProject.TestContainerConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(TestContainerConfiguration.class)
@Slf4j
class ProductRepositoryTest {

    @Autowired
    private ProductRepository repository;

    private static ProductEntity product = new ProductEntity();

    @BeforeAll
    static void beforeAll(){
        log.info("Starting the tests for the Product Repository");
        product = ProductEntity.builder()
                .id(101L)
                .title("MacBook2")
                .price(100000.0)
                .quantity(10)
                .sku("MacBook Air M2")
                .build();
    }

    @Test
    void testFindByTitle_whenTitleIsPresent_thenReturnProduct() {
        // Arrange, Given.
        repository.save(product);

        // Act, When.
        List<ProductEntity> products = repository.findByTitle("MacBook");

        // Assert, Then.
        assertThat(products).isNotEmpty();
        assertThat(products).isNotNull();
    }

    @Test
    void testFindByTitle_whenTitleNotFound_thenReturnEmptyList(){
        String title = "invalid title";

        List<ProductEntity> products = repository.findByTitle(title);

        assertThat(products).isNotNull();
        assertThat(products).isEmpty();

    }

    @Test
    void findByCreatedAtAfter() {
    }

    @Test
    void findByQuantityAndPrice() {
    }

    @Test
    void findByQuantityGreaterThanOrPriceLessThan() {
    }

    @Test
    void findByTitleLike() {
    }

    @AfterAll
    static void afterAll(){
        log.info("Finished all the tests for Product Repository");
    }
}