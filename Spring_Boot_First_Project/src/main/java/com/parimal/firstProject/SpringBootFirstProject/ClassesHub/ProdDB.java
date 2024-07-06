package com.parimal.firstProject.SpringBootFirstProject.ClassesHub;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
@ConditionalOnProperty(name = "deploy.env", havingValue = "production")         // if the condition satisfies from the application.properties, then load this bean
public class ProdDB  implements DB{

    @PostConstruct
    public void beforeBeanUse(){
        System.out.println("Printing the ProdDB before use");
    }

    @PreDestroy
    public void beforeBeanDestroy(){
        System.out.println("Printing the ProdDB before bean destroy");
    }

    public String getData(){
        return "ProdDB";
    }
}
