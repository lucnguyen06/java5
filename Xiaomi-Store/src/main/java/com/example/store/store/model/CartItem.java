package com.example.store.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private Product product;
    private int qty;

    public Product getProduct() { return product; }
    public void setProduct(Product id) { this.product = id; }
    public int getQty() { return qty; }
    public void setQty(int qty) { this.qty = qty; }
}
