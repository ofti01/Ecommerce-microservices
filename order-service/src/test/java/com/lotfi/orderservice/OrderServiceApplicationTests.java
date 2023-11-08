package com.lotfi.orderservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lotfi.orderservice.dtos.OrderDto;
import com.lotfi.orderservice.dtos.OrderItemDto;
import com.lotfi.orderservice.repositories.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class OrderServiceApplicationTests {


    @Autowired
    private static MongoDBContainer mongoDBContainer;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private OrderRepository orderRepository;

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dymDynamicPropertyRegistry) {
        dymDynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }
    @Test
    void shouldCreateOrder() throws Exception {
        OrderDto productRequest = getOrderRequest();
        String productRequestString = objectMapper.writeValueAsString(productRequest);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productRequestString))
                .andExpect(status().isCreated());
        Assertions.assertEquals(1, orderRepository.findAll().size());
    }

    private OrderDto getOrderRequest() {
        OrderItemDto orderItemDto = new OrderItemDto(12,"ef4155");
        Set<OrderItemDto> orderItemDtoList = (Set<OrderItemDto>) new ArrayList<OrderItemDto>();
        orderItemDtoList.add(orderItemDto);
        return OrderDto.builder()
                .id(null)
                .orderItems(orderItemDtoList)
                .payment(null)
                .shipmentAddress("hi 0155")
                .shipped(null)
                .totalPrice(null)
                .build();
    }

}
