package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "departments")
public class DepartmentEntity extends AuditableEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_manager")
    private EmployeeEntity2 manager;

    @OneToMany(mappedBy = "workerDepartment", cascade = CascadeType.ALL, fetch = FetchType.EAGER)       // for department, one department can have many workers.
    private List<EmployeeEntity2> workers;

    @ManyToMany(mappedBy = "freelanceDepartments")
    private List<EmployeeEntity2> freelancers;

}
