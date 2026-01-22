package com.example.store.store.service;

import com.example.store.store.dao.ProductDAO;
import com.example.store.store.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductDAO dao;

    public ProductService(ProductDAO dao) {
        this.dao = dao;
    }

    public List<Product> getAll() { return dao.findAll(); }
    public Product getById(int id) { return dao.findById(id); }
}
