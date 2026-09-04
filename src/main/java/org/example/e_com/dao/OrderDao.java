package org.example.e_com.dao;

import org.example.e_com.model.Order;

import java.sql.Connection;
import java.util.List;

public interface OrderDao {

    List<Order> getAllOrder();

    Order findById(int id);

    List<Order> findByUserId(int userId);

    // Dùng cho các chức năng bình thường
    int insert(Order order);

    // Dùng cho Checkout Transaction
    int insert(Order order, Connection conn);

    int update(Order order);

    int delete(int id);
    //xem lich su don hang cua user
    List<Order> findOrderWithItemByUserId(int userId);
}