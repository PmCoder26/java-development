package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "products",
        uniqueConstraints = {
                @UniqueConstraint(name = "sku_unique", columnNames = {"sku"}),
                @UniqueConstraint(name = "title_price_unique", columnNames = {"title", "price"})        // this combination of title and price will be unique in the table.
                                                                                                        // hence, this combination can't be repeated one.
        },
        indexes = {
                // useful to enhance our queries(get queries), i.e., when user queries for a particular column many times then we index the column.
                // this boost the performance of the spring boot project for interacting with database integrated with it.
                // it uses the B-tree or hash table data structure for it.
                @Index(name = "sku_index", columnList = "sku")
        }
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 20, nullable = false)
    private String sku;

    private String title;

    private Double  price;

    private Integer quantity;

    @Column(name = "createdAt", nullable = false)
    @JsonFormat(pattern = "hh:mm:ss, dd:MM:yyyy")       // pattern for time and date.
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name ="updatedAt", nullable = false)
    @JsonFormat(pattern = "hh:mm:ss, dd:MM:yyyy")       // pattern for time and date.
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
