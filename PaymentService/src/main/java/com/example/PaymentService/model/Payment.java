package com.example.PaymentService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity(name = "payments")
@Getter
@Setter
public class Payment extends BaseModel {
    private UUID userId;
    private UUID orderId;
    private String transactionId;
    private long amount;
    @Enumerated(EnumType.ORDINAL)
    private PaymentStatus paymentStatus;
}
