package com.parimal.project.uber.entities;

import jakarta.persistence.*;
import lombok.Data;

@MappedSuperclass
public class UserSuperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    private Double rating;

}
