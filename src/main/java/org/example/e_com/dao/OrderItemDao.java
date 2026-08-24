package org.example.e_com.dao;

import org.example.e_com.model.OrderItem;

import java.util.List;

public interface OrderItemDao {

    // Lấy tất cả order item
    List<OrderItem> getAllOrderItem();

    // Tìm theo ID
    OrderItem findById(int id);

    // Lấy các item của một order
    List<OrderItem> findByOrderId(int orderId);

    // Thêm
    int insert(OrderItem orderItem);

    // Cập nhật
    int update(OrderItem orderItem);

    // Xóa
    int delete(int id);
}