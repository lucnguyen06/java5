package com.example.store.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.store.store.dao.OrderDAO;
import com.example.store.store.model.Order;
import com.example.store.store.service.AuthService;
import com.example.store.store.service.CartService;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    CartService cart;

    @Autowired
    OrderDAO dao;

    @Autowired
    AuthService auth;

    @GetMapping("/checkout")
    public String checkout() {
        if (auth.getUser() == null) {
            return "redirect:/login";
        }
        
        if (cart.getItems().isEmpty()) {
            return "redirect:/cart/view";
        }

        Order o = new Order();
        o.setUsername(auth.getUser().getUsername());
        o.setTotal(cart.getTotal());

        int orderId = dao.create(o);

        cart.getItems().forEach(item -> {
            dao.addDetail(orderId, item);
        });

        cart.clear();
        return "order-success";
    }

    @GetMapping("/my-orders")
    public String myOrders(Model model) {
        if (auth.getUser() == null) {
            return "redirect:/login";
        }

        String username = auth.getUser().getUsername();
        model.addAttribute("orders", dao.findByUsername(username));
        return "my-orders";
    }

    @GetMapping("/detail/{id}")
    public String orderDetail(@PathVariable int id, Model model) {
        if (auth.getUser() == null) {
            return "redirect:/login";
        }

        Order order = dao.findById(id);
        if (order == null) {
            return "redirect:/order/my-orders";
        }

        // Kiểm tra quyền truy cập
        if (!order.getUsername().equals(auth.getUser().getUsername()) && 
            !"ADMIN".equals(auth.getUser().getRole())) {
            return "redirect:/order/my-orders";
        }

        model.addAttribute("order", order);
        model.addAttribute("orderDetails", dao.findOrderDetails(id));
        return "order-detail";
    }

    @GetMapping("/my-products")
    public String myProducts(Model model) {
        if (auth.getUser() == null) {
            return "redirect:/login";
        }

        String username = auth.getUser().getUsername();
        model.addAttribute("products", dao.findPurchasedProducts(username));
        return "my-products";
    }
}
