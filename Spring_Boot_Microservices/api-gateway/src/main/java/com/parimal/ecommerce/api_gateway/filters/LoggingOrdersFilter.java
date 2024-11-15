package com.parimal.ecommerce.api_gateway.filters;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import static reactor.core.publisher.Mono.fromRunnable;

@Component
@Slf4j
//  Note - don't forget to register this filter in the application.yml file. As the filter is not registered for a specific route,
//         so it won't work.
public class LoggingOrdersFilter extends AbstractGatewayFilterFactory<LoggingOrdersFilter.ConfigClass> {

    public LoggingOrdersFilter(){
        super(ConfigClass.class);
    }

    @Override
    public GatewayFilter apply(ConfigClass config) {        // useful when you want to process more variables/arguments.
        return (exchange, chain) -> {
            log.info("Order Filter Pre: {}", exchange.getRequest().getURI());
            return chain
                    .filter(exchange)
                    .then(fromRunnable(() -> {
                        log.info("Order Filter Logging Post: {}", exchange.getResponse().getStatusCode());
                    }));
        };
    }


    public static class ConfigClass {

    }


}
