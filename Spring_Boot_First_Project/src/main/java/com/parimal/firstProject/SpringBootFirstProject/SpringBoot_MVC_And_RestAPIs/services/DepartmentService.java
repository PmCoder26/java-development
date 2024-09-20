package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.services;

import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.dto.DepartmentDTO;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.dto.Employee2DTO;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.entities.DepartmentEntity;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.entities.EmployeeEntity2;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.repositories.DepartmentRepository;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.repositories.Employee2Repository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final Employee2Repository employeeRepository;
    private final ModelMapper modelMapper;

    public DepartmentDTO createNewDepartment(DepartmentDTO inputDepartment){
        DepartmentEntity toSave = modelMapper.map(inputDepartment, DepartmentEntity.class);
        DepartmentEntity savedDepartment = departmentRepository.save(toSave);
        return modelMapper.map(savedDepartment, DepartmentDTO.class);
    }

    public List<DepartmentDTO> getAllDepartments(){
        List<DepartmentEntity> entities = departmentRepository.findAll();
        return entities.stream()
                .map( entity -> modelMapper.map(entity, DepartmentDTO.class))
                .toList();
    }

    public DepartmentDTO assignManagerToDepartment(Long departmentId, Long employeeId) {
        Optional<DepartmentEntity> departmentEntity = departmentRepository.findById(departmentId);
        Optional<EmployeeEntity2> employeeEntity = employeeRepository.findById(employeeId);
        Optional<DepartmentEntity> saved = departmentEntity.map( department ->
                employeeEntity.map( employee -> {
                    department.setManager(employee);
                    return departmentRepository.save(department);
                })).orElse(null);
        return modelMapper.map(saved, DepartmentDTO.class);
    }

    public DepartmentDTO getAssignedDepartmentOfManager(Long employeeId){
        Optional<EmployeeEntity2> employeeEntity = employeeRepository.findById(employeeId);
        return employeeEntity.map(employee -> {
            return modelMapper.map(employee.getManagedDepartment(), DepartmentDTO.class);
        }).orElse(null);
    }

    // alternative of the above method.
    public DepartmentDTO getAssignedDepartmentOfManager2(Long employeeId){
        EmployeeEntity2 employeeEntity = EmployeeEntity2.builder()
                .id(employeeId)
                .build();
        return modelMapper.map(
                departmentRepository.findByManager(employeeEntity), DepartmentDTO.class
        );
    }

    public Employee2DTO assignWorkerToDepartment(Long departmentId, Long employeeId) {
        Optional<DepartmentEntity> departmentEntity = departmentRepository.findById(departmentId);
        Optional<EmployeeEntity2> employeeEntity = employeeRepository.findById(employeeId);
        Optional<EmployeeEntity2> saved = departmentEntity.map(department ->
                employeeEntity.map( employee -> {
                    employee.setWorkerDepartment(department);
                    return employeeRepository.save(employee);
                })).orElseGet(null);
        return modelMapper.map(saved, Employee2DTO.class);
    }

    public DepartmentDTO getDepartmentById(Long departmentId) {
        Optional<DepartmentEntity> entity = departmentRepository.findById(departmentId);
        return modelMapper.map(entity, DepartmentDTO.class);
    }

    public Employee2DTO assignFreelancerToDepartment(Long departmentId, Long employeeId) {
        Optional<DepartmentEntity> departmentEntity = departmentRepository.findById(departmentId);
        Optional<EmployeeEntity2> employeeEntity = employeeRepository.findById(employeeId);
        Optional<EmployeeEntity2> saved = departmentEntity.map(department ->
                employeeEntity.map( employee -> {
                    List<DepartmentEntity> departmentEntities = new ArrayList<>();
                    departmentEntities.add(department);
                    employee.setFreelanceDepartments(departmentEntities);
                    return employeeRepository.save(employee);
                })).orElseGet(null);
        return modelMapper.map(saved, Employee2DTO.class);
    }

    public List<Employee2DTO> getWorkersByDepartmentId(Long departmentId) {
        List<EmployeeEntity2> workers = departmentRepository.findById(departmentId)
                .get().getWorkers();
        return workers.stream()
                .map( worker ->
                    modelMapper.map(worker, Employee2DTO.class))
                .toList();
    }

    public Employee2DTO getManagerByDepartmentId(Long departmentId) {
        Optional<DepartmentEntity> departmentEntity = departmentRepository.findById(departmentId);
        return modelMapper.map(departmentEntity.get().getManager(), Employee2DTO.class);
    }
}
