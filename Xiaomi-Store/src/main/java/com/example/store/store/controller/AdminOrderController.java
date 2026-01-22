package com.example.store.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.store.store.dao.OrderDAO;

@Controller
@RequestMapping("/admin/order")
public class AdminOrderController {

    private final OrderDAO dao;

    public AdminOrderController(OrderDAO dao) {
        this.dao = dao;
    }

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("orders", dao.findAll());
        return "admin-order-list";
    }
}
