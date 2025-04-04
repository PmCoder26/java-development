package com.parimal.ecommerce.inventory_service.consumers;


//import com.parimal.ecommerce.order_service.events.OrderItemAddedEvent;        // before using the schema registry.
import com.parimal.ecommerce.event.OrderItemAddedEvent;                         // after using the schema registry.
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrdersKafkaConsumer {

    @KafkaListener(topics = {"my-new-topic"}, groupId = "inventory-service")
    // after using the above annotation spring boot AOP will work, it will fetch the messages,
    // and inject in the below method.
    public void handleOrderTopic1(String message) {
        log.info("Message received from my-new-topic to listener-1: {}", message);
    }

    // to create multiple consumers just re-write the above with different method names.
    @KafkaListener(topics = {"my-new-topic"}, groupId = "inventory-service")
    // after using the above annotation spring boot AOP will work, it will fetch the messages,
    // and inject in the below method.
    public void handleOrderTopic2(String message) {
        log.info("Message received from my-new-topic to listener-2: {}", message);
    }

    @KafkaListener(topics = {"my-new-topic"}, groupId = "inventory-service")
    // after using the above annotation spring boot AOP will work, it will fetch the messages,
    // and inject in the below method.
    public void handleOrderTopic3(String message) {
        log.info("Message received from my-new-topic to listener-3: {}", message);
    }

    @KafkaListener(topics = {"order-item-added-topic"})
    public void handleOrderItemAddedTopic(OrderItemAddedEvent itemAddedEvent) {
        log.info("Inside the order item added topic consumer with data: {}", itemAddedEvent);
    }

}
