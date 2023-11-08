package com.lotfi.orderservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class OrderItemDto {
    private String id;
    private int quantity;
    private String productId;
    private OrderDto orderdto;

    public OrderItemDto(int quantity, String productId) {
        this.quantity = quantity;
        this.productId = productId;
    }
}
