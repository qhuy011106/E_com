package org.example.e_com.business.Impl;

import org.example.e_com.business.StatsBusiness;
import org.example.e_com.dto.CustomerStatsDTO;
import org.example.e_com.dto.DashboardStatsDTO;
import org.example.e_com.dto.ProductStatsDTO;
import org.example.e_com.dto.RevenueStatsDTO;
import org.example.e_com.service.StatsService;
import org.example.e_com.service.impl.StatsServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class StatsBusinessImpl implements StatsBusiness {

    private final StatsService statsService;

    public StatsBusinessImpl() {
        this.statsService = new StatsServiceImpl();
    }

    // ===== SẢN PHẨM =====
    @Override
    public List<ProductStatsDTO> getTopSellingProducts(int limit) {
        if (limit <= 0) limit = 10;  // Mặc định top 10
        return statsService.getTopSellingProducts(limit);
    }

    @Override
    public List<ProductStatsDTO> getLowStockProducts(int threshold) {
        if (threshold < 0) threshold = 5;  // Mặc định ngưỡng 5
        return statsService.getLowStockProducts(threshold);
    }

    // ===== DOANH THU =====
    @Override
    public List<RevenueStatsDTO> getRevenueByMonth(int year) {
        if (year <= 0) year = LocalDate.now().getYear();  // Mặc định năm hiện tại
        return statsService.getRevenueByMonth(year);
    }

    @Override
    public RevenueStatsDTO getRevenueByMonth(int month, int year) {
        if (month < 1 || month > 12) return null;
        if (year <= 0) return null;
        return statsService.getRevenueByMonth(month, year);
    }

    // ===== KHÁCH HÀNG =====
    @Override
    public List<CustomerStatsDTO> getTopCustomers(int limit) {
        if (limit <= 0) limit = 10;  // Mặc định top 10
        return statsService.getTopCustomers(limit);
    }

    // ===== DASHBOARD TỔNG QUAN =====
    @Override
    public DashboardStatsDTO getDashboard() {
        DashboardStatsDTO dashboard = new DashboardStatsDTO();

        // 1. Tổng quan
        dashboard.setTotalUsers(statsService.getTotalUsers());
        dashboard.setTotalProducts(statsService.getTotalProducts());
        dashboard.setTotalOrders(statsService.getTotalOrders());
        dashboard.setPendingOrders(statsService.getPendingOrders());
        dashboard.setLowStockProducts(statsService.getLowStockCount(5));

        // 2. Tổng doanh thu
        BigDecimal revenue = BigDecimal.valueOf(statsService.getTotalRevenue());
        dashboard.setTotalRevenue(revenue);

        // 3. Top sản phẩm bán chạy (top 5)
        dashboard.setTopProducts(statsService.getTopSellingProducts(5));

        // 4. Top khách hàng (top 5)
        dashboard.setTopCustomers(statsService.getTopCustomers(5));

        // 5. Doanh thu năm hiện tại
        int currentYear = LocalDate.now().getYear();
        dashboard.setRevenueByMonth(statsService.getRevenueByMonth(currentYear));

        return dashboard;
    }
}