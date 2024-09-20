package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class LoginResponseDTO {

    private Long id;
    private String accessToken;
    private String refreshToken;

}
