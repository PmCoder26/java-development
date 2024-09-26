package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.annotations;


import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.aspects.LoggingAspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface MyLogging {

}
