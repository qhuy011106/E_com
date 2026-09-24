package org.example.e_com.business.Impl;

import org.example.e_com.business.OrderHistoryBusiness;
import org.example.e_com.dto.OrderHistoryDTO;
import org.example.e_com.dto.OrderItemDetailDTO;
import org.example.e_com.model.Order;
import org.example.e_com.model.OrderItem;
import org.example.e_com.model.Product;
import org.example.e_com.service.OrderItemService;
import org.example.e_com.service.OrderService;
import org.example.e_com.service.ProductService;
import org.example.e_com.service.impl.OrderItemServiceImpl;
import org.example.e_com.service.impl.OrderServiceImpl;
import org.example.e_com.service.impl.ProductServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class OrderHistoryBusinessImpl implements OrderHistoryBusiness {

    private final OrderService orderService;
    private final OrderItemService orderItemService;
    private final ProductService productService;

    public OrderHistoryBusinessImpl() {
        this.orderService = new OrderServiceImpl();
        this.orderItemService = new OrderItemServiceImpl();
        this.productService = new ProductServiceImpl();
    }

    // =========================================
    // LẤY LỊCH SỬ ĐƠN HÀNG CỦA USER
    // =========================================
    @Override
    public List<OrderHistoryDTO> getOrderHistory(int userId) {
        if (userId <= 0) return new ArrayList<>();

        // 1. Lấy tất cả đơn hàng của user
        List<Order> orders = orderService.findByUserId(userId);
        List<OrderHistoryDTO> result = new ArrayList<>();

        if (orders == null || orders.isEmpty()) {
            return result;
        }

        // 2. Chuyển đổi từng Order -> OrderHistoryDTO
        for (Order order : orders) {
            OrderHistoryDTO dto = new OrderHistoryDTO(
                    order.getId(),
                    order.getCreateAt(),
                    order.getTotal_amount(),
                    order.getStatus()
            );

            // 3. Lấy chi tiết sản phẩm trong đơn hàng
            List<OrderItem> items = orderItemService.findByOrderId(order.getId());
            List<OrderItemDetailDTO> itemDTOs = new ArrayList<>();

            for (OrderItem item : items) {
                Product product = productService.findById(item.getProductId());
                String productName = (product != null) ? product.getName() : "Sản phẩm đã bị xóa";

                OrderItemDetailDTO itemDTO = new OrderItemDetailDTO(
                        productName,
                        item.getQuantity(),
                        item.getPrice()
                );
                itemDTOs.add(itemDTO);
            }

            dto.setItems(itemDTOs);
            result.add(dto);
        }

        return result;
    }

    // =========================================
    // LẤY CHI TIẾT 1 ĐƠN HÀNG
    // =========================================
    @Override
    public OrderHistoryDTO getOrderDetail(int orderId) {
        if (orderId <= 0) return null;

        // 1. Tìm đơn hàng
        Order order = orderService.findById(orderId);
        if (order == null) return null;

        // 2. Tạo DTO
        OrderHistoryDTO dto = new OrderHistoryDTO(
                order.getId(),
                order.getCreateAt(),
                order.getTotal_amount(),
                order.getStatus()
        );

        // 3. Lấy chi tiết sản phẩm
        List<OrderItem> items = orderItemService.findByOrderId(orderId);
        List<OrderItemDetailDTO> itemDTOs = new ArrayList<>();

        for (OrderItem item : items) {
            Product product = productService.findById(item.getProductId());
            String productName = (product != null) ? product.getName() : "Sản phẩm đã bị xóa";

            OrderItemDetailDTO itemDTO = new OrderItemDetailDTO(
                    productName,
                    item.getQuantity(),
                    item.getPrice()
            );
            itemDTOs.add(itemDTO);
        }

        dto.setItems(itemDTOs);
        return dto;
    }
}