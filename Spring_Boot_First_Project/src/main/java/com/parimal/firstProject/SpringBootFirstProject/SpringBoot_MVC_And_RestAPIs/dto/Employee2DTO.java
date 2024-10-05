package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.entities.DepartmentEntity;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.entities.EmployeeEntity2;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
public class Employee2DTO implements Serializable {
    private Long id;
    private String name;
    @JsonIgnore
    private DepartmentDTO managedDepartment;
    @JsonIgnore
    private DepartmentDTO workerDepartment;
    @JsonIgnore
    private List<DepartmentEntity> freelanceDepartments;
}
