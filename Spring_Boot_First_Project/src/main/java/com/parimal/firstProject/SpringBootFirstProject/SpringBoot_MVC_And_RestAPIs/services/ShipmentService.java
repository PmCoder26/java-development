package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.services;


public interface ShipmentService {
    String orderPackage(Long orderId);

    String trackPackage(Long orderId);
}
