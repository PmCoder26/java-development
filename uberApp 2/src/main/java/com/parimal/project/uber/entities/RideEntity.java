package com.parimal.project.uber.entities;


import com.parimal.project.uber.entities.enums.PaymentMethod;
import com.parimal.project.uber.entities.enums.RideStatus;
import jakarta.persistence.*;
import jakarta.persistence.Column;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.DateTimeException;
import java.time.LocalDateTime;

@Entity(name = "ride")
public class RideEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @jakarta.persistence.Column(columnDefinition = "Geometry(Point, 4326)")
    private Point pickupLocation;

    @Column(columnDefinition = "Geometry(Point, 4326)")
    private Point dropOffLocation;

    @CreationTimestamp
    private LocalDateTime createdTime;      // when driver accepts the ride.

    @ManyToOne(fetch = FetchType.LAZY)
    private RiderEntity rider;

    @ManyToOne(fetch = FetchType.LAZY)
    private DriverEntity driver;

    @Enumerated(value = EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private RideStatus rideRequestStatus;

    private Double fare;
    // when the driver enters the OTP and then hence start the ride, for this we use the startedAt and endedAt;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;

}
