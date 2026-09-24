package org.example.e_com.dao.impl;

import org.example.e_com.dao.StatsDao;
import org.example.e_com.dto.CustomerStatsDTO;
import org.example.e_com.dto.ProductStatsDTO;
import org.example.e_com.dto.RevenueStatsDTO;
import org.example.e_com.util.DBConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatsDaoImpl implements StatsDao {

    // =========================================
    // 1. TOP SẢN PHẨM BÁN CHẠY
    // =========================================
    @Override
    public List<ProductStatsDTO> getTopSellingProducts(int limit) {
        List<ProductStatsDTO> result = new ArrayList<>();

        String sql = """
            SELECT 
                p.id AS product_id,
                p.name AS product_name,
                c.name AS category_name,
                SUM(oi.quantity) AS total_sold,
                SUM(oi.quantity * oi.price) AS total_revenue,
                p.quantity AS stock_quantity
            FROM products p
            JOIN categories c ON p.category_id = c.id
            JOIN order_items oi ON p.id = oi.product_id
            JOIN orders o ON oi.order_id = o.id
            WHERE o.status = 'COMPLETED'
            GROUP BY p.id, p.name, c.name, p.quantity
            ORDER BY total_sold DESC
            LIMIT ?
            """;

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, limit);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result.add(new ProductStatsDTO(
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getString("category_name"),
                        rs.getInt("total_sold"),
                        rs.getBigDecimal("total_revenue"),
                        rs.getInt("stock_quantity")
                ));
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    // =========================================
    // 2. SẢN PHẨM SẮP HẾT HÀNG
    // =========================================
    @Override
    public List<ProductStatsDTO> getLowStockProducts(int threshold) {
        List<ProductStatsDTO> result = new ArrayList<>();

        String sql = """
            SELECT 
                p.id AS product_id,
                p.name AS product_name,
                c.name AS category_name,
                COALESCE(SUM(oi.quantity), 0) AS total_sold,
                COALESCE(SUM(oi.quantity * oi.price), 0) AS total_revenue,
                p.quantity AS stock_quantity
            FROM products p
            JOIN categories c ON p.category_id = c.id
            LEFT JOIN order_items oi ON p.id = oi.product_id
            LEFT JOIN orders o ON oi.order_id = o.id AND o.status = 'COMPLETED'
            WHERE p.quantity < ?
            GROUP BY p.id, p.name, c.name, p.quantity
            ORDER BY p.quantity ASC
            """;

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, threshold);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result.add(new ProductStatsDTO(
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getString("category_name"),
                        rs.getInt("total_sold"),
                        rs.getBigDecimal("total_revenue"),
                        rs.getInt("stock_quantity")
                ));
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    // =========================================
    // 3. DOANH THU THEO THÁNG (CẢ NĂM)
    // =========================================
    @Override
    public List<RevenueStatsDTO> getRevenueByMonth(int year) {
        List<RevenueStatsDTO> result = new ArrayList<>();

        String sql = """
            SELECT 
                MONTH(o.created_at) AS month,
                YEAR(o.created_at) AS year,
                COUNT(DISTINCT o.id) AS total_orders,
                SUM(o.total_amount) AS total_revenue,
                AVG(o.total_amount) AS avg_order_value,
                COALESCE(SUM(oi.quantity), 0) AS total_products
            FROM orders o
            LEFT JOIN order_items oi ON o.id = oi.order_id
            WHERE o.status = 'COMPLETED' AND YEAR(o.created_at) = ?
            GROUP BY YEAR(o.created_at), MONTH(o.created_at)
            ORDER BY month ASC
            """;

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, year);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result.add(new RevenueStatsDTO(
                        rs.getInt("month"),
                        rs.getInt("year"),
                        rs.getInt("total_orders"),
                        rs.getBigDecimal("total_revenue"),
                        rs.getBigDecimal("avg_order_value"),
                        rs.getInt("total_products")
                ));
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    // =========================================
    // 4. DOANH THU 1 THÁNG CỤ THỂ
    // =========================================
    @Override
    public RevenueStatsDTO getRevenueByMonth(int month, int year) {
        RevenueStatsDTO result = null;

        String sql = """
            SELECT 
                MONTH(o.created_at) AS month,
                YEAR(o.created_at) AS year,
                COUNT(DISTINCT o.id) AS total_orders,
                SUM(o.total_amount) AS total_revenue,
                AVG(o.total_amount) AS avg_order_value,
                COALESCE(SUM(oi.quantity), 0) AS total_products
            FROM orders o
            LEFT JOIN order_items oi ON o.id = oi.order_id
            WHERE o.status = 'COMPLETED' 
              AND MONTH(o.created_at) = ? 
              AND YEAR(o.created_at) = ?
            GROUP BY YEAR(o.created_at), MONTH(o.created_at)
            """;

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, month);
            ps.setInt(2, year);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                result = new RevenueStatsDTO(
                        rs.getInt("month"),
                        rs.getInt("year"),
                        rs.getInt("total_orders"),
                        rs.getBigDecimal("total_revenue"),
                        rs.getBigDecimal("avg_order_value"),
                        rs.getInt("total_products")
                );
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    // =========================================
    // 5. TOP KHÁCH HÀNG
    // =========================================
    @Override
    public List<CustomerStatsDTO> getTopCustomers(int limit) {
        List<CustomerStatsDTO> result = new ArrayList<>();

        String sql = """
            SELECT 
                u.id AS user_id,
                u.full_name,
                u.email,
                u.phone,
                COUNT(o.id) AS total_orders,
                COALESCE(SUM(o.total_amount), 0) AS total_spent
            FROM users u
            JOIN orders o ON u.id = o.user_id
            WHERE o.status = 'COMPLETED'
            GROUP BY u.id, u.full_name, u.email, u.phone
            ORDER BY total_spent DESC
            LIMIT ?
            """;

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, limit);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result.add(new CustomerStatsDTO(
                        rs.getInt("user_id"),
                        rs.getString("full_name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getInt("total_orders"),
                        rs.getBigDecimal("total_spent")
                ));
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    // =========================================
    // 6-10. ĐẾM (DÙNG HELPER)
    // =========================================
    @Override
    public int getTotalUsers() {
        return getCount("SELECT COUNT(*) FROM users");
    }

    @Override
    public int getTotalProducts() {
        return getCount("SELECT COUNT(*) FROM products");
    }

    @Override
    public int getTotalOrders() {
        return getCount("SELECT COUNT(*) FROM orders");
    }

    @Override
    public int getPendingOrders() {
        return getCount("SELECT COUNT(*) FROM orders WHERE status = 'PENDING'");
    }

    @Override
    public int getLowStockCount(int threshold) {
        return getCount("SELECT COUNT(*) FROM products WHERE quantity < " + threshold);
    }

    // =========================================
    // 11. TỔNG DOANH THU
    // =========================================
    @Override
    public double getTotalRevenue() {
        double total = 0;
        String sql = "SELECT COALESCE(SUM(total_amount), 0) FROM orders WHERE status = 'COMPLETED'";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                BigDecimal revenue = rs.getBigDecimal(1);
                total = revenue != null ? revenue.doubleValue() : 0;
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return total;
    }

    // =========================================
    // HELPER: Đếm số lượng
    // =========================================
    private int getCount(String sql) {
        int count = 0;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}