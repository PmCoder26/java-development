package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.clients;

import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.dto.Employee2DTO;

import java.util.List;

public interface EmployeeClient {

    List<Employee2DTO> getAllEmployees();

    Employee2DTO getEmployeeById(Long employeeId);

    Employee2DTO createNewEmployee(Employee2DTO employee);

}
