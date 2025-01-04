package com.parimal.ecommerce.order_service.exceptions;

public class InsufficientResourcesException extends RuntimeException {
    public InsufficientResourcesException(String message){
        super(message);
    }
}
