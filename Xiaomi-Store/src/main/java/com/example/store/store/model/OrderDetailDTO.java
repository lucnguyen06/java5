package com.example.store.store.model;

public class OrderDetailDTO {
    private int orderId;
    private int productId;
    private String productName;
    private String productImage;
    private int qty;
    private double price;
    private double subtotal;

    public OrderDetailDTO() {}

    public OrderDetailDTO(int orderId, int productId, String productName, String productImage, int qty, double price) {
        this.orderId = orderId;
        this.productId = productId;
        this.productName = productName;
        this.productImage = productImage;
        this.qty = qty;
        this.price = price;
        this.subtotal = qty * price;
    }

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public String getProductImage() { return productImage; }
    public void setProductImage(String productImage) { this.productImage = productImage; }

    public int getQty() { return qty; }
    public void setQty(int qty) { 
        this.qty = qty;
        this.subtotal = qty * price;
    }

    public double getPrice() { return price; }
    public void setPrice(double price) { 
        this.price = price;
        this.subtotal = qty * price;
    }

    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
}
