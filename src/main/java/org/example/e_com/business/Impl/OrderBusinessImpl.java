package org.example.e_com.business.Impl;

import org.example.e_com.business.OrderBusiness;
import org.example.e_com.model.Order;
import org.example.e_com.model.User;
import org.example.e_com.service.OrderService;
import org.example.e_com.service.UserService;
import org.example.e_com.service.impl.OrderServiceImpl;
import org.example.e_com.service.impl.UserServiceImpl;

import java.math.BigDecimal;
import java.util.List;

public class OrderBusinessImpl implements OrderBusiness {
    private final OrderService orderService;
    private final UserService userService;
    public OrderBusinessImpl(){
        this.orderService = new OrderServiceImpl();
        this.userService = new UserServiceImpl();
    }
    @Override
    public List<Order> getAllOrder() {
        return orderService.getAllOrder();
    }

    @Override
    public Order findById(int id) {
        if(id <= 0) return null;
        return orderService.findById(id);
    }

    @Override
    public List<Order> findByUserId(int userId) {
        if(userId <= 0) return List.of();
        return orderService.findByUserId(userId);
    }

    @Override
    public boolean addOrder(Order order) {
        if(order == null) return false;
        if(order.getUser_id() <= 0) return false;
        User user = userService.findById(order.getUser_id());
        if(user == null) return false;
        //kiem tra tong tien
        if(order.getTotal_amount() == null || order.getTotal_amount().compareTo(BigDecimal.ZERO) < 0) return false;
        //kiem tra trang thai
        if(order.getStatus() == null || order.getStatus().trim().isEmpty()) return false;
        return orderService.addOrder(order);
    }

    @Override
    public boolean updateOrder(Order order) {
        if(order == null || order.getId() <= 0) return false;
        //order phai ton tai
        Order existing = orderService.findById(order.getId());
        if(existing == null) return false;
        //kiem tra user
        if(order.getUser_id() <= 0) return false;
        User user = userService.findById(order.getUser_id());
        if(user == null ) return false;
        //keim tra total
        if(order.getTotal_amount() == null || order.getTotal_amount().compareTo(BigDecimal.ZERO) < 0) return false;

        // Kiểm tra status
        if (order.getStatus() == null
                || order.getStatus().trim().isEmpty()) {

            return false;
        }

        return orderService.updateOrder(order);
    }

    @Override
    public boolean deleteOrder(int id) {

        if (id <= 0) {
            return false;
        }

        // Order phải tồn tại
        Order order =
                orderService.findById(id);

        if (order == null) {
            return false;
        }

        return orderService.deleteOrder(id);
    }
}
