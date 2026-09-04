package org.example.e_com.dto;

import java.math.BigDecimal;

public class CartItemDTO {
    private int cartItemId;
    private int productId;
    private String productName;
    private String categoryName;
    private int quantity;
    private BigDecimal price;
    private BigDecimal subtotal;
    private int maxQuantity; // Số lượng tồn kho tối đa có thể mua

    // Constructor không tham số
    public CartItemDTO() {}

    // Constructor có tham số
    public CartItemDTO(int cartItemId, int productId, String productName,
                       int quantity, BigDecimal price) {
        this.cartItemId = cartItemId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.subtotal = price.multiply(BigDecimal.valueOf(quantity));
    }

    // Getters và Setters
    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    public int getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(int maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    // Kiểm tra có đủ hàng không
    public boolean isAvailable() {
        return maxQuantity >= quantity;
    }

    @Override
    public String toString() {
        String status = isAvailable() ? "✅" : "⚠️";
        return String.format("%s %s x %d = %,.0f VND %s",
                status,
                productName,
                quantity,
                subtotal,
                isAvailable() ? "" : "(Không đủ hàng)"
        );
    }
}