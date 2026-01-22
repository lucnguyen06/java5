package com.example.store.store.service;

import com.example.store.store.dao.ProductDAO;
import com.example.store.store.model.CartItem;
import com.example.store.store.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;

@Service
@SessionScope
public class CartService {

    private final Map<Integer, CartItem> map = new HashMap<>();

    @Autowired
    ProductDAO productDAO;

    public void add(Integer id) {
        Product p = productDAO.findById(id);
        if (p == null) return;

        CartItem item = map.get(id);

        if (item == null) {
            item = new CartItem();
            item.setProduct(p);
            item.setQty(1);
        } else {
            item.setQty(item.getQty() + 1);
        }

        map.put(id, item);
    }

    public Collection<CartItem> getItems() {
        return map.values();
    }

    public double getTotal() {
        return map.values().stream()
                .mapToDouble(i -> i.getProduct().getPrice() * i.getQty())
                .sum();
    }

    public void clear() {
        map.clear();
    }

    public void update(Integer id, int qty) {
        CartItem item = map.get(id);
        if (item != null) {
            if (qty <= 0) {
                map.remove(id);
            } else {
                item.setQty(qty);
            }
        }
    }

    public void remove(Integer id) {
        map.remove(id);
    }
}
