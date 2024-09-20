package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.dto;


    // DTO - Data Transfer Object. Used to transfer the data from client to controller and then to the service.

import com.fasterxml.jackson.annotation.JsonProperty;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.annotations.EmployeeRoleValidation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

// using the lombok annotations which automatically configures or creates the getters and setters for all the class data fields.
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    // using the 'validation annotations'
    // for this you have to also use '@Valid' in the request body of the method 'createNewEmployee'
    // so that to tell him to validate.

    private Long id;
    @NotBlank(message = "Field required: name")
    @Size(min = 3, max = 10, message = "Name should be in the range: [3, 10]")
    private String name;

    @Email(message = "Email should be a valid email")
    private String email;

    @Max(value = 60, message = "Age of the employee can't be greater than 80")
    @Min(value = 18, message = "Age of the employee can't be less than 18")
    private Integer age;

    @PastOrPresent(message = "The data of joining must be past date or present date")
    private LocalDate dateOfJoining;

    @NotBlank(message = "The role of the employee cannot be blank")
    @Pattern(regexp = "^(ADMIN|USER)$", message = "Role of the employee can be either USER or ADMIN")         // using the built-in annotation.
    @EmployeeRoleValidation(message = "The role of the employee must be either USER or ADMIN")                  // using the custom annotation.
    private String role;

    @AssertTrue(message = "Employee should be active")
    @JsonProperty(value = "isActive")
    private Boolean isActive;

    @Positive(message = "The salary of the employee cannot be negative or zero")
    private Double salary = (double) 0;

}
