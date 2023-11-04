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
    private long id;

    private Long quantity;

    private long product_id;

    private Order order;
}
