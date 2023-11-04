package com.lotfi.orderservice.repositories;

import com.lotfi.orderservice.entities.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}
