package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class ShipmentServiceImplTest {

    @Autowired
    private ShipmentServiceImpl shipmentService;

    @Test
    void orderPackageTest(){
        String orderString = shipmentService.orderPackage(-1L);
        log.info("OrderString: " + orderString);
    }

    @Test
    void testSampleMethod(){
        shipmentService.sampleMethod(10L);
    }

    @Test
    void testAnnotationMeth1(){
        shipmentService.annotationTestMeth1();
    }

    @Test
    void testAnnotationMeth2(){
        shipmentService.annotationTestMeth2();
    }

    @Test
    void testAnnotationMeth3 (){
        shipmentService.annotationTestMeth3();
    }


}