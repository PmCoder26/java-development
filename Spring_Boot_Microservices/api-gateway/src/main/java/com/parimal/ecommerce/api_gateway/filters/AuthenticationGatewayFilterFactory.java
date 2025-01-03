package com.parimal.ecommerce.api_gateway.filters;

import com.parimal.ecommerce.api_gateway.services.JwtService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class AuthenticationGatewayFilterFactory extends AbstractGatewayFilterFactory<AuthenticationGatewayFilterFactory.ConfigClass> {

    private final JwtService jwtService;

    public AuthenticationGatewayFilterFactory(JwtService jwtService){
        super(ConfigClass.class);
        this.jwtService = jwtService;
    }

    @Override
    public GatewayFilter apply(ConfigClass configClass){
        return (exchange, chain) -> {
//            String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
//            log.info(authHeader);
//            if (authHeader == null) {
//                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
////               Means that as the authentication header is null, there is not jwt token for authentication,
////                so don't let the request further pass to other (filters after this filter) or (downstream services) .
//                return exchange.getResponse().setComplete();
//            }
//            String token = authHeader.split("Bearer ")[1];
//            Long userId = jwtService.getUserIdFromToken(token);
            // now modifying the requests called mutating the request.
            exchange.getRequest()
                    .mutate()
//                    .header("X-User-Id", userId.toString())
                    .build();
            return chain.filter(exchange);
        };
    }

    @Data
    public static class ConfigClass {
        private boolean isEnabled;
    }

}
