package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.dto;


import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.entities.DepartmentEntity;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.entities.EmployeeEntity2;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class DepartmentDTO {
    private Long id;
    private String title;
    private Employee2DTO manager;
    private List<Employee2DTO> workers;
    private List<EmployeeEntity2> freelancers;
}
