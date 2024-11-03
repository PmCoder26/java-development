package com.parimal.ecommerce.inventory_service.exceptions;

public class InsufficientResourcesException extends RuntimeException {
    public InsufficientResourcesException(String message){
        super(message);
    }
}
