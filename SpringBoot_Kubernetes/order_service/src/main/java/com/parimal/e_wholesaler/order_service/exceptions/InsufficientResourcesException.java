package com.parimal.e_wholesaler.order_service.exceptions;

public class InsufficientResourcesException extends RuntimeException {
    public InsufficientResourcesException(String message){
        super(message);
    }
}
