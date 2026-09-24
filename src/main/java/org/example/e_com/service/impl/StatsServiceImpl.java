package org.example.e_com.service.impl;

import org.example.e_com.dao.StatsDao;
import org.example.e_com.dao.impl.StatsDaoImpl;
import org.example.e_com.dto.CustomerStatsDTO;
import org.example.e_com.dto.ProductStatsDTO;
import org.example.e_com.dto.RevenueStatsDTO;
import org.example.e_com.service.StatsService;

import java.util.List;

public class StatsServiceImpl implements StatsService {

    private final StatsDao statsDao;

    public StatsServiceImpl() {
        this.statsDao = new StatsDaoImpl();
    }

    // ===== SẢN PHẨM =====
    @Override
    public List<ProductStatsDTO> getTopSellingProducts(int limit) {
        if (limit <= 0) return List.of();
        return statsDao.getTopSellingProducts(limit);
    }

    @Override
    public List<ProductStatsDTO> getLowStockProducts(int threshold) {
        if (threshold < 0) return List.of();
        return statsDao.getLowStockProducts(threshold);
    }

    // ===== DOANH THU =====
    @Override
    public List<RevenueStatsDTO> getRevenueByMonth(int year) {
        if (year <= 0) return List.of();
        return statsDao.getRevenueByMonth(year);
    }

    @Override
    public RevenueStatsDTO getRevenueByMonth(int month, int year) {
        if (month < 1 || month > 12) return null;
        if (year <= 0) return null;
        return statsDao.getRevenueByMonth(month, year);
    }

    // ===== KHÁCH HÀNG =====
    @Override
    public List<CustomerStatsDTO> getTopCustomers(int limit) {
        if (limit <= 0) return List.of();
        return statsDao.getTopCustomers(limit);
    }

    // ===== TỔNG QUAN =====
    @Override
    public int getTotalUsers() {
        return statsDao.getTotalUsers();
    }

    @Override
    public int getTotalProducts() {
        return statsDao.getTotalProducts();
    }

    @Override
    public int getTotalOrders() {
        return statsDao.getTotalOrders();
    }

    @Override
    public double getTotalRevenue() {
        return statsDao.getTotalRevenue();
    }

    @Override
    public int getPendingOrders() {
        return statsDao.getPendingOrders();
    }

    @Override
    public int getLowStockCount(int threshold) {
        if (threshold < 0) return 0;
        return statsDao.getLowStockCount(threshold);
    }
}