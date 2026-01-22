package com.example.store.store.service;

import com.example.store.store.model.Account;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class AuthService {
    private Account user;

    public Account getUser() { return user; }
    public void login(Account u) { this.user = u; }
    public void logout() { this.user = null; }
}
