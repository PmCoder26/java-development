package com.parimal.firstProject.SpringBootFirstProject.ClassesHub;

import org.springframework.stereotype.Component;

// without using the AppConfig class, by declaring the Apple class as Bean, and further using the @AutoWired.
@Component
public class Apple {

    public void eatApple(){
        System.out.println("Eating an Apple");
    }

}
