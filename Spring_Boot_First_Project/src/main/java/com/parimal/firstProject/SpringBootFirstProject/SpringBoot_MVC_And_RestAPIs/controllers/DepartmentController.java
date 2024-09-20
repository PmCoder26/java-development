package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.controllers;


import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.dto.DepartmentDTO;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.dto.Employee2DTO;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.entities.DepartmentEntity;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.entities.EmployeeEntity2;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.services.DepartmentService;
import lombok.*;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {

    private final DepartmentService service;

    @PutMapping(path = "/createNewDepartment")
    public DepartmentDTO createNewDepartment(
            @RequestBody(required = true)
            DepartmentDTO inputDepartment
    ){
        return service.createNewDepartment(inputDepartment);
    }

    @PutMapping(path = "/{departmentId}/manager/{employeeId}")
    public DepartmentDTO assignManagerToDepartment(
            @PathVariable
            Long departmentId,
            @PathVariable
            Long employeeId
    ){
        return service.assignManagerToDepartment(departmentId, employeeId);
    }

    @GetMapping(path = "/getAllDepartments")
    @Secured(value = {"ALL_EMPLOYEE_VIEW"})          // authorization part; refer the 'WebSecurityConfig.java' file.
    // you can also configure using the Roles, for that, you need to add the roles in the authorities of user in 'UserEntity'
    // so that when spring security checks the authorities of the user,
    // it can find the roles in the authorities(in UserEntity ) along with the already included permissions(as authorities).
    public List<DepartmentDTO> getAllDepartments(){
        List<DepartmentDTO> list = service.getAllDepartments();
        return list;
    }

    @GetMapping(path = "/getAssignedDepartmentOfManager/{employeeId}")
    public DepartmentDTO getAssignedDepartmentOfManager(
            @PathVariable(required = true)
            Long employeeId
    ){
//        return service.getAssignedDepartmentOfManager(employeeId);
                // Method 2.
        return service.getAssignedDepartmentOfManager2(employeeId);
    }

    @GetMapping(path = "/getDepartmentById/{departmentId}")
    @PreAuthorize(value = "hasAnyRole('USER', 'ADMIN') AND hasAuthority('DEPARTMENT_VIEW')")    // you can also use 'OR' as per your requirement.
    public DepartmentDTO getDepartmentById(
            @PathVariable
            Long departmentId
    ){
        return service.getDepartmentById(departmentId);
    }

    @GetMapping(path = "/getWorkersByDepartmentId/{departmentId}")
    public List<Employee2DTO> getWorkersByDepartmentId(
            @PathVariable(required = true)
            Long departmentId
    ){
        return service.getWorkersByDepartmentId(departmentId);
    }

    @GetMapping(path = "/getManagerByDepartmentId/{departmentId}")
    public Employee2DTO getManagerByDepartmentId(
            @PathVariable(required = true)
            Long departmentId
    ){
        return service.getManagerByDepartmentId(departmentId);
    }

    @PutMapping(path = "/{departmentId}/worker/{employeeId}")
    public Employee2DTO assignWorkerToDepartment(
            @PathVariable(required = true)
            Long departmentId,
            @PathVariable(required = true)
            Long employeeId
    ){
        return service.assignWorkerToDepartment(departmentId, employeeId);
    }

    @PutMapping(path = "/{departmentId}/freelancers/{employeeId}")
    public Employee2DTO assignFreelancerToDepartment(
            @PathVariable(required = true)
            Long departmentId,
            @PathVariable(required = true)
            Long employeeId
    ){
        return service.assignFreelancerToDepartment(departmentId, employeeId);
    }


}
