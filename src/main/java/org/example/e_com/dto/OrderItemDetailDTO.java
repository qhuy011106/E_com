package org.example.e_com.dto;

import java.math.BigDecimal;

public class OrderItemDetailDTO {
    private String productName;
    private int quantity;
    private BigDecimal price;
    private BigDecimal subtotal;

    // Constructor không tham số
    public OrderItemDetailDTO() {}

    // Constructor có tham số
    public OrderItemDetailDTO(String productName, int quantity, BigDecimal price) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.subtotal = price.multiply(BigDecimal.valueOf(quantity));
    }

    // Getters và Setters
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.subtotal = price.multiply(BigDecimal.valueOf(quantity));
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
        this.subtotal = price.multiply(BigDecimal.valueOf(quantity));
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return String.format("📦 %s x %d = %,.0f VND",
                productName, quantity, subtotal);
    }
}