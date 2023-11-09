package com.lotfi.orderservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@Builder
public class OrderDto {

    private String id;
    private BigDecimal totalPrice;
    private String status;
    private ZonedDateTime shipped;
    private PaymentDto payment;
    private String shipmentAddress;
    private Set<OrderItemDto> orderItems;
}
