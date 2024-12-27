package com.parimal.ecommerce.inventory_service.repositories;

import com.parimal.ecommerce.inventory_service.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
