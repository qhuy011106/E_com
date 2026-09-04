package org.example.e_com.dto;

import java.math.BigDecimal;
import java.util.List;

public class CartDTO {
    private int cartId;
    private int userId;
    private String userName;
    private List<CartItemDTO> items;
    private int totalItems;
    private BigDecimal totalPrice;

    // Constructor không tham số
    public CartDTO() {}

    // Constructor có tham số
    public CartDTO(int cartId, int userId, String userName) {
        this.cartId = cartId;
        this.userId = userId;
        this.userName = userName;
        this.totalItems = 0;
        this.totalPrice = BigDecimal.ZERO;
    }

    // Getters và Setters
    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<CartItemDTO> getItems() {
        return items;
    }

    public void setItems(List<CartItemDTO> items) {
        this.items = items;
        // Tự động tính toán khi set items
        calculateTotals();
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    // Phương thức tính tổng
    private void calculateTotals() {
        if (items == null || items.isEmpty()) {
            this.totalItems = 0;
            this.totalPrice = BigDecimal.ZERO;
            return;
        }

        this.totalItems = items.stream()
                .mapToInt(CartItemDTO::getQuantity)
                .sum();

        this.totalPrice = items.stream()
                .map(CartItemDTO::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Kiểm tra giỏ hàng trống
    public boolean isEmpty() {
        return items == null || items.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("┌─────────────────────────────────────────────────\n");
        sb.append(String.format("│ 🛒 GIỎ HÀNG CỦA: %s\n", userName));
        sb.append(String.format("│ 📋 Mã giỏ hàng: #%-10d\n", cartId));
        sb.append("├─────────────────────────────────────────────────\n");

        if (isEmpty()) {
            sb.append("│   🛍️ Giỏ hàng trống!\n");
        } else {
            sb.append("│ 📦 DANH SÁCH SẢN PHẨM:\n");
            for (CartItemDTO item : items) {
                sb.append(String.format("│   %s\n", item));
            }
            sb.append("├─────────────────────────────────────────────────\n");
            sb.append(String.format("│ 📊 Tổng số sản phẩm: %d\n", totalItems));
            sb.append(String.format("│ 💰 Tổng tiền: %,.0f VND\n", totalPrice));
        }

        sb.append("└─────────────────────────────────────────────────\n");
        return sb.toString();
    }
}