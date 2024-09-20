package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@Getter         // used to create getter, using the lombok dependency
@Setter         // used to create setter, using the lombok dependency
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")      // if you don't use this annotation, still the code works by setting the table name as 'EmployeeEntity',
                                // but if you want to specifically specify the table name by your own
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private LocalDate dateOfJoining;
    @JsonProperty(value = "isActive")
    private Boolean isActive;
    private String role;
    private Double salary = (double) 0;
}
