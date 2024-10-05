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

}
