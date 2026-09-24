package org.example.e_com.business;

import org.example.e_com.model.Order;
import org.example.e_com.model.Product;
import org.example.e_com.model.User;

import java.util.List;

public interface AdminBusiness {
    List<User> getAllUser();
    boolean updateUserRole(int userId, String newRole);
    boolean deleteUser(int userId);

    //quan ly don hang
    List<Order> getAllOrder();
    Order getOrderDetail(int orderId);
    boolean updateOrderStatus(int orderId, String status);

    //quan ly san pham
    List<Product> getAllProduct();
    boolean addProduct(Product product);
    boolean updateProduct(Product product);
    boolean deletProduct(int productId);

    //thong ke co ban
    int getTotalUser();
    int getTotalOrder();
    int getTotalProduct();
    double getTotalRevenue();



}
