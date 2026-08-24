package org.example.e_com.business;

import org.example.e_com.model.Payment;

import java.util.List;

public interface PaymentBusiness {
    List<Payment> getAllPayment();
    Payment findById(int id);
    Payment findByOrderId(int orderId);
    boolean addPayment(Payment payment);
    boolean updatePayment(Payment payment);

    boolean deletePayment(int id);
}
