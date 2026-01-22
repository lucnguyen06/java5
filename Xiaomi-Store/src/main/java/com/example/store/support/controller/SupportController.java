package com.example.store.support.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SupportController {

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }
    
    @PostMapping("/contact")
    public String handleContact() {
        return "redirect:/contact";
    }

    @GetMapping("/news")
    public String news() {
        return "news";
    }
}
