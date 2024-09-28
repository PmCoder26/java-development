package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.aspects;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.aspectj.lang.JoinPoint;

@Aspect
@Slf4j
@Component
public class LoggingAspect {

    /*
        syntax for pointcut: "point_cut_type(return_type method_name(parameter))"

        return_type: of the join point method.
        (..): indicates that the join point method with any parameters.

        -- if you have multiple methods with same name under 'services' package then use '*'.
        e.g.: execution(* com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.services.*.orderPackage(..))
    */

    @Before(value = "execution(* orderPackage(..))")         // the string passed in the 'value' is the pointcut.
    public void beforeOrderPackage(){
        log.info("Before orderPackage() called from LoggingAspect");
    }

    // joint point for the specific pointcut method.
    @Before(value = "execution(* com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.services.impl.ShipmentServiceImpl.orderPackage(..))")
    public void beforeOrderPackageSpecific(){
        log.info("Before orderPackage() specific, called from Logging Aspect");
    }

    // join point for the specific method with the all mentioned fields.
    @Before(value = "execution(String com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.services.impl.ShipmentServiceImpl.sampleMethod(Long))")
    public void sampleMethodAspect(JoinPoint joinPoint){
        log.info("Before sampleMethod() specific, called from sampleMethodAspect()");
        log.info("Signature: " + joinPoint.getSignature().toString());
        log.info("Kind: " + joinPoint.getKind());
    }
    @Before(value = "execution(String com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.services.impl.ShipmentServiceImpl.sampleMethod(Long))")
    public void sampleMethodAspect2(JoinPoint joinPoint){
        log.info("Before sampleMethod() specific, called from sampleMethodAspect2()");
        log.info("Signature: " + joinPoint.getSignature().toString());
        log.info("Kind: " + joinPoint.getKind());
    }

    /*
            Use within() when you want to limit the advice to a particular class or
            package, without focusing on specific methods. This pointcut applies to any
            join point within the com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs package, including
            methods, fields, and constructor
     */

    // '..*' specifies for any package in 'SpringBoot_MVC_And_RestAPIs' or the subpackages of packages in the 'SpringBoot_MVC_And_RestAPIs'.
    @Before(value = "within(com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs..*)")
    public void beforeAllMethodsInThePackage(){
        log.info("Called before any method inside the given package, calling from AspectLogging");
    }

    /*
            Use @annotation() when you want to apply advice to methods annotated with a
            particular annotation. You can also create your custom annotations for this
            pointcut.
     */

    @Before(value = "@annotation(com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.annotations.MyLogging)")
    public void beforeMyLoggingAnnotationCall(){
        log.info("Before my custom annotation '@MyLogging' call.");
    }

    /*
            The pointcut is defined separately using @Pointcut and then referenced by
            the advice annotations.
     */
    @Pointcut(value = "execution(* com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.services.impl.ShipmentServiceImpl.orderPackage(..))")
    public void myLoggingAspectPointCut() {}

    @After(value = "myLoggingAspectPointCut()")
    public void aspectMethodUsingThePointCutMethod(){
        log.info("Calling from the method: myLoggingAspectPointCut()");
    }

    // This is similar to @After, but it’s run only after a normal execution of the
    // method. This can also be used to access the returning object.
    @AfterReturning(value = "myLoggingAspectPointCut()", returning = "returnedObject")
    public void afterReturningMethod(JoinPoint joinPoint, Object returnedObject){
        log.info("afterReturning method called!");
        log.info("JoinPoint : " + joinPoint.getSignature());
        log.info("Returned Object: " + returnedObject);
    }

    // This is similar to @After, but it’s run only after an exception is thrown
    // while executing the method.
    @AfterReturning(value = "myLoggingAspectPointCut()")
    public void afterThrowingMethod(JoinPoint joinPoint){
        log.info("afterThrowing method called!");
        log.info("JoinPoint : " + joinPoint.getSignature());
    }


}
