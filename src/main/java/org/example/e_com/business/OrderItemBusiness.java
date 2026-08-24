package org.example.e_com.business;

import org.example.e_com.model.OrderItem;

import java.util.List;

public interface OrderItemBusiness {

    List<OrderItem> getAllOrderItem();

    OrderItem getOrderItemById(int id);

    List<OrderItem> getOrderItemsByOrderId(int orderId);

    boolean addOrderItem(OrderItem orderItem);

    boolean updateOrderItem(OrderItem orderItem);

    boolean deleteOrderItem(int id);
}