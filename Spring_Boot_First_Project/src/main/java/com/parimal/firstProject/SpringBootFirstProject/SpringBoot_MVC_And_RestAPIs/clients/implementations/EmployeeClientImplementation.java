package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.clients.implementations;


import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.advices.ApiResponse;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.clients.EmployeeClient;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.dto.Employee2DTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeClientImplementation implements EmployeeClient {

    @Autowired
    private final RestClient restClient;

    // Implementing the logging concept.
    private final Logger logger = LoggerFactory.getLogger(EmployeeClientImplementation.class);

    @Override
    public List<Employee2DTO> getAllEmployees() {
        logger.error("error log");
        logger.warn("warn log");
        logger.info("info log");
        logger.trace("trace log");  // to see this log in the terminal, we need to configure in the application.properties file.
        try {
            logger.trace("Trying to fetch the employees from the another server!\n");
            ApiResponse<List<Employee2DTO>> employeesResponse =  restClient.get()
                    .uri("/employees2/getAllEmployees")  // the remaining part of the api request address.
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {});    // auto-detects the datatype and convert response in that type.
            logger.debug("Successfully get all the employees");
            logger.trace("Retrieved employees are: {}", employeesResponse.getData());
            return employeesResponse.getData();
        } catch(Exception e){
            logger.error("Error in the getAllEmployees from the another server." + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Employee2DTO getEmployeeById(Long employeeId){
        try {
            ApiResponse<Employee2DTO> employee2DTO = restClient.get()
                    .uri("/employees2/getEmployeeById/{employeeId}", employeeId)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {});
            return employee2DTO.getData();
        } catch(Exception e){
            throw e;
        }
    }

    @Override
    public Employee2DTO createNewEmployee(Employee2DTO inputEmployee){
        try{
            ApiResponse<Employee2DTO> employee2DTOApiResponse = restClient.put()
                    .uri("/employees2/createNewEmployee")
                    .body(inputEmployee)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {});
            return employee2DTOApiResponse.getData();
        } catch(Exception e){
            throw e;
        }
    }

}
