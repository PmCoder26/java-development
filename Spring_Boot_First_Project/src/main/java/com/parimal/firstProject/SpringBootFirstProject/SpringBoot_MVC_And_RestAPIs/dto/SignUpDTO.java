package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.dto;


import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.entities.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDTO {
    private String email;
    private String name;
    private String password;
    private List<Role> roles;       // only for demonstration purpose, mentioning in this DTO. But in production ready application, it is not recommended to place here.
}
