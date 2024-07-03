package com.parimal.firstProject.SpringBootFirstProject.ClassesHub;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "deploy.env", havingValue = "development")        // if the condition satisfies from the application.properties, then load this bean.
public class DevDB implements DB{
    public String getData(){
        return "DevDB";
    }

    @PostConstruct
    public void beforeBeanUse(){
        System.out.println("Printing the DevDB before use");
    }

    @PreDestroy
    public void beforeBeanDestroy(){
        System.out.println("Printing the DevDB before bean destroy");
    }

}
