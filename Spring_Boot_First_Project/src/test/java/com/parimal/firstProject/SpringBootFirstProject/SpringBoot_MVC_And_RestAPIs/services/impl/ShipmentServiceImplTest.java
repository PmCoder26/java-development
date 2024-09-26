package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.services.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShipmentServiceImplTest {

    @Autowired
    private ShipmentServiceImpl shipmentService;

    @Test
    void orderPackageTest(){
        shipmentService.orderPackage(1L);
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