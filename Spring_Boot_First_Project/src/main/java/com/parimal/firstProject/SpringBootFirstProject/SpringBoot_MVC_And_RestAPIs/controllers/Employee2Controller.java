package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.controllers;


import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.dto.Employee2DTO;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.services.Employee2Service;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping(path = "/employees2")
public class Employee2Controller {

    private final Employee2Service service;

    @GetMapping(path = "/getAllEmployees")
    public List<Employee2DTO> getAllEmployees(){
        return service.getAllEmployees();
    }

    @GetMapping(path = "/getEmployeeById/{employeeId}")
    public Employee2DTO getEmployeeById(
            @PathVariable
            Long employeeId
    ){
        return service.getEmployeeById(employeeId);
    }

    @PutMapping(path = "/createNewEmployee")
    public Employee2DTO createNewEmployee(
            @RequestBody(required = true)
            Employee2DTO inputEmployee
    ){
        return service.createNewEmployee(inputEmployee);
    }

    @PostMapping(path = "/updateEmployeeNameById/{employeeId}")
    public Employee2DTO updateEmployeeById(
            @RequestBody(required = true)
            Employee2DTO inputEmployee,
            @PathVariable(required = true)
            Long employeeId
    ){
        return service.updateEmployeeNameById(inputEmployee, employeeId);
    }

}
