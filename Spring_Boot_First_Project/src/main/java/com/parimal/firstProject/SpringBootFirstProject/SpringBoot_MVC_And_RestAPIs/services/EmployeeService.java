package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.services;


import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.configs.ModelMapperConfig;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.dto.EmployeeDTO;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.entities.EmployeeEntity;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.exceptions.ResourceNotFoundException;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper mm){
        this.employeeRepository = employeeRepository;
        modelMapper = mm;
    }

    public Optional<EmployeeDTO> getEmployeeById(long id){
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);
        return employeeEntity.map(entity ->
                  modelMapper.map(entity, EmployeeDTO.class)
                );
    }

    // use the Optional, as recommended and industry standard, it basically used when the object to be
    // returned may or may not be null, hence it contains object which may or may not be null
    public List<EmployeeDTO> getAllEmployees(){
        List<EmployeeEntity> employees = employeeRepository.findAll();
        List<EmployeeDTO> dtos = employees.stream()
                .map(entity ->
                    modelMapper.map(entity, EmployeeDTO.class)
                )
                .collect(Collectors.toList());
        return dtos;
    }

    public EmployeeDTO createNewEmployee(EmployeeDTO inputEmployee){
        EmployeeEntity newEmployee = modelMapper.map(inputEmployee, EmployeeEntity.class);
        EmployeeEntity saved = employeeRepository.save(newEmployee);
        return modelMapper.map(saved, EmployeeDTO.class);
    }

//    public EmployeeDTO updateEmployeeById(EmployeeDTO inputEmployee, long employeeId){
//        EmployeeEntity employeeEntity = modelMapper.map(inputEmployee, EmployeeEntity.class);
//        // if the data exists for the given id, then it will update the data from inputEmployee, it just works as HashMap as in Java.
//        employeeEntity.setId(employeeId);
//        EmployeeEntity savedEntity = employeeRepository.save(employeeEntity);
//        return modelMapper.map(savedEntity, EmployeeDTO.class);
//    }

    // implementing the employee data update using the global exception handler.
    public EmployeeDTO updateEmployeeById(EmployeeDTO inputEmployee, long employeeId){
        boolean exists = isExistsById(employeeId);
        if(!exists) throw new ResourceNotFoundException("Employee not found of id: " + employeeId);
        EmployeeEntity employeeEntity = modelMapper.map(inputEmployee, EmployeeEntity.class);
        EmployeeEntity saved = employeeRepository.save(employeeEntity);
        return modelMapper.map(saved, EmployeeDTO.class);
    }

    public boolean deleteEmployeeById(long id) {
        try {
            if(!isExistsById(id)) return false;
            employeeRepository.deleteById(id);
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public EmployeeDTO updatePartialEmployeeById(long id, Map<String, Object> updates){
        try{
            if(!isExistsById(id)) return null;
            EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
            updates.forEach((field, value) -> {
                Field fieldToBeUpdated = ReflectionUtils.findField(EmployeeEntity.class, field);
                fieldToBeUpdated.setAccessible(true);       // as the fields in the EmployeeEntity class are private.
                ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);
            });
            EmployeeEntity savedEmployee = employeeRepository.save(employeeEntity);
            return modelMapper.map(savedEmployee, EmployeeDTO.class);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    private boolean isExistsById(long employeeId){
        return employeeRepository.existsById(employeeId);
    }

}
