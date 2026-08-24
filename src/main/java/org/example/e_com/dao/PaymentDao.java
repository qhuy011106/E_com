package org.example.e_com.dao;

import org.example.e_com.model.Payment;

import java.util.List;

public interface PaymentDao {

    // Lấy tất cả payment
    List<Payment> getAllPayment();

    // Tìm payment theo ID
    Payment findById(int id);

    // Tìm payment theo Order ID
    Payment findByOrderId(int orderId);

    // Thêm payment
    int insert(Payment payment);

    // Cập nhật payment
    int update(Payment payment);

    // Xóa payment
    int delete(int id);
}