package com.example.store.store.controller;

import com.example.store.store.dao.AccountDAO;
import com.example.store.store.model.Account;
import com.example.store.store.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/account")
public class AccountController {

    private final AccountDAO dao;
    private final AuthService auth;

    public AccountController(AccountDAO dao, AuthService auth) {
        this.dao = dao;
        this.auth = auth;
    }

    @GetMapping("/settings")
    public String settingsForm(org.springframework.ui.Model model) {
        Account user = auth.getUser();
        if (user == null) {
            return "redirect:/login";
        }
        // Lấy thông tin mới nhất từ database
        Account acc = dao.findByUsername(user.getUsername());
        model.addAttribute("account", acc);
        return "account-settings";
    }

    @PostMapping("/update")
    public String updateAccount(@RequestParam(required = false) String email,
                               @RequestParam(required = false) String fullName,
                               @RequestParam(required = false) String phone,
                               @RequestParam(required = false) String address,
                               org.springframework.ui.Model model) {
        Account user = auth.getUser();
        if (user == null) {
            return "redirect:/login";
        }

        Account acc = dao.findByUsername(user.getUsername());
        if (acc == null) {
            model.addAttribute("error", "Không tìm thấy tài khoản!");
            return "account-settings";
        }

        // Kiểm tra email đã được sử dụng bởi tài khoản khác
        if (email != null && !email.trim().isEmpty() && !email.equals(acc.getEmail())) {
            Account existing = dao.findByEmail(email);
            if (existing != null && !existing.getUsername().equals(acc.getUsername())) {
                model.addAttribute("error", "Email đã được sử dụng bởi tài khoản khác!");
                model.addAttribute("account", acc);
                return "account-settings";
            }
        }

        // Cập nhật thông tin
        if (email != null && !email.trim().isEmpty()) {
            acc.setEmail(email);
        }
        if (fullName != null) {
            acc.setFullName(fullName);
        }
        if (phone != null) {
            acc.setPhone(phone);
        }
        if (address != null) {
            acc.setAddress(address);
        }

        dao.update(acc);
        
        // Cập nhật session
        auth.login(acc);

        model.addAttribute("message", "Cập nhật thông tin thành công!");
        model.addAttribute("account", acc);
        return "account-settings";
    }

    @GetMapping("/change-password")
    public String changePasswordForm() {
        return "change-password";
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestParam String currentPassword,
                                @RequestParam String newPassword,
                                @RequestParam String confirmPassword,
                                org.springframework.ui.Model model) {
        Account user = auth.getUser();
        if (user == null) {
            return "redirect:/login";
        }

        Account acc = dao.findByUsername(user.getUsername());
        if (acc == null) {
            model.addAttribute("error", "Không tìm thấy tài khoản!");
            return "change-password";
        }

        // Kiểm tra mật khẩu hiện tại
        if (!acc.getPassword().equals(currentPassword)) {
            model.addAttribute("error", "Mật khẩu hiện tại không đúng!");
            return "change-password";
        }

        // Kiểm tra mật khẩu mới
        if (newPassword == null || newPassword.trim().isEmpty()) {
            model.addAttribute("error", "Mật khẩu mới không được để trống!");
            return "change-password";
        }

        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("error", "Mật khẩu xác nhận không khớp!");
            return "change-password";
        }

        // Cập nhật mật khẩu
        acc.setPassword(newPassword);
        dao.update(acc);
        
        // Cập nhật session
        auth.login(acc);

        model.addAttribute("message", "Đổi mật khẩu thành công!");
        return "change-password";
    }
}
