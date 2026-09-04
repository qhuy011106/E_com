package org.example.e_com.dto;

import java.math.BigDecimal;

public class ProductDTO {
    private int id;
    private String name;
    private BigDecimal price;
    private int quantity;
    private String categoryName;
    private int categoryId;
    private int totalSold;
    private BigDecimal totalRevenue;
    private double averageRating;
    private int reviewCount;

    // Constructor không tham số
    public ProductDTO() {}

    // Constructor cơ bản
    public ProductDTO(int id, String name, BigDecimal price, int quantity, String categoryName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.categoryName = categoryName;
    }

    // Constructor đầy đủ
    public ProductDTO(int id, String name, BigDecimal price, int quantity,
                      String categoryName, int totalSold, BigDecimal totalRevenue) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.categoryName = categoryName;
        this.totalSold = totalSold;
        this.totalRevenue = totalRevenue;
    }

    // Getters và Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    // Kiểm tra hàng còn hay không
    public boolean isInStock() {
        return quantity > 0;
    }

    // Kiểm tra hàng sắp hết (ít hơn 5)
    public boolean isLowStock() {
        return quantity > 0 && quantity < 5;
    }

    // Lấy trạng thái tồn kho
    public String getStockStatus() {
        if (quantity <= 0) return "🔴 Hết hàng";
        if (quantity < 5) return "🟡 Sắp hết";
        if (quantity < 20) return "🟢 Còn ít";
        return "✅ Còn nhiều";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("┌─────────────────────────────────────────────────\n");
        sb.append(String.format("│ 📦 %s\n", name));
        sb.append(String.format("│ 📂 Danh mục: %s\n", categoryName));
        sb.append(String.format("│ 💰 Giá: %,.0f VND\n", price));
        sb.append(String.format("│ 📊 Tồn kho: %d %s\n", quantity, getStockStatus()));

        if (totalSold > 0) {
            sb.append(String.format("│ 🛒 Đã bán: %d\n", totalSold));
            sb.append(String.format("│ 💵 Doanh thu: %,.0f VND\n", totalRevenue));
        }

        if (reviewCount > 0) {
            sb.append(String.format("│ ⭐ Đánh giá: %.1f/5 (%d đánh giá)\n",
                    averageRating, reviewCount));
        }

        sb.append("└─────────────────────────────────────────────────\n");
        return sb.toString();
    }

    // Phiên bản ngắn gọn để hiển thị trong danh sách
    public String toShortString() {
        return String.format("%-30s | %,.0f VND | %s",
                name, price, getStockStatus());
    }
}