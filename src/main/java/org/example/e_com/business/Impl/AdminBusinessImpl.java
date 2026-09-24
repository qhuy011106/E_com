package org.example.e_com.business.Impl;

import org.example.e_com.business.AdminBusiness;
import org.example.e_com.model.Order;
import org.example.e_com.model.Product;
import org.example.e_com.model.User;
import org.example.e_com.service.OrderService;
import org.example.e_com.service.ProductService;
import org.example.e_com.service.UserService;
import org.example.e_com.service.impl.OrderServiceImpl;
import org.example.e_com.service.impl.ProductServiceImpl;
import org.example.e_com.service.impl.UserServiceImpl;

import java.math.BigDecimal;
import java.util.List;

public class AdminBusinessImpl implements AdminBusiness {
    private final UserService userService;
    private final OrderService orderService;
    private final ProductService productService;
    public AdminBusinessImpl(){
        this.userService = new UserServiceImpl();
        this.orderService = new OrderServiceImpl();
        this.productService = new ProductServiceImpl();
    }
    @Override
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @Override
    public boolean updateUserRole(int userId, String newRole) {
        if(userId <= 0) return false;
        if(newRole == null || newRole.trim().isEmpty()) return false;
        //chi cho phep admin hoac customer
        String role = newRole.trim().toUpperCase();
        if(!role.equals("ADMIN") || !role.equals("CUSTOMER")) return false;
        User user = userService.findById(userId);
        user.setRole(role);
        return userService.updateUser(user);
    }

    @Override
    public boolean deleteUser(int userId) {
        if(userId <= 0) return false;
        User user = userService.findById(userId);
        if(user == null) return false;
        return userService.deleteUser(userId);
    }

    @Override
    public List<Order> getAllOrder() {
        return orderService.getAllOrder();
    }

    @Override
    public Order getOrderDetail(int orderId) {
        if(orderId <= 0) return null;
        return orderService.findById(orderId);
    }

    @Override
    public boolean updateOrderStatus(int orderId, String status) {
        if(orderId <= 0) return false;
        if(status == null || status.trim().isEmpty()) return false;
        String statusUpper = status.trim().toUpperCase();
        String[] validStatus ={"PENDING", "CONFIRMED", "SHIPPING", "COMPLETED", "CANCELLED"};
        boolean isValid = false;
        for(String s : validStatus){
            if(s.equals(statusUpper)){
                isValid = true;
                break;
            }
        }
        if(!isValid) return false;
        Order order = orderService.findById(orderId);
        if(order == null) return false;
        order.setStatus(statusUpper);
        return orderService.updateOrder(order);
    }

    @Override
    public List<Product> getAllProduct() {
        return productService.getAllProduct();
    }

    @Override
    public boolean addProduct(Product product) {
        if (product == null) return false;
        if (product.getName() == null || product.getName().trim().isEmpty()) return false;
        if (product.getPrice() == null || product.getPrice().compareTo(BigDecimal.ZERO) < 0) return false;
        if (product.getQuantity() < 0) return false;
        if (product.getCategory_id() <= 0) return false;
        return productService.addProduct(product);
    }

    @Override
    public boolean updateProduct(Product product) {
        if (product == null || product.getId() <= 0) return false;
        if (product.getName() == null || product.getName().trim().isEmpty()) return false;
        if (product.getPrice() == null || product.getPrice().compareTo(BigDecimal.ZERO) < 0) return false;
        if (product.getQuantity() < 0) return false;
        if (product.getCategory_id() <= 0) return false;

        // Kiểm tra tồn tại
        Product existing = productService.findById(product.getId());
        if (existing == null) return false;

        return productService.updateProduct(product);
    }

    @Override
    public boolean deletProduct(int productId) {
        if (productId <= 0) return false;

        Product product = productService.findById(productId);
        if (product == null) return false;

        return productService.deleteProduct(productId);
    }

    @Override
    public int getTotalUser() {
        List<User> users = userService.getAllUser();
        if(users == null) return 0;
        else return users.size();
    }

    @Override
    public int getTotalProduct() {
        List<Product> products = productService.getAllProduct();
        if(products == null) return 0;
        return products.size();

    }

    @Override
    public int getTotalOrder() {
        List<Order> orders = orderService.getAllOrder();
        if(orders == null) return 0;
        return orders.size();
    }

    @Override
    public double getTotalRevenue() {
        List<Order> orders = orderService.getAllOrder();
        if (orders == null || orders.isEmpty()) return 0;
        double total = 0;
        for(Order order : orders){
            //chi tinh doang thu tu down hang da thanh toan
            if("COMPLETED".equals(order.getStatus())){
                total += order.getTotal_amount().doubleValue();
            }
        }
        return total;
    }
}
