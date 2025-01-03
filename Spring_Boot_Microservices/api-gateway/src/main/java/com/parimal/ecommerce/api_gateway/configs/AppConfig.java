package com.parimal.ecommerce.api_gateway.configs;


import feign.Capability;
import feign.micrometer.MicrometerCapability;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Capability getCapability(final MeterRegistry meterRegistry) {
        return new MicrometerCapability(meterRegistry);
    }

}
