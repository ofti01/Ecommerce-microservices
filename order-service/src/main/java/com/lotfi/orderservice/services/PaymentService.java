package com.lotfi.orderservice.services;

import com.lotfi.orderservice.dtos.OrderItemDto;
import com.lotfi.orderservice.dtos.PaymentDto;
import com.lotfi.orderservice.entities.Order;
import com.lotfi.orderservice.entities.OrderItem;
import com.lotfi.orderservice.entities.Payment;
import com.lotfi.orderservice.entities.enums.PaymentStatus;
import com.lotfi.orderservice.repositories.OrderRepository;
import com.lotfi.orderservice.repositories.PaymentReposiory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

    private final PaymentReposiory paymentRepository;
    private final OrderRepository orderRepository;

    public List<PaymentDto> findAll() {
        log.debug("Request to get all Payments");
        return this.paymentRepository.findAll()
                .stream()
                .map(PaymentService::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PaymentDto findById(String id) {
        log.debug("Request to get Payment : {}", id);
        return this.paymentRepository.findById(id).map(PaymentService::mapToDto).orElse(null);
    }

    public PaymentDto create(PaymentDto paymentDto) {
        log.debug("Request to create Payment : {}", paymentDto);
        Order order =
                this.orderRepository.findById(paymentDto.getOrder().getId())
                        .orElseThrow(
                                () -> new IllegalStateException("The Order does not exist!")
                        );
        return mapToDto(this.paymentRepository.save(
                new Payment(
                        paymentDto.getPaypalPaymentId(),
                        PaymentStatus.valueOf(paymentDto.getStatus()),
                        order
                )
        ));
    }
    public void delete(String id) {
        log.debug("Request to delete Payment : {}", id);
        this.paymentRepository.deleteById(id);
    }

    public static Payment mapFromDto(PaymentDto paymentDto){
        if(paymentDto == null) return null;
        else
            return Payment.builder()
                    .id(paymentDto.getPaypalPaymentId())
                    .status(PaymentStatus.valueOf(paymentDto.getStatus()))
                    .paypalPaymentId(paymentDto.getPaypalPaymentId())
                    .order(OrderService.mapFromDto(paymentDto.getOrder()))
                    .build();
    }

    public static PaymentDto mapToDto(Payment payment){
        if(payment == null) return null;
        else
            return PaymentDto.builder()
                    .id(payment.getPaypalPaymentId())
                    .status(payment.getStatus().name())
                    .paypalPaymentId(payment.getPaypalPaymentId())
                    .order(OrderService.mapToDto(payment.getOrder()))
                    .build();
    }
}
