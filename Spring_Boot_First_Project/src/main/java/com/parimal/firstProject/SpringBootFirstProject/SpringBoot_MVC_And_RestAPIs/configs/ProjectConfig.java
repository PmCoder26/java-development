package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.configs;


import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.auth.AuditorAwareImplementation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ProjectConfig {
    @Bean
    @Scope(scopeName = "singleton")
    AuditorAwareImplementation getAuditorAwareImplementation(){
        return new AuditorAwareImplementation();
    }
}
