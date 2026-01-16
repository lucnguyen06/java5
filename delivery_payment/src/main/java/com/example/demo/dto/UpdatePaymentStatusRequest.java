package com.example.demo.dto;

import com.example.demo.model.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePaymentStatusRequest {
    @NotNull(message = "Payment status is required")
    private Payment.PaymentStatus status;

    private String transactionId;
}
