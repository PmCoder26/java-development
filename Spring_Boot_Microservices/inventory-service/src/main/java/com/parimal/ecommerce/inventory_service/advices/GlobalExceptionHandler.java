package com.parimal.ecommerce.inventory_service.advices;


import com.parimal.ecommerce.inventory_service.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFound(ResourceNotFoundException e) {
        ApiError apiError = ApiError.builder()
                .message(e.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .build();
        return buildApiErrorResponse(apiError);
    }

    private ResponseEntity<ApiResponse> buildApiErrorResponse(ApiError apiError){
        return new ResponseEntity<>(new ApiResponse(apiError), apiError.getStatus());
    }

}
