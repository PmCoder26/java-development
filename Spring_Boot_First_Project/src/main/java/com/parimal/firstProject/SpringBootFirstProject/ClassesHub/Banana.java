package com.parimal.firstProject.SpringBootFirstProject.ClassesHub;



// use case of the AppConfig class here.
// using the @Bean hence, no need to use the @Component annotation.
// here, developer just take care of object creation using 'new' keyword, but the lifecycle of object is managed by spring.

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class Banana {

    public void eatBanana(){
        System.out.println("Eating a Banana");
    }

    @PostConstruct      // similar to constructor, used to invoke method before the bean is used.
    public void callMethodBeforeBananaUsed(){
        System.out.println("Calling the method before the Banana bean is used");
    }

    @PreDestroy     // used to run method before destroying the bean, usually when the application is terminated.
    public void callMethodBeforeBeanDestroy(){
        System.out.println("Calling the method before destroy of bean");
    }

}
