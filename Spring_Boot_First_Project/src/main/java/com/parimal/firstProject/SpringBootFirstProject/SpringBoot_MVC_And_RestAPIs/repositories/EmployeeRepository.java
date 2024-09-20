package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.repositories;


import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository()
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

}
