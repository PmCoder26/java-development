package com.parimal.e_wholesaler.order_service.services;

import com.parimal.e_wholesaler.order_service.dtos.OrderItemRequestDTO;
import com.parimal.e_wholesaler.order_service.dtos.OrderItemResponseDTO;
import com.parimal.e_wholesaler.order_service.entities.OrderItemEntity;
import com.parimal.e_wholesaler.order_service.repositories.OrderItemEntityRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemEntityRepository orderItemEntityRepository;
    private final ModelMapper modelMapper;

    public OrderItemResponseDTO addOrderItem(OrderItemRequestDTO requestDTO) {
        OrderItemEntity toSave = modelMapper.map(requestDTO, OrderItemEntity.class);
        OrderItemEntity saved = orderItemEntityRepository.save(toSave);
        return modelMapper.map(saved, OrderItemResponseDTO.class);
    }

}
