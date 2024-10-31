package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.services.impl;


import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.annotations.MyLogging;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.services.ShipmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ShipmentServiceImpl implements ShipmentService {

    @Override
    public String orderPackage(Long orderId) {
        try{
            log.info("Processing the order...");
            Thread.sleep(1000);
            return "Ordered package";
        } catch (Exception e){
            log.error("order package error", e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public String trackPackage(Long orderId) {
        try{
            log.info("Tracking the package...");
            Thread.sleep(1000);
            return "Tracked the package";
        } catch (Exception e){
            log.error("track package error", e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    public String sampleMethod(Long id){
        try{
            log.info("Sample method running...");
            Thread.sleep(1000);
            return "Sample method";
        } catch (Exception e){
            log.error("sample method error", e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    @MyLogging
    public void annotationTestMeth1(){
        try{
            log.info("annotationTestMeth1 running...");
            Thread.sleep(1000);
        } catch (Exception e){
            log.error("test method_1 error", e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    @MyLogging
    public void annotationTestMeth2(){
        try{
            log.info("annotationTestMeth2 running...");
            Thread.sleep(1000);
        } catch (Exception e){
            log.error("test method_2 error", e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    @MyLogging
    public void annotationTestMeth3(){
        try{
            log.info("annotationTestMeth3 running...");
            Thread.sleep(1000);
        } catch (Exception e){
            log.error("test method_3 error", e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }
}
