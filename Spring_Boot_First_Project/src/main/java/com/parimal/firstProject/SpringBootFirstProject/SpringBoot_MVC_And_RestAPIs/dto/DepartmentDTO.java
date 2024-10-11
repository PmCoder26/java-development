package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.dto;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DepartmentDTO implements Serializable {
    private Long id;
    private String title;
    private Employee2DTO manager;
    private List<Employee2DTO> workers;
    private List<Employee2DTO> freelancers;
}
