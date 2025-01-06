package com.parimal.ecommerce.order_service.configs;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class KafkaTopicConfig {

    @Value(value = "${spring.kafka.topic.order-item-added-topic}")
    private String ORDER_ITEM_ADDED_TOPIC;

    @Bean
    public NewTopic getNewTopic() {
        return new NewTopic("my-new-topic", 3, (short) 1);
    }

    @Bean
    public NewTopic getOrderCreatedTopic() {
        return new NewTopic(ORDER_ITEM_ADDED_TOPIC, 3, (short) 1);
    }

}
