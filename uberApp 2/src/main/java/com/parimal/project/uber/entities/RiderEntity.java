package com.parimal.project.uber.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "rider")
@AllArgsConstructor
@Data
public class RiderEntity extends UserSuperEntity{       // Rider is the customer.

}
