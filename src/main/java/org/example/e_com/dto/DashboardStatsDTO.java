package org.example.e_com.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DashboardStatsDTO {
    // ===== TỔNG QUAN =====
    private int totalUsers;
    private int totalProducts;
    private int totalOrders;
    private BigDecimal totalRevenue;
    private int pendingOrders;
    private int lowStockProducts;

    // ===== CHI TIẾT =====
    private List<ProductStatsDTO> topProducts;
    private List<CustomerStatsDTO> topCustomers;
    private List<RevenueStatsDTO> revenueByMonth;

    // Constructor không tham số
    public DashboardStatsDTO() {
        this.topProducts = new ArrayList<>();
        this.topCustomers = new ArrayList<>();
        this.revenueByMonth = new ArrayList<>();
        this.totalRevenue = BigDecimal.ZERO;
    }

    // Getters và Setters
    public int getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(int totalUsers) {
        this.totalUsers = totalUsers;
    }

    public int getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(int totalProducts) {
        this.totalProducts = totalProducts;
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

    public int getPendingOrders() {
        return pendingOrders;
    }

    public void setPendingOrders(int pendingOrders) {
        this.pendingOrders = pendingOrders;
    }

    public int getLowStockProducts() {
        return lowStockProducts;
    }

    public void setLowStockProducts(int lowStockProducts) {
        this.lowStockProducts = lowStockProducts;
    }

    public List<ProductStatsDTO> getTopProducts() {
        return topProducts;
    }

    public void setTopProducts(List<ProductStatsDTO> topProducts) {
        this.topProducts = topProducts;
    }

    public List<CustomerStatsDTO> getTopCustomers() {
        return topCustomers;
    }

    public void setTopCustomers(List<CustomerStatsDTO> topCustomers) {
        this.topCustomers = topCustomers;
    }

    public List<RevenueStatsDTO> getRevenueByMonth() {
        return revenueByMonth;
    }

    public void setRevenueByMonth(List<RevenueStatsDTO> revenueByMonth) {
        this.revenueByMonth = revenueByMonth;
    }

    // ===== HIỂN THỊ ĐẸP =====
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("\n╔══════════════════════════════════════════════════╗\n");
        sb.append("║           📊 DASHBOARD TỔNG QUAN                ║\n");
        sb.append("╠══════════════════════════════════════════════════╣\n");
        sb.append(String.format("║ Tổng người dùng:    %-25d ║\n", totalUsers));
        sb.append(String.format("║ Tổng sản phẩm:      %-25d ║\n", totalProducts));
        sb.append(String.format("║ Tổng đơn hàng:      %-25d ║\n", totalOrders));
        sb.append(String.format("║ Đơn chờ xử lý:      %-25d ║\n", pendingOrders));
        sb.append(String.format("║ Sản phẩm sắp hết:   %-25d ║\n", lowStockProducts));
        sb.append(String.format("║ Tổng doanh thu:     %,.0f VND%n", totalRevenue));
        sb.append("╚══════════════════════════════════════════════════╝\n");

        return sb.toString();
    }
}