package com.example.store.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.store.store.dao.ProductDAO;

@Controller
public class HomeController {

    @Autowired
    ProductDAO productDAO;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("items", productDAO.findAll());
        return "product-list";
    }
}
