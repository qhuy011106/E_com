package org.example.e_com.dao;

import org.example.e_com.dto.CustomerStatsDTO;
import org.example.e_com.dto.ProductStatsDTO;
import org.example.e_com.dto.RevenueStatsDTO;
import org.example.e_com.model.Product;

import java.util.List;

public interface StatsDao {
    List<ProductStatsDTO> getTopSellingProducts(int limit);
    List<ProductStatsDTO> getLowStockProducts(int limit);

    List<RevenueStatsDTO> getRevenueByMonth(int year);
    RevenueStatsDTO getRevenueByMonth(int month, int year);
    List<CustomerStatsDTO> getTopCustomers(int limit);
    int getTotalUsers();
    int getTotalProducts();
    int getTotalOrders();
    double getTotalRevenue();
    int getPendingOrders();
    int getLowStockCount(int threshold);

}
