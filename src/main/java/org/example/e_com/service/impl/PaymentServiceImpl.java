package org.example.e_com.service.impl;

import org.example.e_com.dao.PaymentDao;
import org.example.e_com.dao.impl.PaymentDaoImpl;
import org.example.e_com.model.Payment;
import org.example.e_com.service.PaymentService;

import java.sql.Connection;
import java.util.List;

public class PaymentServiceImpl implements PaymentService {
    private  final PaymentDao paymentdao;
    public PaymentServiceImpl(){
        this.paymentdao = new PaymentDaoImpl();
    }
    @Override
    public List<Payment> getAllPayment() {
        return paymentdao.getAllPayment();
    }

    @Override
    public Payment findById(int id) {
        if(id <= 0) return null;
        return paymentdao.findById(id);
    }

    @Override
    public Payment findByOrderId(int orderId) {
        if(orderId <= 0) return null;
        return paymentdao.findByOrderId(orderId);
    }

    @Override
    public boolean addPayment(Payment payment) {
        if(payment == null ||payment.getOrderId() <= 0
                || payment.getPaymentMethod() == null
                || payment.getPaymentMethod().trim().isEmpty()
                || payment.getPaymentStatus() == null
                || payment.getPaymentStatus().trim().isEmpty() ) return false;
        return paymentdao.insert(payment) > 0;
    }

    @Override
    public boolean updatePayment(Payment payment) {
        if (payment == null
                || payment.getId() <= 0
                || payment.getOrderId() <= 0
                || payment.getPaymentMethod() == null
                || payment.getPaymentMethod().trim().isEmpty()
                || payment.getPaymentStatus() == null
                || payment.getPaymentStatus().trim().isEmpty()) {

            return false;
        }

        return paymentdao.update(payment) > 0;
    }

    @Override
    public boolean deletePayment(int id) {
        if(id <= 0) return false;
        Payment payment = paymentdao.findById(id);
        if(payment == null) return false;
        return paymentdao.delete(id) > 0;
    }

    @Override
    public int createPayment(Payment payment, Connection conn) {

        if (payment == null || conn == null) {
            return 0;
        }

        if (payment.getOrderId() <= 0
                || payment.getPaymentMethod() == null
                || payment.getPaymentMethod().trim().isEmpty()
                || payment.getPaymentStatus() == null
                || payment.getPaymentStatus().trim().isEmpty()) {

            return 0;
        }

        return paymentdao.insert(payment, conn);
    }
}
