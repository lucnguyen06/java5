package com.example.store.store.controller;

import com.example.store.store.service.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cart;

    @GetMapping("/view")
    public String view(Model model) {
        model.addAttribute("cart", cart.getItems());
        model.addAttribute("total", cart.getTotal());
        return "cart";
    }

    @GetMapping("/add/{id}")
    public String add(@PathVariable Integer id) {
        cart.add(id);
        return "redirect:/cart/view";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Integer id, @RequestParam int qty) {
        cart.update(id, qty);
        return "redirect:/cart/view";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable Integer id) {
        cart.remove(id);
        return "redirect:/cart/view";
    }
}
