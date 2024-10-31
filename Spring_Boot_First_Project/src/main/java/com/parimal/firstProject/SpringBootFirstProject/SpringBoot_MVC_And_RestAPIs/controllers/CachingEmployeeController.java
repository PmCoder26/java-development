package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.controllers;


import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.dto.Employee2DTO;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.services.CacheEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/cachingEmployee")
@RequiredArgsConstructor
public class CachingEmployeeController {

    private final CacheEmployeeService employeeService;

    @GetMapping(path = "/getEmployeeById/{employeeId}")
    public Employee2DTO getEmployeeById(
            @PathVariable
            Long employeeId
    ){
        return employeeService.getEmployeeById(employeeId);
    }

    @PutMapping(path = "updateEmployeeById/{employeeId}")
    public Employee2DTO updateEmployeeById(
            @PathVariable
            Long employeeId,
            @RequestBody
            Employee2DTO employee2DTO
    ){
        return employeeService.updateEmployeeById(employeeId, employee2DTO);
    }

    @PutMapping(path = "deleteEmployeeById/{employeeId}")
    public void deleteEmployeeById(
            @PathVariable
            Long employeeId
    ){
        employeeService.deleteEmployeeById(employeeId);
    }

}
