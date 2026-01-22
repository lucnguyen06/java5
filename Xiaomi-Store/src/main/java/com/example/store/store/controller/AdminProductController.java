package com.example.store.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.store.store.dao.ProductDAO;
import com.example.store.store.model.Product;

@Controller
@RequestMapping("/admin/product")
public class AdminProductController {

    private final ProductDAO dao;

    public AdminProductController(ProductDAO dao) {
        this.dao = dao;
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("items", dao.findAll());
        return "admin-product";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("item", new Product());
        return "admin/product-form";
    }

    @PostMapping("/create")
    public String create(Product p) {
        dao.insert(p);
        return "redirect:/admin/product/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("item", dao.findById(id));
        return "admin/product-form";
    }

    @PostMapping("/update")
    public String update(Product p) {
        dao.update(p);
        return "redirect:/admin/product/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        dao.delete(id);
        return "redirect:/admin/product/list";
    }
}
