package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.advices;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class ApiResponse<T> {

    private T data;
    private ApiError error;

//    @JsonFormat(pattern = "hh:mm:ss, dd:MM:yyyy")     // ignored for the api calls from another server using RestClient
    private LocalDateTime timeStamp;

    public ApiResponse(){
        this.timeStamp = LocalDateTime.now();
    }
    public ApiResponse(T data){
        this();     // calling the default constructor.
        this.data = data;
    }
    public ApiResponse(ApiError error){
        this();
        this.error = error;
    }

}
