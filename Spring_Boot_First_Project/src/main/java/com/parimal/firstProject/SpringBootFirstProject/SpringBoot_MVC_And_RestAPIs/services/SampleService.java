package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.services;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SampleService {
    public void meth(){
        log.info("Hello meth");
    }

    @PostConstruct
    public void meth2(){
        System.out.println("Before constructing");
    }

}
