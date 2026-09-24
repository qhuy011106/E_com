package org.example.e_com.dto;

import java.math.BigDecimal;

public class RevenueStatsDTO {
    private int month;
    private int year;
    private int totalOrders;          // Số đơn hàng trong tháng
    private BigDecimal totalRevenue;  // Tổng doanh thu
    private BigDecimal avgOrderValue; // Giá trị đơn hàng trung bình
    private int totalProducts;        // Tổng số sản phẩm đã bán

    // Constructor không tham số
    public RevenueStatsDTO() {}

    // Constructor có tham số
    public RevenueStatsDTO(int month, int year, int totalOrders,
                           BigDecimal totalRevenue, BigDecimal avgOrderValue, int totalProducts) {
        this.month = month;
        this.year = year;
        this.totalOrders = totalOrders;
        this.totalRevenue = totalRevenue;
        this.avgOrderValue = avgOrderValue;
        this.totalProducts = totalProducts;
    }

    // Getters và Setters
    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(int totalOrders) {
        this.totalOrders = totalOrders;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public BigDecimal getAvgOrderValue() {
        return avgOrderValue;
    }

    public void setAvgOrderValue(BigDecimal avgOrderValue) {
        this.avgOrderValue = avgOrderValue;
    }

    public int getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(int totalProducts) {
        this.totalProducts = totalProducts;
    }

    // Lấy label hiển thị
    public String getMonthLabel() {
        return String.format("Tháng %02d/%d", month, year);
    }

    @Override
    public String toString() {
        return String.format(
                "📅 %-15s | Đơn: %-5d | Doanh thu: %,.0f VND | TB/đơn: %,.0f VND | SP bán: %d",
                getMonthLabel(), totalOrders, totalRevenue, avgOrderValue, totalProducts
        );
    }
}