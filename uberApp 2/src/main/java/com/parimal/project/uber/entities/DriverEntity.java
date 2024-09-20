package com.parimal.project.uber.entities;


import jakarta.persistence.*;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.locationtech.jts.geom.Point;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "driver")
@AllArgsConstructor
public class DriverEntity extends UserSuperEntity{

    private Boolean available;

    @Column(columnDefinition = "Geometry(Point, 4326)")        // defines that we are dealing with the Earth's geometry, as we use maps.
    Point currentLocation;


}
