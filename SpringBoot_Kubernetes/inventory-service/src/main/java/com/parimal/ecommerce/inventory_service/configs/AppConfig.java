package com.parimal.ecommerce.inventory_service.configs;


import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Capability;
import feign.micrometer.MicrometerCapability;
import io.micrometer.core.instrument.MeterRegistry;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }

    @Bean
    public RestClient getRestClient(){
        return RestClient.builder().build();
    }

    @Bean
    public ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }

    @Bean
    public Capability getCapability(final MeterRegistry meterRegistry) {
        return new MicrometerCapability(meterRegistry);
    }

}
