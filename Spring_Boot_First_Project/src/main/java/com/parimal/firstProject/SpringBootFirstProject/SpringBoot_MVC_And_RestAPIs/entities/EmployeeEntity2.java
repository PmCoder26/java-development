package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees2")
// if you have an existing database record before using this annotation then this condition is required.
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class EmployeeEntity2 extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToOne(mappedBy = "manager", cascade = CascadeType.ALL)
    @JsonIgnore     // A Jackson annotation to ignore this field during serialization and deserialization to avoid recursive calls.
    private DepartmentEntity managedDepartment;

    @ManyToOne(cascade = CascadeType.ALL)      // for employee, many employees work for a single department.
    @JoinColumn(name = "worker_department_id", referencedColumnName = "id")
    @JsonIgnore
    private DepartmentEntity workerDepartment;

    @ManyToMany
    @JoinTable(
            name = "freelancer_department_mapping",
            // 'joinColumns' specifies the foreign key column in the join
            // table that references the primary key of the owner entity
            // (the entity where the @JoinTable annotation is placed).
            joinColumns = @JoinColumn(name = "employee_id"),
            // 'inverseJoinColumns' specifies the foreign key column in the join table that
            // references the primary key of the inverse entity (the other entity in the relationship).
            inverseJoinColumns = @JoinColumn(name = "department_id")
    )
    @JsonIgnore
    private List<DepartmentEntity> freelanceDepartments;

}
