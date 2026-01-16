package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryRequest {
    @NotNull(message = "Order ID is required")
    private Long orderId;

    @NotBlank(message = "Shipping address is required")
    private String shippingAddress;

    private String carrier;

    private Double shippingCost;

    @NotNull(message = "Estimated delivery date is required")
    @Future(message = "Estimated delivery date must be in the future")
    private LocalDateTime estimatedDeliveryDate;
}
