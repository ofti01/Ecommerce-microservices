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
public class PaymentDto {
    private String id;
    private String paypalPaymentId;
    private String status;
    private OrderDto order;

}
