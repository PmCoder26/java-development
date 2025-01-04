package com.parimal.ecommerce.order_service.configs;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic getNewTopic() {
        return new NewTopic("my-new-topic", 3, (short) 1);
    }

}
