package com.lotfi.orderservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class OrderItemDto {
    private String id;
    private Long quantity;
    private String productId;
    private OrderDto orderdto;
}
