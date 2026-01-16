package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.model.Order;
import com.example.demo.model.Delivery;
import com.example.demo.model.Payment;
import com.example.demo.service.OrderService;
import com.example.demo.service.DeliveryService;
import com.example.demo.service.PaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class WebController {
    private final OrderService orderService;
    private final DeliveryService deliveryService;
    private final PaymentService paymentService;

    public WebController(OrderService orderService, DeliveryService deliveryService, PaymentService paymentService) {
        this.orderService = orderService;
        this.deliveryService = deliveryService;
        this.paymentService = paymentService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        model.addAttribute("deliveries", deliveryService.getAllDeliveries());
        model.addAttribute("payments", paymentService.getAllPayments());
        return "index";
    }

    @GetMapping("/orders")
    public String orders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        model.addAttribute("orderRequest", new OrderRequest());
        return "orders";
    }

    @PostMapping("/orders")
    public String createOrder(@ModelAttribute OrderRequest request, RedirectAttributes redirectAttributes) {
        try {
            orderService.createOrder(request);
            redirectAttributes.addFlashAttribute("success", "Order created successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error: " + e.getMessage());
        }
        return "redirect:/orders";
    }

    @GetMapping("/deliveries")
    public String deliveries(Model model) {
        model.addAttribute("deliveries", deliveryService.getAllDeliveries());
        model.addAttribute("orders", orderService.getAllOrders());
        model.addAttribute("deliveryRequest", new DeliveryRequest());
        return "deliveries";
    }

    @PostMapping("/deliveries")
    public String createDelivery(@ModelAttribute DeliveryRequest request, RedirectAttributes redirectAttributes) {
        try {
            deliveryService.createDelivery(request);
            redirectAttributes.addFlashAttribute("success", "Delivery created successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error: " + e.getMessage());
        }
        return "redirect:/deliveries";
    }

    @GetMapping("/payments")
    public String payments(Model model) {
        model.addAttribute("payments", paymentService.getAllPayments());
        model.addAttribute("orders", orderService.getAllOrders());
        model.addAttribute("paymentRequest", new PaymentRequest());
        return "payments";
    }

    @PostMapping("/payments")
    public String createPayment(@ModelAttribute PaymentRequest request, RedirectAttributes redirectAttributes) {
        try {
            paymentService.createPayment(request);
            redirectAttributes.addFlashAttribute("success", "Payment created successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error: " + e.getMessage());
        }
        return "redirect:/payments";
    }

    @PostMapping("/orders/{id}/status")
    public String updateOrderStatus(@PathVariable Long id, @RequestParam Order.OrderStatus status, 
                                    RedirectAttributes redirectAttributes) {
        try {
            orderService.updateOrderStatus(id, status);
            redirectAttributes.addFlashAttribute("success", "Order status updated!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error: " + e.getMessage());
        }
        return "redirect:/orders";
    }

    @PostMapping("/deliveries/{id}/status")
    public String updateDeliveryStatus(@PathVariable Long id, @RequestParam Delivery.DeliveryStatus status,
                                       RedirectAttributes redirectAttributes) {
        try {
            UpdateDeliveryStatusRequest request = new UpdateDeliveryStatusRequest();
            request.setStatus(status);
            deliveryService.updateDeliveryStatus(id, request);
            redirectAttributes.addFlashAttribute("success", "Delivery status updated!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error: " + e.getMessage());
        }
        return "redirect:/deliveries";
    }

    @PostMapping("/payments/{id}/status")
    public String updatePaymentStatus(@PathVariable Long id, @RequestParam Payment.PaymentStatus status,
                                      RedirectAttributes redirectAttributes) {
        try {
            UpdatePaymentStatusRequest request = new UpdatePaymentStatusRequest();
            request.setStatus(status);
            paymentService.updatePaymentStatus(id, request);
            redirectAttributes.addFlashAttribute("success", "Payment status updated!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error: " + e.getMessage());
        }
        return "redirect:/payments";
    }
}
