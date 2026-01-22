package com.example.store.store.service;

import com.example.store.store.dao.OrderDAO;
import com.example.store.store.model.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderDAO dao;

    public OrderService(OrderDAO dao) {
        this.dao = dao;
    }

    public void create(Order o) {
        dao.insert(o);
    }
}
