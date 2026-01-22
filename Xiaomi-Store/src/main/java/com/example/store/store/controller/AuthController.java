package com.example.store.store.controller;

import com.example.store.store.dao.AccountDAO;
import com.example.store.store.model.Account;
import com.example.store.store.service.AuthService;
import com.example.store.store.service.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Controller
public class AuthController {

    private final AccountDAO dao;
    private final AuthService auth;
    private final EmailService emailService;

    public AuthController(AccountDAO dao, AuthService auth, EmailService emailService) {
        this.dao = dao;
        this.auth = auth;
        this.emailService = emailService;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam(required = false) String username,
                        @RequestParam(required = false) String password,
                        org.springframework.ui.Model model) {
        // Kiểm tra nếu username hoặc password trống
        if (username == null || username.trim().isEmpty() || 
            password == null || password.trim().isEmpty()) {
            model.addAttribute("error", "Hãy điền thông tin đăng nhập");
            return "login";
        }
        
        Account acc = dao.findByUsername(username);
        if (acc != null && acc.getPassword().equals(password)) {
            if (!acc.isEnabled()) {
                model.addAttribute("error", "Tài khoản chưa được kích hoạt. Vui lòng kiểm tra email!");
                return "login";
            }
            auth.login(acc);
            return "redirect:/product/list";
        }
        model.addAttribute("error", "Sai tên đăng nhập hoặc mật khẩu!");
        return "login";
    }

    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                          @RequestParam String password,
                          @RequestParam String email,
                          @RequestParam(required = false) String fullName,
                          org.springframework.ui.Model model) {
        // Validation
        if (username == null || username.trim().isEmpty() ||
            password == null || password.trim().isEmpty() ||
            email == null || email.trim().isEmpty()) {
            model.addAttribute("error", "Vui lòng điền đầy đủ thông tin!");
            return "register";
        }

        // Kiểm tra username đã tồn tại
        if (dao.findByUsername(username) != null) {
            model.addAttribute("error", "Tên đăng nhập đã tồn tại!");
            return "register";
        }

        // Kiểm tra email đã tồn tại
        if (dao.findByEmail(email) != null) {
            model.addAttribute("error", "Email đã được sử dụng!");
            return "register";
        }

        // Tạo tài khoản mới
        Account acc = new Account();
        acc.setUsername(username);
        acc.setPassword(password);
        acc.setEmail(email);
        acc.setFullName(fullName);
        acc.setRole("USER");
        acc.setEnabled(false);
        
        // Tạo activation token
        String activationToken = UUID.randomUUID().toString();
        acc.setActivationToken(activationToken);
        
        dao.save(acc);
        
        // Gửi email kích hoạt
        emailService.sendActivationEmail(email, username, activationToken);
        
        model.addAttribute("message", "Đăng ký thành công! Vui lòng kiểm tra email để kích hoạt tài khoản.");
        return "register";
    }

    @GetMapping("/activate")
    public String activate(@RequestParam String token, org.springframework.ui.Model model) {
        Account acc = dao.findByActivationToken(token);
        if (acc == null) {
            model.addAttribute("error", "Token không hợp lệ hoặc đã hết hạn!");
            return "activate";
        }

        acc.setEnabled(true);
        acc.setActivationToken(null);
        dao.update(acc);
        
        model.addAttribute("message", "Kích hoạt tài khoản thành công! Bạn có thể đăng nhập ngay bây giờ.");
        return "activate";
    }

    @GetMapping("/forgot-password")
    public String forgotPasswordForm() {
        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestParam String email, org.springframework.ui.Model model) {
        Account acc = dao.findByEmail(email);
        if (acc == null) {
            // Không tiết lộ email có tồn tại hay không (bảo mật)
            model.addAttribute("message", "Nếu email tồn tại, chúng tôi đã gửi link đặt lại mật khẩu.");
            return "forgot-password";
        }

        // Tạo reset token
        String resetToken = UUID.randomUUID().toString();
        acc.setResetToken(resetToken);
        acc.setResetTokenExpiry(LocalDateTime.now().plusHours(1));
        dao.update(acc);

        // Gửi email reset password
        emailService.sendPasswordResetEmail(acc.getEmail(), acc.getUsername(), resetToken);
        
        model.addAttribute("message", "Nếu email tồn tại, chúng tôi đã gửi link đặt lại mật khẩu.");
        return "forgot-password";
    }

    @GetMapping("/reset-password")
    public String resetPasswordForm(@RequestParam String token, org.springframework.ui.Model model) {
        Account acc = dao.findByResetToken(token);
        if (acc == null) {
            model.addAttribute("error", "Token không hợp lệ hoặc đã hết hạn!");
            return "reset-password";
        }
        model.addAttribute("token", token);
        return "reset-password";
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam String token,
                               @RequestParam String password,
                               @RequestParam String confirmPassword,
                               org.springframework.ui.Model model) {
        Account acc = dao.findByResetToken(token);
        if (acc == null) {
            model.addAttribute("error", "Token không hợp lệ hoặc đã hết hạn!");
            return "reset-password";
        }

        if (password == null || password.trim().isEmpty()) {
            model.addAttribute("error", "Mật khẩu không được để trống!");
            model.addAttribute("token", token);
            return "reset-password";
        }

        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Mật khẩu xác nhận không khớp!");
            model.addAttribute("token", token);
            return "reset-password";
        }

        acc.setPassword(password);
        acc.setResetToken(null);
        acc.setResetTokenExpiry(null);
        dao.update(acc);

        model.addAttribute("message", "Đặt lại mật khẩu thành công! Bạn có thể đăng nhập ngay bây giờ.");
        return "reset-password";
    }

    @GetMapping("/logout")
    public String logout() {
        auth.logout();
        return "redirect:/login";
    }
}
