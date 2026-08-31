package org.example.e_com.service;

import org.example.e_com.model.Payment;

import java.sql.Connection;
import java.util.List;

public interface PaymentService {

    List<Payment> getAllPayment();

    Payment findById(int id);

    Payment findByOrderId(int orderId);

    boolean addPayment(Payment payment);

    boolean updatePayment(Payment payment);

    boolean deletePayment(int id);

    // Tạo payment trong transaction
    int createPayment(Payment payment, Connection conn);
}