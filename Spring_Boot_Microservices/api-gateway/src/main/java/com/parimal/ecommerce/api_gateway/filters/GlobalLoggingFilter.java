package com.parimal.ecommerce.api_gateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static reactor.core.publisher.Mono.fromRunnable;


@Component
@Slf4j
public class GlobalLoggingFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("Logging from Global Pre: {}", exchange.getRequest().getURI());
        return chain
                .filter(exchange)
                .then(fromRunnable(() -> {
                    log.info("Logging from the Global Post: {}", exchange.getResponse().getStatusCode());
                }));
    }

    @Override
    public int getOrder() {     // defines and return the order of this filter.
        return 1;
    }
}
