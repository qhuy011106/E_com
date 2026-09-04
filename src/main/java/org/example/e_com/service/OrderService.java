package org.example.e_com.service;

import org.example.e_com.model.Order;

import java.sql.Connection;
import java.util.List;

public interface OrderService {

    List<Order> getAllOrder();

    Order findById(int id);

    List<Order> findByUserId(int userId);

    boolean addOrder(Order order);

    boolean updateOrder(Order order);

    boolean deleteOrder(int id);

    int createOrder(Order order, Connection conn);
    List<Order> findOrderWithItemByUserId(int userId);


}
