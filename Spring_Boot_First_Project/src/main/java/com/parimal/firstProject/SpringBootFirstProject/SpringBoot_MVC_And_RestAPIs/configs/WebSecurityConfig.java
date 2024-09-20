package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.configs;


import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.entities.enums.Permission;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.filters.JwtAuthFilter;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.handlers.OAuth2SuccessHandler;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.services.JWTService;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.services.UserService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.entities.enums.Role.ADMIN;

@Configuration
@EnableWebSecurity      // telling to the spring that we want to configure
                        // the spring security filter chain that 'spring security has'
@AllArgsConstructor
@EnableMethodSecurity(securedEnabled = true)       // used when we want to do authorization by using the '@Secured' annotation(in controller part).
                            // This reduces the code for configuring the authorization in the below security filter chain.
public class WebSecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final JWTService jwtService;
    private final UserService userService;
    @Autowired
    private final OAuth2SuccessHandler oAuth2SuccessHandler;

    private final String[] publicRoutes = new String[]{
            "/error", "/auth/**", "/home.html"
    };

    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .formLogin(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        /* tells that these request are public, hence anyone can access/see.
                           '**' means permitting all the routes under the employees2(here).
                           e.g.,  /employees2/getEmployeeById, /employees2/getGetAllEmployees, etc.
                       w */
//                        .requestMatchers("/employees2/**", "/home.html").permitAll()
                        .requestMatchers("/auth/**").permitAll()
//              authorization-part code consisting of role based and authority based authorization.
//                                .requestMatchers(publicRoutes).permitAll()
//                                .requestMatchers("/departments/**").hasRole(ADMIN.name())
//                                .requestMatchers(HttpMethod.POST, "/updateEmployeeNameById/**").hasAuthority(Permission.EMPLOYEE_UPDATE.name())
                        // if the user has the ADMIN role then he is permitted to visit the path or access the data.
//                        .requestMatchers ("/departments/**").hasAnyRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .oauth2Login(oauthConfig ->
                        oauthConfig.failureUrl("/login?error=true")
                                .successHandler(oAuth2SuccessHandler)
                )
                .csrf(csrfConfig -> csrfConfig.disable());      // need to disable for signUp purpose, also the Post request doesn't work due to enable.
        return httpSecurity.build();
    }



    // comment out the @Service of 'UserService' class to use the below code, else error will occur.
//    @Bean
//    UserDetailsService inMemoryUserDetailService(){
//        UserDetails normalUser = User
//                .withUsername("Rohit")
//                .password(passwordEncoder().encode("userPassword"))
//                .roles("USER")
//                .build();
//        UserDetails adminUser = User
//                .withUsername("Rohan")
//                .password(passwordEncoder().encode("adminPassword"))
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(normalUser, adminUser);
//    }

    @Bean
    AuthenticationManager getAuthenticationManager(AuthenticationConfiguration authConfig) throws Exception{
        return authConfig.getAuthenticationManager();
    }

}