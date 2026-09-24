package org.example.e_com.business;

import org.example.e_com.dto.OrderHistoryDTO;
import java.util.List;

public interface OrderHistoryBusiness {
    // Lấy lịch sử đơn hàng của user
    List<OrderHistoryDTO> getOrderHistory(int userId);

    // Lấy chi tiết 1 đơn hàng
    OrderHistoryDTO getOrderDetail(int orderId);
}