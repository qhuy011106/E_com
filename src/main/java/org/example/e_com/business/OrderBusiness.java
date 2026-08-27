package org.example.e_com.business;

import org.example.e_com.model.Order;

import java.util.List;

public interface OrderBusiness {
    List<Order> getAllOrder();
    Order findById(int id);
    List<Order> findByUserId(int userId);
    boolean addOrder(Order order);
    boolean updateOrder(Order order);
    boolean deleteOrder(int id);
}
