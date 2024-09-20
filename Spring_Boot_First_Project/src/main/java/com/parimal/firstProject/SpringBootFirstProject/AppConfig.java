package com.parimal.firstProject.SpringBootFirstProject;


import com.parimal.firstProject.SpringBootFirstProject.ClassesHub.Apple;
import com.parimal.firstProject.SpringBootFirstProject.ClassesHub.Banana;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

    @Bean
    @Scope(scopeName = "prototype")     // for creating multiple beans.
    public Banana getBanana() {
        return new Banana();
    }

}

