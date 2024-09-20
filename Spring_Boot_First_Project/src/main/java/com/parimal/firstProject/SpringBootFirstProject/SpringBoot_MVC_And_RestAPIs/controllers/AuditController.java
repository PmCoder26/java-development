package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.controllers;


import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.entities.EmployeeEntity2;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/audit")
public class AuditController {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @GetMapping(path = "/employees2/{employeeId}")
    public List<EmployeeEntity2> getEmployeeRevisions(
            @PathVariable(required = true)
            Long employeeId
    ){
        AuditReader reader = AuditReaderFactory.get(entityManagerFactory.createEntityManager());
        List<Number> revisions = reader.getRevisions(EmployeeEntity2.class, employeeId);
        return revisions
                .stream()
                .map( revisionNumber -> reader.find(EmployeeEntity2.class, employeeId, revisionNumber))
                .toList();
    }

}
