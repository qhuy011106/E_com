package org.example.e_com.service;

import org.example.e_com.dto.CustomerStatsDTO;
import org.example.e_com.dto.ProductStatsDTO;
import org.example.e_com.dto.RevenueStatsDTO;

import java.util.List;

public interface StatsService {
    // ===== SẢN PHẨM =====
    List<ProductStatsDTO> getTopSellingProducts(int limit);
    List<ProductStatsDTO> getLowStockProducts(int threshold);

    // ===== DOANH THU =====
    List<RevenueStatsDTO> getRevenueByMonth(int year);
    RevenueStatsDTO getRevenueByMonth(int month, int year);

    // ===== KHÁCH HÀNG =====
    List<CustomerStatsDTO> getTopCustomers(int limit);

    // ===== TỔNG QUAN =====
    int getTotalUsers();
    int getTotalProducts();
    int getTotalOrders();
    double getTotalRevenue();
    int getPendingOrders();
    int getLowStockCount(int threshold);
}