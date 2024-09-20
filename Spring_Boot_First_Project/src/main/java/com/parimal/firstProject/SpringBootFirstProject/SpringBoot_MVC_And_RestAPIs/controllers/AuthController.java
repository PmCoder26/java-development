package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.controllers;


import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.dto.*;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.services.AuthService;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.services.JWTService;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/auth")
@AllArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @GetMapping
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping(path = "/signup")
    public UserDTO signUp(
            @RequestBody(required = true)
            SignUpDTO signUpDTO
    ){
        UserDTO userDTO = userService.signUp(signUpDTO);
        return userDTO;
    }

    @PostMapping(path = "/signin")
    public TokenDTO login(
            @RequestBody(required = true)
            LoginDTO loginDTO,
            HttpServletResponse response
    ){
        TokenDTO token = authService.logIn(loginDTO);
        Cookie cookie = new Cookie("token", token.getToken());
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return token;
    }

    @PostMapping(path = "/signin2")
    public LoginResponseDTO login2(
            @RequestBody(required = true)
            LoginDTO loginDTO,
            HttpServletResponse response
    ){
        LoginResponseDTO loginResponseDTO = authService.login2(loginDTO);
        Cookie refreshTokenCookie = new Cookie("refreshToken", loginResponseDTO.getRefreshToken());
        Cookie accessTokenCookie = new Cookie("accessToken", loginResponseDTO.getAccessToken());
        refreshTokenCookie.setHttpOnly(true);
        accessTokenCookie.setHttpOnly(true);
        response.addCookie(refreshTokenCookie);
        response.addCookie(accessTokenCookie);
        return loginResponseDTO;
    }

    @PostMapping(path = "/refresh")
    public LoginResponseDTO refresh(HttpServletRequest request){
        String refreshToken = Arrays.stream(request.getCookies())
                .filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .orElseThrow(() -> new AuthenticationServiceException("Refresh token not found!"))
                .getName();
        return authService.refreshToken(refreshToken);
    }

}
