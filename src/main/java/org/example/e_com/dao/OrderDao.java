package org.example.e_com.dao;

import org.example.e_com.model.Order;

import java.util.List;

public interface OrderDao {

    // Lấy tất cả đơn hàng
    List<Order> getAllOrder();

    // Tìm đơn hàng theo ID
    Order findById(int id);

    // Lấy tất cả đơn hàng của một user
    List<Order> findByUserId(int userId);

    // Thêm đơn hàng
    int insert(Order order);

    // Cập nhật đơn hàng
    int update(Order order);

    // Xóa đơn hàng
    int delete(int id);
}