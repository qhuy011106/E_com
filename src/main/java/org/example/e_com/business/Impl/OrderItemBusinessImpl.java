package org.example.e_com.business.Impl;

import org.example.e_com.business.OrderItemBusiness;
import org.example.e_com.model.Order;
import org.example.e_com.model.OrderItem;
import org.example.e_com.model.Product;
import org.example.e_com.service.OrderItemService;
import org.example.e_com.service.OrderService;
import org.example.e_com.service.ProductService;
import org.example.e_com.service.impl.OrderItemServiceImpl;
import org.example.e_com.service.impl.OrderServiceImpl;
import org.example.e_com.service.impl.ProductServiceImpl;

import java.math.BigDecimal;
import java.util.List;

public class OrderItemBusinessImpl implements OrderItemBusiness {
    private final OrderItemService orderItemService;
    private final OrderService orderService;
    private final ProductService productService;

    public OrderItemBusinessImpl() {
        this.orderItemService = new OrderItemServiceImpl();
        this.orderService = new OrderServiceImpl();
        this.productService = new ProductServiceImpl();
    }

    @Override
    public List<OrderItem> getAllOrderItem() {
        return orderItemService.getAllOrderItem();
    }

    @Override
    public OrderItem getOrderItemById(int id) {
        if(id <= 0) return null;
        return orderItemService.findById(id);
    }

    @Override
    public List<OrderItem> getOrderItemsByOrderId(int orderId) {
        if(orderId <= 0) return List.of();
        return orderItemService.findByOrderId(orderId);

    }

    @Override
    public boolean addOrderItem(OrderItem orderItem) {
        if(orderItem == null) return false;
        //kiem tr order
        if(orderItem.getOrderId() <= 0) return false;
        Order order = orderService.findById(orderItem.getOrderId());
        if(order == null) return false;
        //kien tra Product
        if(orderItem.getProductId() <= 0) return false;
        Product product = productService.findById(orderItem.getProductId());
        if(product == null) return false;
        //kiem tra so luong
        if(orderItem.getQuantity() <= 0) return false;
        //kiem tra price
        if(orderItem.getPrice() == null || orderItem.getPrice().compareTo(BigDecimal.ZERO) < 0) return false;
        //khong duoc mua qua hang ton kho
        if(orderItem.getQuantity() > product.getQuantity()) return false;
        return orderItemService.addOrderItem(orderItem);

    }

    @Override
    public boolean updateOrderItem(OrderItem orderItem) {
        if(orderItem == null
                || orderItem.getId() <= 0
                || orderItem.getOrderId() <= 0
                || orderItem.getProductId() <= 0
                || orderItem.getQuantity() <= 0) return false;

        OrderItem existing = orderItemService.findById(orderItem.getId());
        if(existing == null) return false;
        //kiem tra product
        Product product = productService.findById(orderItem.getProductId());
        if(product == null) return false;
        //kiem tra price
        if(orderItem.getPrice() == null || orderItem.getPrice().compareTo(BigDecimal.ZERO) < 0) return false;
        //kiem tra quantity
        if(orderItem.getQuantity() > product.getQuantity()) return false;

        return orderItemService.updateOrderItem(orderItem);


    }


    @Override
    public boolean deleteOrderItem(int id) {
        if(id <= 0) return false;
        OrderItem orderItem = orderItemService.findById(id);
        if(orderItem == null) return false;
        return orderItemService.deleteOrderItem(id);
    }
}
