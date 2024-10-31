package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.services;

import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.dto.Employee2DTO;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.entities.EmployeeEntity2;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.repositories.Employee2Repository;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class Employee2Service {

    private final Employee2Repository repository;
    private final SalaryAccountService accountService;
    private final ModelMapper modelMapper;

    // method level transaction, if anything goes wrong in the method then database changes are rollback.
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)   // uses the previous transaction's context, if not exists then it creates new one.
    public Employee2DTO createNewEmployee(Employee2DTO inputEmployee){
        EmployeeEntity2 toSave = modelMapper.map(inputEmployee, EmployeeEntity2.class);
        EmployeeEntity2 saved = repository.save(toSave);
        accountService.createAccount(inputEmployee);
        return modelMapper.map(saved, Employee2DTO.class);
    }

    public List<Employee2DTO> getAllEmployees(){
        List<EmployeeEntity2> entities = repository.findAll();
        List<Employee2DTO> dtos = entities.stream()
                .map( entity -> modelMapper.map(entity, Employee2DTO.class))
                .toList();
        return dtos;
    }

    public Employee2DTO getEmployeeById(Long id) {
        Optional<EmployeeEntity2> employeeEntity = repository.findById(id);
        return modelMapper.map(employeeEntity, Employee2DTO.class);
    }

    public Employee2DTO updateEmployeeNameById(Employee2DTO inputEmployee, Long employeeId) {
        Optional<EmployeeEntity2> employeeEntity = repository.findById(employeeId);
        if(employeeEntity.isPresent()){
            employeeEntity.get().setName(inputEmployee.getName());
            repository.save(employeeEntity.get());
            return modelMapper.map(employeeEntity.get(), Employee2DTO.class);
        }
        else{
            return null;
        }
    }
}
