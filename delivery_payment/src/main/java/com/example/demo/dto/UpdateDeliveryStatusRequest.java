package com.example.demo.dto;

import com.example.demo.model.Delivery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDeliveryStatusRequest {
    @NotNull(message = "Delivery status is required")
    private Delivery.DeliveryStatus status;

    private String trackingNumber;
}
