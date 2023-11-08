package com.lotfi.orderservice.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("order_item")
public class OrderItem {

    @Id
    private String id;

    private int quantity;

    private String product_id;

    private Order order;

    public OrderItem(int quantity, String product_id, Order order) {
        this.quantity = quantity;
        this.product_id = product_id;
        this.order = order;
    }
}
