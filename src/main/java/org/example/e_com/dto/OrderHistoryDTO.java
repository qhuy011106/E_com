package org.example.e_com.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class OrderHistoryDTO {
    private int orderId;
    private Timestamp createdAt;
    private BigDecimal totalAmount;
    private String status;
    private List<OrderItemDetailDTO> items;

    // Constructor không tham số
    public OrderHistoryDTO() {}

    // Constructor có tham số
    public OrderHistoryDTO(int orderId, Timestamp createdAt, BigDecimal totalAmount, String status) {
        this.orderId = orderId;
        this.createdAt = createdAt;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    // Getters và Setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderItemDetailDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDetailDTO> items) {
        this.items = items;
    }

    // Lấy label hiển thị cho status
    public String getStatusLabel() {
        switch (status) {
            case "PENDING": return "⏳ Chờ xử lý";
            case "CONFIRMED": return "✅ Đã xác nhận";
            case "SHIPPING": return "🚚 Đang giao hàng";
            case "COMPLETED": return "🎉 Hoàn thành";
            case "CANCELLED": return "❌ Đã hủy";
            default: return status;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("┌─────────────────────────────────────────────────\n");
        sb.append(String.format("│ 📋 Mã đơn hàng: #%-10d\n", orderId));
        sb.append(String.format("│ 📅 Ngày tạo: %-30s\n", createdAt));
        sb.append(String.format("│ 💰 Tổng tiền: %,-20.0f VND\n", totalAmount));
        sb.append(String.format("│ 📊 Trạng thái: %-25s\n", getStatusLabel()));
        sb.append("├─────────────────────────────────────────────────\n");
        sb.append("│ 🛒 Chi tiết sản phẩm:\n");

        if (items != null && !items.isEmpty()) {
            for (OrderItemDetailDTO item : items) {
                sb.append(String.format("│   %s\n", item));
            }
        } else {
            sb.append("│   (Không có sản phẩm)\n");
        }

        sb.append("└─────────────────────────────────────────────────\n");
        return sb.toString();
    }
}