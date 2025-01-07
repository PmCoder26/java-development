package com.parimal.ecommerce.order_service.services;

import com.parimal.ecommerce.event.OrderItemAddedEvent;
import com.parimal.ecommerce.order_service.dtos.OrderItemRequestDTO;
import com.parimal.ecommerce.order_service.dtos.OrderItemResponseDTO;
import com.parimal.ecommerce.order_service.entities.OrderItemEntity;
//import com.parimal.ecommerce.order_service.events.OrderItemAddedEvent;    // before using kafka schema registry
import com.parimal.ecommerce.order_service.repositories.OrderItemEntityRepository;  // for using kafka schema registry
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemEntityRepository orderItemEntityRepository;
    private final ModelMapper modelMapper;

    @Value(value = "${spring.kafka.topic.order-item-added-topic}")
    private String ORDER_ITEM_ADDED_TOPIC;

    private final KafkaTemplate<Long, OrderItemAddedEvent> kafkaTemplate;

    public OrderItemResponseDTO addOrderItem(OrderItemRequestDTO requestDTO) {
        OrderItemEntity toSave = modelMapper.map(requestDTO, OrderItemEntity.class);
        OrderItemEntity saved = orderItemEntityRepository.save(toSave);
        OrderItemAddedEvent itemAddedEvent = modelMapper.map(saved, OrderItemAddedEvent.class);
        kafkaTemplate.send(ORDER_ITEM_ADDED_TOPIC, itemAddedEvent.getId(), itemAddedEvent);
        return modelMapper.map(saved, OrderItemResponseDTO.class);
    }

}
