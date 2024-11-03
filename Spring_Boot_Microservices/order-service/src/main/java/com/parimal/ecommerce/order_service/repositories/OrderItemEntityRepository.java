package com.parimal.ecommerce.order_service.repositories;


import com.parimal.ecommerce.order_service.entities.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemEntityRepository extends JpaRepository<OrderItemEntity, Long>
{
}
