package com.lotfi.orderservice.entities;

import com.lotfi.orderservice.entities.enums.OrderStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("order")
public class Order {

    @Id
    private String id;

    private BigDecimal totalPrice;

    private OrderStatus status;

    private ZonedDateTime shipped;

    private Payment payment;

    private String shipmentAddress;

    private Set<OrderItem> orderItems;

}
