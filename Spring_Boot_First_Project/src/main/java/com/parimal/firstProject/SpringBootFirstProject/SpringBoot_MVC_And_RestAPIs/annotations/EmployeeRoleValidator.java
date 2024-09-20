package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.annotations;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class EmployeeRoleValidator implements ConstraintValidator<EmployeeRoleValidation, String> {

    @Override
    public boolean isValid(String inputRole, ConstraintValidatorContext constraintValidatorContext){
        List<String> roleList = List.of("USER", "ADMIN");
        return roleList.contains(inputRole);
    }

}
