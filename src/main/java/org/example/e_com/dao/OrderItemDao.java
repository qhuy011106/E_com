package org.example.e_com.dao;

import org.example.e_com.model.OrderItem;

import java.sql.Connection;
import java.util.List;

public interface OrderItemDao {

    List<OrderItem> getAllOrderItem();

    OrderItem findById(int id);

    List<OrderItem> findByOrderId(int orderId);

    // Dùng cho CRUD bình thường
    int insert(OrderItem orderItem);

    // Dùng cho Checkout Transaction
    int insert(OrderItem orderItem, Connection conn);

    int update(OrderItem orderItem);

    int delete(int id);
}