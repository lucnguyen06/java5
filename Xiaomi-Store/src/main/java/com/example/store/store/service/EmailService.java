package com.example.store.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired(required = false)
    private JavaMailSender mailSender;

    public void sendActivationEmail(String email, String username, String activationToken) {
        if (mailSender == null) {
            // Nếu không cấu hình email, in ra console (cho development)
            System.out.println("=== ACTIVATION EMAIL ===");
            System.out.println("To: " + email);
            System.out.println("Subject: Kich hoat tai khoan");
            System.out.println("Body: Xin chao " + username + ",\n\n" +
                "Vui long click vao link sau de kich hoat tai khoan:\n" +
                "http://localhost:8080/activate?token=" + activationToken + "\n\n" +
                "Cam on ban!");
            return;
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Kich hoat tai khoan");
        message.setText("Xin chao " + username + ",\n\n" +
            "Vui long click vao link sau de kich hoat tai khoan:\n" +
            "http://localhost:8080/activate?token=" + activationToken + "\n\n" +
            "Cam on ban!");
        mailSender.send(message);
    }

    public void sendPasswordResetEmail(String email, String username, String resetToken) {
        if (mailSender == null) {
            // Nếu không cấu hình email, in ra console (cho development)
            System.out.println("=== PASSWORD RESET EMAIL ===");
            System.out.println("To: " + email);
            System.out.println("Subject: Dat lai mat khau");
            System.out.println("Body: Xin chao " + username + ",\n\n" +
                "Ban da yeu cau dat lai mat khau. Vui long click vao link sau:\n" +
                "http://localhost:8080/reset-password?token=" + resetToken + "\n\n" +
                "Link nay se het han sau 1 gio.\n\n" +
                "Neu ban khong yeu cau dat lai mat khau, vui long bo qua email nay.");
            return;
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Dat lai mat khau");
        message.setText("Xin chao " + username + ",\n\n" +
            "Ban da yeu cau dat lai mat khau. Vui long click vao link sau:\n" +
            "http://localhost:8080/reset-password?token=" + resetToken + "\n\n" +
            "Link nay se het han sau 1 gio.\n\n" +
            "Neu ban khong yeu cau dat lai mat khau, vui long bo qua email nay.");
        mailSender.send(message);
    }
}
