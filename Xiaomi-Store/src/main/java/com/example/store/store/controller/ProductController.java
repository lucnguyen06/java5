package com.example.store.store.controller;

import com.example.store.store.dao.ProductDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductDAO dao;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("items", dao.findAll());
        return "product-list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        model.addAttribute("p", dao.findById(id));
        return "product-detail";
    }
}
