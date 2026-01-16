package com.example.demo.service;

import com.example.demo.dto.DeliveryRequest;
import com.example.demo.dto.DeliveryResponse;
import com.example.demo.dto.UpdateDeliveryStatusRequest;
import com.example.demo.model.Delivery;
import com.example.demo.model.Order;
import com.example.demo.repository.DeliveryRepository;
import com.example.demo.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;
    private final OrderRepository orderRepository;

    public DeliveryService(DeliveryRepository deliveryRepository, OrderRepository orderRepository) {
        this.deliveryRepository = deliveryRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public DeliveryResponse createDelivery(DeliveryRequest request) {
        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + request.getOrderId()));

        if (deliveryRepository.findByOrderId(request.getOrderId()).isPresent()) {
            throw new RuntimeException("Delivery already exists for order id: " + request.getOrderId());
        }

        Delivery delivery = new Delivery();
        delivery.setOrder(order);
        delivery.setShippingAddress(request.getShippingAddress());
        delivery.setCarrier(request.getCarrier());
        delivery.setShippingCost(request.getShippingCost());
        delivery.setEstimatedDeliveryDate(request.getEstimatedDeliveryDate());
        delivery.setStatus(Delivery.DeliveryStatus.PENDING);

        order.setDelivery(delivery);
        order.setStatus(Order.OrderStatus.CONFIRMED);

        Delivery savedDelivery = deliveryRepository.save(delivery);
        return DeliveryResponse.fromEntity(savedDelivery);
    }

    @Transactional(readOnly = true)
    public List<DeliveryResponse> getAllDeliveries() {
        return deliveryRepository.findAll().stream()
                .map(DeliveryResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public DeliveryResponse getDeliveryById(Long id) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery not found with id: " + id));
        return DeliveryResponse.fromEntity(delivery);
    }

    @Transactional(readOnly = true)
    public DeliveryResponse getDeliveryByOrderId(Long orderId) {
        Delivery delivery = deliveryRepository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Delivery not found for order id: " + orderId));
        return DeliveryResponse.fromEntity(delivery);
    }

    @Transactional(readOnly = true)
    public DeliveryResponse getDeliveryByTrackingNumber(String trackingNumber) {
        Delivery delivery = deliveryRepository.findByTrackingNumber(trackingNumber)
                .orElseThrow(() -> new RuntimeException("Delivery not found with tracking number: " + trackingNumber));
        return DeliveryResponse.fromEntity(delivery);
    }

    @Transactional(readOnly = true)
    public List<DeliveryResponse> getDeliveriesByStatus(Delivery.DeliveryStatus status) {
        return deliveryRepository.findByStatus(status).stream()
                .map(DeliveryResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public DeliveryResponse updateDeliveryStatus(Long id, UpdateDeliveryStatusRequest request) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery not found with id: " + id));

        delivery.setStatus(request.getStatus());

        if (request.getTrackingNumber() != null && !request.getTrackingNumber().isEmpty()) {
            delivery.setTrackingNumber(request.getTrackingNumber());
        }

        if (request.getStatus() == Delivery.DeliveryStatus.IN_TRANSIT && delivery.getTrackingNumber() == null) {
            delivery.setTrackingNumber("TRACK-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        }

        if (request.getStatus() == Delivery.DeliveryStatus.DELIVERED) {
            delivery.setActualDeliveryDate(LocalDateTime.now());
            delivery.getOrder().setStatus(Order.OrderStatus.DELIVERED);
        }

        Delivery updatedDelivery = deliveryRepository.save(delivery);
        return DeliveryResponse.fromEntity(updatedDelivery);
    }

    @Transactional
    public void deleteDelivery(Long id) {
        if (!deliveryRepository.existsById(id)) {
            throw new RuntimeException("Delivery not found with id: " + id);
        }
        deliveryRepository.deleteById(id);
    }
}
