package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.repositories;

import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.entities.ProductEntity;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>{

    // since we have only declared the method, the hibernate automatically decrypts/understands the query by the method name
    // and hence provide the service to the method to perform the required operation.
    List<ProductEntity> findByTitle(String nestle123);

    List<ProductEntity> findByCreatedAtAfter(LocalDateTime after);

    List<ProductEntity> findByQuantityAndPrice(Integer i, Double     v);

    List<ProductEntity> findByQuantityGreaterThanOrPriceLessThan(Integer quantity, Integer price);

    // finds the entities containing the 'title'(method parameter) or the 'title'(method parameter) itself in the entity's field.
    List<ProductEntity> findByTitleLike(String title);

    // similar to 'findByTitleLike()' but more convenient.
    List<ProductEntity> findByTitleContaining(String nest);

    // same as 'findByTitleContaining()'
    List<ProductEntity> findByTitleContains(String nest);

    Optional<ProductEntity> findByTitleAndPrice(String title, Double price);


    /*
        Custom and complex queries which are manually designed by developer.
        here, '?1 and ?2' are the parameters passed with the below function when called.
        hence in Query, syntax 1:  to use these parameters: ?position_of_the_parameter,
                        syntax 2:  to use these parameters: :parameter_name.
        note that, even if you define the column name in the entity class, you have to
        use the object name(in the query) of the entity defined(in the entity class) not the column name.
        The Hibernate will automatically convert the entity field name into the column name
        to proceed further.
        Note - the query is written in the JPQL not SQL query language.
    */
//    @Query(value = "SELECT e FROM ProductEntity e WHERE e.title = ?1 AND e.price  = ?2")      // or
    @Query(value = "SELECT e FROM ProductEntity e WHERE e.title = :title AND e.price = :price")
    Optional<ProductEntity> findByTitleAndPrice2(String title, Double price);

    List<ProductEntity> findByOrderByPrice();

    List<ProductEntity> findBy(Sort sort);

    List<ProductEntity> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}