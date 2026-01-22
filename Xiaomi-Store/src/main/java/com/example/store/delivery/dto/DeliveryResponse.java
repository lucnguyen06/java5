package com.example.store.delivery.dto;

import com.example.store.delivery.model.Delivery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryResponse {
    private Long id;
    private Long orderId;
    private String shippingAddress;
    private String trackingNumber;
    private Delivery.DeliveryStatus status;
    private String carrier;
    private Double shippingCost;
    private LocalDateTime estimatedDeliveryDate;
    private LocalDateTime actualDeliveryDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static DeliveryResponse fromEntity(Delivery delivery) {
        DeliveryResponse response = new DeliveryResponse();
        response.setId(delivery.getId());
        response.setOrderId(delivery.getOrder().getId());
        response.setShippingAddress(delivery.getShippingAddress());
        response.setTrackingNumber(delivery.getTrackingNumber());
        response.setStatus(delivery.getStatus());
        response.setCarrier(delivery.getCarrier());
        response.setShippingCost(delivery.getShippingCost());
        response.setEstimatedDeliveryDate(delivery.getEstimatedDeliveryDate());
        response.setActualDeliveryDate(delivery.getActualDeliveryDate());
        response.setCreatedAt(delivery.getCreatedAt());
        response.setUpdatedAt(delivery.getUpdatedAt());
        return response;
    }
}
