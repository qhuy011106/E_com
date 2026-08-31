package org.example.e_com.service;

import org.example.e_com.model.OrderItem;

import java.sql.Connection;
import java.util.List;

public interface OrderItemService {

    List<OrderItem> getAllOrderItem();

    OrderItem findById(int id);

    List<OrderItem> findByOrderId(int orderId);

    boolean addOrderItem(OrderItem orderItem);

    boolean updateOrderItem(OrderItem orderItem);

    boolean deleteOrderItem(int id);
    int insert(OrderItem orderItem, Connection conn);
}