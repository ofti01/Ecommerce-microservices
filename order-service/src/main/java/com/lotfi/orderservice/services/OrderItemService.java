package com.lotfi.orderservice.services;

import com.lotfi.orderservice.dtos.OrderItemDto;
import com.lotfi.orderservice.entities.Order;
import com.lotfi.orderservice.entities.OrderItem;
import com.lotfi.orderservice.repositories.OrderItemRepository;
import com.lotfi.orderservice.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final WebClient webClient;
   // private final ProductRepository productRepository;
    public List<OrderItemDto> findAll() {
        log.debug("Request to get all OrderItems");
        return this.orderItemRepository.findAll()
                .stream()
                .map(OrderItemService::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public OrderItemDto findById(String id) {
        log.debug("Request to get OrderItem : {}", id);
        return this.orderItemRepository.findById(id).map(OrderItemService::mapToDto).orElse(null);
    }

    public void delete(String id) {
        log.debug("Request to delete OrderItem : {}", id);
        this.orderItemRepository.deleteById(id);
    }

    public OrderItemDto create(OrderItemDto orderItemDto) {
        log.debug("Request to create OrderItem : {}", orderItemDto);
        Order order =
                this.orderRepository.findById(orderItemDto.getOrderdto().getId())
                        .orElseThrow(
                                () ->
                                        new IllegalStateException("The Order does not exist!")
                        );
        Mono<Boolean> result = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .host("http://localhost:8082/products")
                        .path("/check/{id}")
                        .queryParam("id", orderItemDto.getProductId())
                        .build(1))
                .retrieve()
                .bodyToMono(Boolean.class);
        if (result.block() == false)
            throw new IllegalStateException("desn't exist product");
        else
            return mapToDto(
                this.orderItemRepository.save(
                        new OrderItem(
                                orderItemDto.getQuantity(),
                                orderItemDto.getProductId(),
                                order
                        )));
    }

    public static OrderItem mapFromDto(OrderItemDto orderItemDto){
        if(orderItemDto == null) return null;
        else
            return OrderItem.builder()
                    .id(orderItemDto.getId())
                    .product_id(orderItemDto.getProductId())
                    .quantity(orderItemDto.getQuantity())
                    .order(OrderService.mapFromDto(orderItemDto.getOrderdto()))
                    .build();
    }

    public static OrderItemDto mapToDto(OrderItem orderItem){
        if(orderItem == null) return null;
        else
            return OrderItemDto.builder()
                    .id(orderItem.getId())
                    .productId(orderItem.getProduct_id())
                    .quantity(orderItem.getQuantity())
                    .orderdto(OrderService.mapToDto(orderItem.getOrder()))
                    .build();
    }
}
