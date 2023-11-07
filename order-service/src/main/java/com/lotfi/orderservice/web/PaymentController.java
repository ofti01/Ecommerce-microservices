package com.lotfi.orderservice.web;

import com.lotfi.orderservice.dtos.PaymentDto;
import com.lotfi.orderservice.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;
    @GetMapping
    public List<PaymentDto> findAll() {
        return this.paymentService.findAll();
    }
    @GetMapping("/{id}")
    public PaymentDto findById(@PathVariable String id) {
        return this.paymentService.findById(id);
    }
    @PostMapping
    public PaymentDto create(@RequestBody PaymentDto orderItemDto) {
        return this.paymentService.create(orderItemDto);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        this.paymentService.delete(id);
    }
}

