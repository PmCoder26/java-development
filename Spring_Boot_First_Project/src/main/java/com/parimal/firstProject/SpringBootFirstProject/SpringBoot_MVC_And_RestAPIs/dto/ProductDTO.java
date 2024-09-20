package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long id;

    private String sku;

    private String title;

    private Double  price;

    private Integer quantity;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
