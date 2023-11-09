package com.lotfi.orderservice.repositories;

import com.lotfi.orderservice.entities.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentReposiory extends MongoRepository<Payment, String> {
}
