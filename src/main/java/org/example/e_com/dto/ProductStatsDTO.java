package org.example.e_com.dto;

import java.math.BigDecimal;

public class ProductStatsDTO {
    private int productId;
    private String productName;
    private String categoryName;
    private int totalSold;           // Tổng số lượng đã bán
    private BigDecimal totalRevenue; // Tổng doanh thu từ sản phẩm
    private int stockQuantity;       // Tồn kho hiện tại

    // Constructor không tham số
    public ProductStatsDTO() {}

    // Constructor có tham số
    public ProductStatsDTO(int productId, String productName, String categoryName,
                           int totalSold, BigDecimal totalRevenue, int stockQuantity) {
        this.productId = productId;
        this.productName = productName;
        this.categoryName = categoryName;
        this.totalSold = totalSold;
        this.totalRevenue = totalRevenue;
        this.stockQuantity = stockQuantity;
    }

    // Getters và Setters
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getTotalSold() {
        return totalSold;
    }

    public void setTotalSold(int totalSold) {
        this.totalSold = totalSold;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    @Override
    public String toString() {
        return String.format(
                "📦 %-30s | Danh mục: %-15s | Đã bán: %-5d | Doanh thu: %,.0f VND | Tồn: %d",
                productName, categoryName, totalSold, totalRevenue, stockQuantity
        );
    }
}
