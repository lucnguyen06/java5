package com.example.store.store.config;

import com.example.store.store.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    private AuthService authService;

    @ModelAttribute("currentUser")
    public com.example.store.store.model.Account getCurrentUser() {
        return authService.getUser();
    }
}
