package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.exceptions;




public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message){
        super(message);
    }
}
