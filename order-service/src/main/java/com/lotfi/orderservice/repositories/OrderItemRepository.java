package com.lotfi.orderservice.repositories;

import com.lotfi.orderservice.entities.OrderItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderItemRepository extends MongoRepository<OrderItem, String> {
}
