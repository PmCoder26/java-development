package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.advices;


import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data       // generates getters and setters.
@Builder    // This Lombok annotation produces complex builder APIs for your class.
            // It allows you to create an instance of ApiError using a fluent API
public class ApiError {
    private HttpStatus status;
    private String message;
    // further modifications.
    private List<String> subErrors;
}
