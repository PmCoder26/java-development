package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ValidationAspect {

    @Pointcut(value = "execution(* com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.services.impl.ShipmentServiceImpl.orderPackage(..))")
    public void myLoggingAspectPointCut() {}

    // This annotation allows us to take actions either before or after a JoinPoint
    // method is run. We can use it to return a custom value or throw an exception
    // or simply let the method run and return normally.
    @Around(value = "myLoggingAspectPointCut()")
    public Object validateOrderId(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Long orderId = (Long) args[0];
        if(orderId > 0) return joinPoint.proceed();
        return "Cannot call with negative order id";
    }

}
