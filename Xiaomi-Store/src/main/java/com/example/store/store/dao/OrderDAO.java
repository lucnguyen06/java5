package com.example.store.store.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.store.store.model.CartItem;
import com.example.store.store.model.Order;
import com.example.store.store.model.OrderDetailDTO;
import com.example.store.store.model.Product;

@Repository
public class OrderDAO {

    private final JdbcTemplate jdbc;

    public OrderDAO(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public int create(Order o) {
        String sql = "INSERT INTO orders(username, total) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update(connection -> {
            PreparedStatement ps =
                connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, o.getUsername());
            ps.setDouble(2, o.getTotal());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    public void addDetail(int orderId, CartItem item) {
        String sql = """
            INSERT INTO order_details(order_id, product_id, qty, price)
            VALUES (?, ?, ?, ?)
        """;

        jdbc.update(sql,
            orderId,
            item.getProduct().getId(),
            item.getQty(),
            item.getProduct().getPrice()
        );
    }

    public void insert(Order o) {
        // TODO Auto-generated method stub
    }
    
    public List<Order> findAll() {
        String sql = "SELECT * FROM orders ORDER BY created_at DESC";
        return jdbc.query(sql, orderRowMapper);
    }

    public List<Order> findByUsername(String username) {
        String sql = "SELECT * FROM orders WHERE username = ? ORDER BY created_at DESC";
        return jdbc.query(sql, orderRowMapper, username);
    }

    public Order findById(int id) {
        String sql = "SELECT * FROM orders WHERE id = ?";
        try {
            return jdbc.queryForObject(sql, orderRowMapper, id);
        } catch (Exception e) {
            return null;
        }
    }

    public List<OrderDetailDTO> findOrderDetails(int orderId) {
        String sql = """
            SELECT od.order_id, od.product_id, p.name as product_name, p.image as product_image, 
                   od.qty, od.price
            FROM order_details od
            JOIN products p ON od.product_id = p.id
            WHERE od.order_id = ?
        """;
        return jdbc.query(sql, (rs, i) -> {
            return new OrderDetailDTO(
                rs.getInt("order_id"),
                rs.getInt("product_id"),
                rs.getString("product_name"),
                rs.getString("product_image"),
                rs.getInt("qty"),
                rs.getDouble("price")
            );
        }, orderId);
    }

    public List<Product> findPurchasedProducts(String username) {
        String sql = """
            SELECT DISTINCT p.id, p.name, p.price, COALESCE(p.discount, 0) as discount, 
                   p.image, p.description, p.ram, p.rom, p.screen, p.resolution, 
                   p.sale_period, p.badge, p.codename
            FROM products p
            JOIN order_details od ON p.id = od.product_id
            JOIN orders o ON od.order_id = o.id
            WHERE o.username = ?
            ORDER BY o.created_at DESC
        """;
        return jdbc.query(sql, (rs, i) -> {
            Product p = new Product();
            p.setId(rs.getInt("id"));
            p.setName(rs.getString("name"));
            p.setPrice(rs.getDouble("price"));
            p.setDiscount(rs.getDouble("discount"));
            p.setImage(rs.getString("image"));
            p.setDescription(rs.getString("description"));
            p.setRam(rs.getString("ram"));
            p.setRom(rs.getString("rom"));
            p.setScreen(rs.getString("screen"));
            p.setResolution(rs.getString("resolution"));
            p.setSalePeriod(rs.getString("sale_period"));
            p.setBadge(rs.getString("badge"));
            p.setCodename(rs.getString("codename"));
            return p;
        }, username);
    }

    private final RowMapper<Order> orderRowMapper = (rs, i) -> {
        Order o = new Order();
        o.setId(rs.getInt("id"));
        o.setUsername(rs.getString("username"));
        o.setTotal(rs.getDouble("total"));
        Timestamp createdAt = rs.getTimestamp("created_at");
        if (createdAt != null) {
            o.setCreatedAt(createdAt.toLocalDateTime());
        }
        return o;
    };
}
