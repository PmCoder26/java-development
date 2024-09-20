package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.advices;


import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.exceptions.ResourceNotFoundException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

// using the concept of the global exception handlers
// hence, these handlers are available for all the controllers and service layers of this project.
@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(NoSuchElementException.class)
//    public ResponseEntity<String> handleResourceNotFound(){
//        return new ResponseEntity<>("Resource not found for the given data", HttpStatus.NOT_FOUND);
//    }

    // using the custom exception and the ApiError class.
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFound(ResourceNotFoundException e){
        ApiError error = ApiError.builder()
                .message(e.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .build();
        return buildErrorResponseEntity(error);
    }

    private ResponseEntity<ApiResponse> buildErrorResponseEntity(ApiError error) {
        return new ResponseEntity<>(new ApiResponse<>(error), error.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleInternalServerError(Exception e){
        ApiError error = ApiError.builder()
                .message(e.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleInputValidationError(MethodArgumentNotValidException e){
        List<String> errors = e
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(error ->
                        error.getDefaultMessage()
                )
                .collect(Collectors.toList());
        ApiError error = ApiError.builder()
                .subErrors(errors)
                .message("Input validation failed")
                .status(HttpStatus.BAD_REQUEST)
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError> handleAuthenticationError(AuthenticationException e){
        ApiError error = new ApiError.ApiErrorBuilder()
                .message(e.getLocalizedMessage())
                .status(HttpStatus.UNAUTHORIZED)
                .build();
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(JwtException.class)       // this will work if the context of this exception is in dispatc her servlet.
    public ResponseEntity<ApiError> handleJWTError(JwtException e){
        ApiError error = new ApiError.ApiErrorBuilder()
                .message(e.getLocalizedMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiError> handleAccessDeniedError(Exception e){
        ApiError error = ApiError.builder()
                .message(e.getLocalizedMessage())
                .status(HttpStatus.UNAUTHORIZED)
                .build();
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

}
