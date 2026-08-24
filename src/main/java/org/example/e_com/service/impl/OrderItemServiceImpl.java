package org.example.e_com.service.impl;

import org.example.e_com.dao.OrderItemDao;
import org.example.e_com.dao.impl.OrderItemDaoImpl;
import org.example.e_com.model.OrderItem;
import org.example.e_com.service.OrderItemService;

import java.math.BigDecimal;
import java.util.List;

public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemDao orderItemDao;

    public OrderItemServiceImpl() {
        this.orderItemDao = new OrderItemDaoImpl();
    }

    @Override
    public List<OrderItem> getAllOrderItem() {
        return orderItemDao.getAllOrderItem();
    }

    @Override
    public OrderItem findById(int id) {
        if (id <= 0) {
            return null;
        }

        return orderItemDao.findById(id);
    }

    @Override
    public List<OrderItem> findByOrderId(int orderId) {
        if (orderId <= 0) {
            return List.of();
        }

        return orderItemDao.findByOrderId(orderId);
    }

    @Override
    public boolean addOrderItem(OrderItem orderItem) {

        if (orderItem == null
                || orderItem.getOrderId() <= 0
                || orderItem.getProductId() <= 0
                || orderItem.getQuantity() <= 0
                || orderItem.getPrice() == null
                || orderItem.getPrice().compareTo(BigDecimal.ZERO) < 0) {

            return false;
        }

        return orderItemDao.insert(orderItem) > 0;
    }

    @Override
    public boolean updateOrderItem(OrderItem orderItem) {

        if (orderItem == null
                || orderItem.getId() <= 0
                || orderItem.getOrderId() <= 0
                || orderItem.getProductId() <= 0
                || orderItem.getQuantity() <= 0
                || orderItem.getPrice() == null
                || orderItem.getPrice().compareTo(BigDecimal.ZERO) < 0) {

            return false;
        }

        return orderItemDao.update(orderItem) > 0;
    }

    @Override
    public boolean deleteOrderItem(int id) {

        if (id <= 0) {
            return false;
        }

        OrderItem orderItem = orderItemDao.findById(id);

        if (orderItem == null) {
            return false;
        }

        return orderItemDao.delete(id) > 0;
    }
}