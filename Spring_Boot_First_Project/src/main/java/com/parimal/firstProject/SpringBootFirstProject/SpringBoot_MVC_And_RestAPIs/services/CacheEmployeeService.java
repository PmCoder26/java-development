package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.services;

import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.dto.Employee2DTO;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.entities.EmployeeEntity2;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.exceptions.ResourceNotFoundException;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.repositories.CacheEmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


// using this service for performing the caching tasks/concept.
@Service
@RequiredArgsConstructor
@Slf4j
public class CacheEmployeeService {

    private final ModelMapper modelMapper;
    private final CacheEmployeeRepository employeeRepository;

    @Cacheable(cacheNames = "employee_by_id_cache", key = "#employeeId")      // beware of from where this annotation is imported, refer from the above imports.
    public Employee2DTO getEmployeeById(Long employeeId) {
        log.info("Entered in the method");
        EmployeeEntity2 employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> {
                    log.error("Employee not found for id: " + employeeId);
                    return new ResourceNotFoundException("Employee not found with id: " + employeeId);
                });
        log.info("Returning from the database");
        return modelMapper.map(employee, Employee2DTO.class);
    }

    @CachePut(cacheNames = "employee_by_id_cache", key = "#result.id")      // updates the data in the cache.
    // 'result' is a reserved keyword. It is the nothing but the returned value from the below method.
    public Employee2DTO updateEmployeeById(Long employeeId, Employee2DTO employee2DTO) {
        EmployeeEntity2 employeeEntity2 = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("No such employee exists!"));
        modelMapper.map(employee2DTO, employeeEntity2);
        EmployeeEntity2 saved = employeeRepository.save(employeeEntity2);
        log.info("Employee updated successfully!");
        return modelMapper.map(saved, Employee2DTO.class);
    }

    @CacheEvict(cacheNames = "employee_by_id_cache", key = "#result.id")        // deletes the data from the cache.
    public void deleteEmployeeById(Long employeeId) {
        log.info("Deleting the employee!");
        if(employeeRepository.existsById(employeeId)){
            employeeRepository.deleteById(employeeId);
            log.info("Employee deleted successfully");
            return;
        }
        log.info("Employee not found!");
    }
}
