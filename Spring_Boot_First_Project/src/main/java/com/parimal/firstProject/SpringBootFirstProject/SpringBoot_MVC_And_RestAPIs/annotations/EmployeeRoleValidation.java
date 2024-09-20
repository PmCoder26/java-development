package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.annotations;

import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/* Here, when we use this custom annotation, the string which will be
    passed to the 'EmployeeRoleValidator', then it will call the 'isValid'
    function of the 'EmployeeRoleValidator' class and check the string.
    Hence, it validates.
 */
@Retention(RetentionPolicy.RUNTIME)     // Indicates how long annotations with the annotated interface are to be retained.
@Target(ElementType.FIELD)              // targets for the field values only.
@Constraint(validatedBy = EmployeeRoleValidator.class)
public @interface EmployeeRoleValidation {
    String message() default "";

    java.lang.Class<?>[] groups() default {};

    java.lang.Class<? extends jakarta.validation.Payload>[] payload() default {};

}
