package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.controllers;


import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.dto.EmployeeDTO;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.entities.EmployeeEntity;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.exceptions.ResourceNotFoundException;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.repositories.EmployeeRepository;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.services.EmployeeService;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;


@RestController         // determines that the controller is REST in nature.
@RequestMapping("/employees")     // path for parent
public class EmployeeController {

//    private final EmployeeRepository employeeRepository;
//
//    public EmployeeController(EmployeeRepository employeeRepository){
//        this.employeeRepository = employeeRepository;
//    }


    // children
//    @GetMapping(path = "/{id}")       // using the PathVariable
//    public EmployeeDTO getEmployeeById(
//            @PathVariable double id
//    ){
//        return new EmployeeDTO(
//                id, "Parimal",
//                "parimal@gmail.com", 20,
//                LocalDate.of(2024, 6, 2),
//                true
//        );
//    }
//
//    // children
//    @GetMapping(path = "")            // using the RequestParam
//    public String getAllEmployees(
//            @RequestParam(required = false)
//            int age,
//            @RequestParam(required = false)
//            String sortBy
//    ){
//        return "Hey age: " + age + ", sortBy: " + sortBy;
//    }

    // for now (just for learning the database demo), directly using the repository inside the controller, but this practice is not recommended.
//    @GetMapping
//    public EmployeeEntity getEmployeeById(
//            @PathVariable(name = "employeeId")
//            long id
//    ){
//        return employeeRepository.getById(id);
//    }
//
//    @GetMapping(path = "/getAllEmployees")
//    public List<EmployeeEntity> getAllEmployees(){
//        return employeeRepository.findAll();
//    }
//
//    @PostMapping(path = "/createNew")
//    public void createEmployee2(
//            @RequestBody(required = true)
//            EmployeeEntity inputEmployee
//    ){
//        employeeRepository.save(inputEmployee);
//    }

//    @PostMapping(path = "/samplePost")
//    public String createNewEmployee(){
//        return "Hello from post";
//    }

//    @PutMapping(path = "")
//    public String updateEmployeeId(){
//        return "Hello from put";
//    }

//    @PostMapping(path = "/2")
//    public EmployeeDTO createNewEmployee2(
//            @RequestBody
//            EmployeeDTO inputEmployee
//    ){
//        inputEmployee.setId(2);
//        return inputEmployee;
//    }

    // Now designing with the proper MVC structure.
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/getEmployee")
    public ResponseEntity<EmployeeDTO> getEmployeeById(
            @RequestParam(required = true, name = "id")
            long id
    ){
        Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(id);
        return employeeDTO
                .map(dto ->
                    ResponseEntity.ok(dto)
                )
//                .orElse(ResponseEntity.notFound().build());
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found of id: " + id));
    }

    // using the exception handling in the SpringBoot.
    // since, the below exception code is written for this controller, it works only for this controller.
//    @ExceptionHandler(NoSuchElementException.class)
//    public ResponseEntity<String> handleEmployeeNotFound(){
//        return new ResponseEntity<>("Employee not found for the given data", HttpStatus.NOT_FOUND);
//    }

    @GetMapping(path = "/getEmployees")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping(path = "/createNewEmployee")
    public ResponseEntity<EmployeeDTO> createNewEmployee(
            @RequestBody
            @Valid
            EmployeeDTO inputEmployee
    ){
        EmployeeDTO saved = employeeService.createNewEmployee(inputEmployee);
        if(saved == null) return ResponseEntity.notFound().build();
        else return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping(path = "/updateEmployee")           // when you want to change the entire database entity or big data in database.
    public ResponseEntity<EmployeeDTO> updateEmployeeById(
            @RequestBody(required = true)
            EmployeeDTO inputEmployee,
            @RequestParam(required = true)
            long employeeId
    ){
        return ResponseEntity.ok(employeeService.updateEmployeeById(inputEmployee, employeeId));
    }


    @DeleteMapping(path = "/deleteEmployee")
    public ResponseEntity<Boolean> deleteEmployeeById(
            @RequestParam(required = true)
            long employeeId
    ){
        boolean isDeleted = employeeService.deleteEmployeeById(employeeId);
        if (isDeleted) return ResponseEntity.ok(true);
        else return ResponseEntity.notFound().build();
    }

    @PatchMapping(path = "/updateEmployeeByPatch")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(
            @RequestBody(required = true)
            Map<String, Object> updates,
            @RequestParam(required = true)
            long employeeId
    ){
        return ResponseEntity.ok(employeeService.updatePartialEmployeeById(employeeId, updates));
    }




}
