package org.example.e_com.service.impl;

import org.example.e_com.dao.OrderDao;
import org.example.e_com.dao.impl.OrderDaoImpl;
import org.example.e_com.model.Order;
import org.example.e_com.service.OrderService;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;
    public OrderServiceImpl(){
        this.orderDao = new OrderDaoImpl();

    }
    @Override
    public List<Order> getAllOrder() {
        return orderDao.getAllOrder();
    }

    @Override
    public Order findById(int id) {
        if(id <= 0) return null;
        return orderDao.findById(id);
    }

    @Override
    public List<Order> findByUserId(int userId) {
        if(userId <= 0) return null;
        return orderDao.findByUserId(userId);
    }

    @Override
    public boolean addOrder(Order order) {
        if (order == null
                || order.getUser_id() <= 0
                || order.getTotal_amount() == null
                || order.getTotal_amount().compareTo(BigDecimal.ZERO) < 0
                || order.getStatus() == null
                || order.getStatus().trim().isEmpty()) {

            return false;
        }

        return orderDao.insert(order) > 0;
    }

    @Override
    public boolean updateOrder(Order order) {
        if (order == null
                || order.getId() <= 0
                || order.getUser_id() <= 0
                || order.getTotal_amount() == null
                || order.getTotal_amount().compareTo(BigDecimal.ZERO) < 0
                || order.getStatus() == null
                || order.getStatus().trim().isEmpty()) {

            return false;
        }

        return orderDao.update(order) > 0;
    }

    @Override
    public boolean deleteOrder(int id) {
        if (id <= 0) {
            return false;
        }

        Order order = orderDao.findById(id);

        if (order == null) {
            return false;
        }

        return orderDao.delete(id) > 0;
    }

    @Override
    public int createOrder(Order order, Connection conn) {

        if (order == null || conn == null) {
            return 0;
        }

        if (order.getUser_id() <= 0
                || order.getTotal_amount() == null
                || order.getTotal_amount().compareTo(BigDecimal.ZERO) < 0
                || order.getStatus() == null
                || order.getStatus().trim().isEmpty()) {

            return 0;
        }

        return orderDao.insert(order, conn);
    }

    @Override
    public List<Order> findOrderWithItemByUserId(int userId) {
        if(userId <= 0) return List.of();
        return orderDao.findOrderWithItemByUserId(userId);
    }
}
