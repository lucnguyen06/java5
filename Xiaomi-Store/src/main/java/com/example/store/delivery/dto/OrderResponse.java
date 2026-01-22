package com.example.store.delivery.dto;

import com.example.store.delivery.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private Long id;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private String shippingAddress;
    private Double totalAmount;
    private Order.OrderStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private DeliveryResponse delivery;
    private List<PaymentResponse> payments;

    public static OrderResponse fromEntity(Order order) {
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setCustomerName(order.getCustomerName());
        response.setCustomerEmail(order.getCustomerEmail());
        response.setCustomerPhone(order.getCustomerPhone());
        response.setShippingAddress(order.getShippingAddress());
        response.setTotalAmount(order.getTotalAmount());
        response.setStatus(order.getStatus());
        response.setCreatedAt(order.getCreatedAt());
        response.setUpdatedAt(order.getUpdatedAt());
        
        if (order.getDelivery() != null) {
            response.setDelivery(DeliveryResponse.fromEntity(order.getDelivery()));
        }
        
        if (order.getPayments() != null && !order.getPayments().isEmpty()) {
            response.setPayments(order.getPayments().stream()
                    .map(PaymentResponse::fromEntity)
                    .toList());
        }
        
        return response;
    }
}
