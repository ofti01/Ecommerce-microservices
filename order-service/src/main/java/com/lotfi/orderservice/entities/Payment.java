package com.lotfi.orderservice.entities;

import com.lotfi.orderservice.entities.enums.PaymentStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("payment")
public class Payment {

    @Id
    private String id;

    private String paypalPaymentId;

    private PaymentStatus status;

    private Order order;

    public Payment(String paypalPaymentId, PaymentStatus status, Order order) {
        this.paypalPaymentId = paypalPaymentId;
        this.status = status;
        this.order = order;
    }
}
