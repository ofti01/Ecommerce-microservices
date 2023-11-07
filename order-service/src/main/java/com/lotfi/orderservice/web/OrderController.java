package com.lotfi.orderservice.web;

import com.lotfi.orderservice.dtos.OrderDto;
import com.lotfi.orderservice.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    @GetMapping
    public List<OrderDto> findAll() {
        return this.orderService.findAll();
    }

    @GetMapping("/{id}")
    public OrderDto findById(@PathVariable String id) {
        return this.orderService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        this.orderService.delete(id);
    }
}
