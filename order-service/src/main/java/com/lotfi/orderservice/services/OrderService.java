package com.lotfi.orderservice.services;

import com.lotfi.orderservice.dtos.OrderDto;
import com.lotfi.orderservice.entities.Order;
import com.lotfi.orderservice.entities.enums.OrderStatus;
import com.lotfi.orderservice.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;


    public List<OrderDto> findAll() {
        log.debug("request to get all order");
        return orderRepository.findAll().stream()
                .map(OrderService::mapToDto)
                .collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public OrderDto findById(String id) {
        log.debug("Request to get Category : {}", id);
        return mapToDto(this.orderRepository.findById(id).orElse(null));

    }


    public OrderDto create(OrderDto orderDto) {
        log.debug("Request to create Order : {}", orderDto);
        return mapToDto(this.orderRepository.save(mapFromDto(orderDto)));
    }

    public void delete(String id) {
        log.debug("Request to delete Order : {}", id);
        this.orderRepository.deleteById(id);
    }
    public static OrderDto mapToDto(Order order) {
        // TODO create dtos and complete services
        if(order == null) return null;
        else
            return OrderDto.builder()
                    .id(order.getId())
                    .shipmentAddress(order.getShipmentAddress())
                    .orderItems(order.getOrderItems().stream()
                            .map(OrderItemService::mapToDto)
                            .collect(Collectors.toSet()))
                    .payment(PaymentService.mapToDto(order.getPayment()))
                    .shipped(order.getShipped())
                    .status(order.getStatus().name())
                    .totalPrice(order.getTotalPrice())
                    .build();
    }
    public static Order mapFromDto(OrderDto orderDto){
        if(orderDto == null) return null;
        else
            return Order.builder()
                    .id(orderDto.getId())
                    .shipmentAddress(orderDto.getShipmentAddress())
                    .orderItems(orderDto.getOrderItems()
                            .stream()
                            .map(OrderItemService::mapFromDto)
                            .collect(Collectors.toSet()))
                    .payment(PaymentService.mapFromDto(orderDto.getPayment()))
                    .shipped(orderDto.getShipped())
                    .status(OrderStatus.valueOf(orderDto.getStatus()))
                    .totalPrice(orderDto.getTotalPrice())
                    .build();
    }
}
