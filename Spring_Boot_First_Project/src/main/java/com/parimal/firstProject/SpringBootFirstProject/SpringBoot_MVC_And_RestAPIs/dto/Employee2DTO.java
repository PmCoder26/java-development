package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Employee2DTO implements Serializable {
    private Long id;
    private String name;
    @JsonIgnore
    private DepartmentDTO managedDepartment;
    @JsonIgnore
    private DepartmentDTO workerDepartment;
    @JsonIgnore
    private List<DepartmentDTO> freelanceDepartments;
}
