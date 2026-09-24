package org.example.e_com.dto;

import java.math.BigDecimal;

public class CustomerStatsDTO {
    private int userId;
    private String fullName;
    private String email;
    private String phone;
    private int totalOrders;         // Số đơn hàng đã mua
    private BigDecimal totalSpent;   // Tổng tiền đã chi

    // Constructor không tham số
    public CustomerStatsDTO() {}

    // Constructor có tham số
    public CustomerStatsDTO(int userId, String fullName, String email, String phone,
                            int totalOrders, BigDecimal totalSpent) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.totalOrders = totalOrders;
        this.totalSpent = totalSpent;
    }

    // Getters và Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(int totalOrders) {
        this.totalOrders = totalOrders;
    }

    public BigDecimal getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(BigDecimal totalSpent) {
        this.totalSpent = totalSpent;
    }

    @Override
    public String toString() {
        return String.format(
                "👤 %-25s | 📧 %-25s | Đơn: %-5d | Chi tiêu: %,.0f VND",
                fullName, email, totalOrders, totalSpent
        );
    }
}