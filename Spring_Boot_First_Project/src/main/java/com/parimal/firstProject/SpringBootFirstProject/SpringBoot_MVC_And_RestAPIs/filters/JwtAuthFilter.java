package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.filters;


import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.entities.UserEntity;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.services.JWTService;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.services.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component          // hence, the bean gets created automatically due to constructor dependency injection.
@AllArgsConstructor
/*  Due to this filter, skipping of form-based authentication process is carried out.
    When any request which has to be protected from unauthorized access; due to this
    filter the form based authentication is skipped. If we don't provide this custom
    filter then for every protected path the form login will be required.
*/
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver handlerExceptionResolver;
    private final JWTService jwtService;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {   // we used try-catch and HandlerExceptionResolver because since the Jwt exception wasn't handled by the global exception handler because
                // we are not in the dispatcher servlet, as the global exception handler works in the context of dispatcher servlet
                // and the 'expired token exception or others related Jwt exceptions' wasn't handled.
            final String requestTokenHeader = request.getHeader("Authorization");   // 'Authorization' header contains the token.
            // the token in the header isn't present directly in the form of string
            // it is present in the form of -> 'Bearer token' as an industry standard, e.g. Bearer iurnewcrjdkfjklncjfiwe
            if (requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer")) {
                filterChain.doFilter(request, response);        // hence, do the filtering further.
                return;
            }

            // else.
            String token = requestTokenHeader.split("Bearer ")[1];  // hence, splitting and getting the second part and that is the token.
            Long userId = jwtService.getUserIdFromToken(token);

            // means that perform this 'JwtAuthFilter' filtering for only once. This prevents the filtering through one filter more than one time.
            if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserEntity user = userService.getUserById(userId);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)          // for further process, it provides some web related details.
                );
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            filterChain.doFilter(request, response);
        } catch(Exception e){
            handlerExceptionResolver.resolveException(request, response, null, e);
        }
    }
}
