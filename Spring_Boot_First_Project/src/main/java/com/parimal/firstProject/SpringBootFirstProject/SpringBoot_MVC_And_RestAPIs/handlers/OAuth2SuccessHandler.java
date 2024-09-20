package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.handlers;

import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.entities.UserEntity;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.services.JWTService;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UserService userService;
    private final JWTService jwtService;

    @Value("${deploy.env}")
    String deployEnv;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request, HttpServletResponse response, Authentication authentication
    ) throws IOException, ServletException {
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        DefaultOAuth2User oAuth2User = (DefaultOAuth2User) token.getPrincipal();
        logger.info(oAuth2User.getAttribute("email"));
        logger.info(oAuth2User.getAttribute("name"));
        UserEntity user = userService.getUserByEmail(oAuth2User.getAttribute("email"));

        if(user == null){
            UserEntity newUser = UserEntity.builder()
                    .name(oAuth2User.getAttribute("name"))
                    .email(oAuth2User.getAttribute("email"))
                    .build();
            user = userService.save(newUser);
        }

        String accessToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        Cookie accessTokenCookie = new Cookie("accessToken", accessToken);
        Cookie refreshTokenCookie = new Cookie("refreshTokenCookie", refreshToken);
        accessTokenCookie.setHttpOnly(true);
        accessTokenCookie.setSecure("production".equals(deployEnv));
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setSecure("production".equals(deployEnv));
        response.addCookie(accessTokenCookie);
        response.addCookie(refreshTokenCookie);

        String frontEndUrl = "http://localhost:8080/home.html?token=" + accessToken;
        // now redirecting to the above url after authentication.
        getRedirectStrategy().sendRedirect(request, response, frontEndUrl);


    }

}
