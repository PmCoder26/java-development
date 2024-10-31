package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.services;


import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.dto.Employee2DTO;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.entities.EmployeeEntity2;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.entities.SalaryAccountEntity;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.repositories.SalaryAccountRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class SalaryAccountService {

    private final SalaryAccountRepository accountRepository;
    private final ModelMapper modelMapper;

    public void createAccount(Employee2DTO employee){
        if(employee.getName().equals("Omkar")){
            throw new RuntimeException("Omkar not allowed!");
        }
        SalaryAccountEntity salaryAccount = SalaryAccountEntity.builder()
                .employee(modelMapper.map(employee, EmployeeEntity2.class))
                .balance(BigDecimal.ZERO)
                .build();
        accountRepository.save(salaryAccount);
    }

}
