package com.lotfi.orderservice.web;

import com.lotfi.orderservice.dtos.OrderItemDto;
import com.lotfi.orderservice.services.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orderitems")
public class OrderItemController {

    private final OrderItemService itemService;
    @GetMapping
    public List<OrderItemDto> findAll() {
        return this.itemService.findAll();
    }
    @GetMapping("/{id}")
    public OrderItemDto findById(@PathVariable String id) {
        return this.itemService.findById(id);
    }
    @PostMapping
    public OrderItemDto create(@RequestBody OrderItemDto orderItemDto) {
        return this.itemService.create(orderItemDto);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        this.itemService.delete(id);
    }
}
