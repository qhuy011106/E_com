package org.example.e_com.business;

import org.example.e_com.dto.CustomerStatsDTO;
import org.example.e_com.dto.DashboardStatsDTO;
import org.example.e_com.dto.ProductStatsDTO;
import org.example.e_com.dto.RevenueStatsDTO;

import java.util.List;

public interface StatsBusiness {
    // ===== SẢN PHẨM =====
    List<ProductStatsDTO> getTopSellingProducts(int limit);
    List<ProductStatsDTO> getLowStockProducts(int threshold);

    // ===== DOANH THU =====
    List<RevenueStatsDTO> getRevenueByMonth(int year);
    RevenueStatsDTO getRevenueByMonth(int month, int year);

    // ===== KHÁCH HÀNG =====
    List<CustomerStatsDTO> getTopCustomers(int limit);

    // ===== DASHBOARD TỔNG QUAN =====
    DashboardStatsDTO getDashboard();
}