package com.example.store.store.dao;

import com.example.store.store.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.Timestamp;

@Repository
public class AccountDAO {

    private final JdbcTemplate jdbc;

    public AccountDAO(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        initDefaultAccounts();
    }

    private void initDefaultAccounts() {
        // Kiểm tra và tạo tài khoản mặc định nếu chưa tồn tại
        if (findByUsername("admin") == null) {
            Account admin = new Account();
            admin.setUsername("admin");
            admin.setPassword("admin123");
            admin.setEmail("admin@example.com");
            admin.setFullName("Administrator");
            admin.setRole("ADMIN");
            admin.setEnabled(true);
            save(admin);
        }
        if (findByUsername("user") == null) {
            Account user = new Account();
            user.setUsername("user");
            user.setPassword("user123");
            user.setEmail("user@example.com");
            user.setFullName("Test User");
            user.setRole("USER");
            user.setEnabled(true);
            save(user);
        }
    }

    private final RowMapper<Account> rowMapper = (ResultSet rs, int rowNum) -> {
        Account acc = new Account();
        acc.setUsername(rs.getString("username"));
        acc.setPassword(rs.getString("password"));
        acc.setEmail(rs.getString("email"));
        acc.setFullName(rs.getString("full_name"));
        acc.setPhone(rs.getString("phone"));
        acc.setAddress(rs.getString("address"));
        acc.setRole(rs.getString("role"));
        acc.setEnabled(rs.getBoolean("enabled"));
        acc.setActivationToken(rs.getString("activation_token"));
        acc.setResetToken(rs.getString("reset_token"));
        Timestamp expiry = rs.getTimestamp("reset_token_expiry");
        if (expiry != null) {
            acc.setResetTokenExpiry(expiry.toLocalDateTime());
        }
        return acc;
    };

    public Account findByUsername(String username) {
        String sql = "SELECT * FROM accounts WHERE username = ?";
        try {
            return jdbc.queryForObject(sql, rowMapper, username);
        } catch (Exception e) {
            return null;
        }
    }

    public Account findByEmail(String email) {
        String sql = "SELECT * FROM accounts WHERE email = ?";
        try {
            return jdbc.queryForObject(sql, rowMapper, email);
        } catch (Exception e) {
            return null;
        }
    }

    public Account findByActivationToken(String token) {
        String sql = "SELECT * FROM accounts WHERE activation_token = ?";
        try {
            return jdbc.queryForObject(sql, rowMapper, token);
        } catch (Exception e) {
            return null;
        }
    }

    public Account findByResetToken(String token) {
        String sql = "SELECT * FROM accounts WHERE reset_token = ? AND reset_token_expiry > CURRENT_TIMESTAMP";
        try {
            return jdbc.queryForObject(sql, rowMapper, token);
        } catch (Exception e) {
            return null;
        }
    }

    public void save(Account acc) {
        // Kiểm tra xem account đã tồn tại chưa
        Account existing = findByUsername(acc.getUsername());
        
        if (existing == null) {
            // INSERT mới
            String sql = """
                INSERT INTO accounts (username, password, email, full_name, phone, address, role, enabled, activation_token, reset_token, reset_token_expiry)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;
            jdbc.update(sql,
                acc.getUsername(),
                acc.getPassword(),
                acc.getEmail(),
                acc.getFullName(),
                acc.getPhone(),
                acc.getAddress(),
                acc.getRole() != null ? acc.getRole() : "USER",
                acc.isEnabled(),
                acc.getActivationToken(),
                acc.getResetToken(),
                acc.getResetTokenExpiry() != null ? Timestamp.valueOf(acc.getResetTokenExpiry()) : null
            );
        } else {
            // UPDATE existing
            update(acc);
        }
    }

    public void update(Account acc) {
        String sql = """
            UPDATE accounts SET
                password = ?,
                email = ?,
                full_name = ?,
                phone = ?,
                address = ?,
                enabled = ?,
                activation_token = ?,
                reset_token = ?,
                reset_token_expiry = ?
            WHERE username = ?
        """;
        jdbc.update(sql,
            acc.getPassword(),
            acc.getEmail(),
            acc.getFullName(),
            acc.getPhone(),
            acc.getAddress(),
            acc.isEnabled(),
            acc.getActivationToken(),
            acc.getResetToken(),
            acc.getResetTokenExpiry() != null ? Timestamp.valueOf(acc.getResetTokenExpiry()) : null,
            acc.getUsername()
        );
    }
}
