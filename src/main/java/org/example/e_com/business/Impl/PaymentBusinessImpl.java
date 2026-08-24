package org.example.e_com.business.Impl;

import org.example.e_com.business.PaymentBusiness;
import org.example.e_com.model.Order;
import org.example.e_com.model.Payment;
import org.example.e_com.model.Product;
import org.example.e_com.service.OrderService;
import org.example.e_com.service.PaymentService;
import org.example.e_com.service.impl.OrderServiceImpl;
import org.example.e_com.service.impl.PaymentServiceImpl;

import java.util.List;

public class PaymentBusinessImpl implements PaymentBusiness {
    private final PaymentService paymentService;
    private final OrderService orderService;
    public PaymentBusinessImpl(){
        this.paymentService = new PaymentServiceImpl();
        this.orderService = new OrderServiceImpl();
    }
    @Override
    public List<Payment> getAllPayment() {
        return paymentService.getAllPayment();
    }

    @Override
    public Payment findById(int id) {
        if(id <= 0) return null;
        return paymentService.findById(id);
    }

    @Override
    public Payment findByOrderId(int orderId) {
        if(orderId <= 0) return null;
        return paymentService.findByOrderId(orderId);
    }

    @Override
    public boolean addPayment(Payment payment) {

        if(payment == null) return false;

        // Kiểm tra Order
        if(payment.getOrderId() <= 0) return false;

        Order order = orderService.findById(payment.getOrderId());

        if(order == null) return false;

        // Kiểm tra phương thức thanh toán
        if(payment.getPaymentMethod() == null
                || payment.getPaymentMethod().trim().isEmpty()) {
            return false;
        }

        // Kiểm tra trạng thái thanh toán
        if(payment.getPaymentStatus() == null
                || payment.getPaymentStatus().trim().isEmpty()) {
            return false;
        }

        // Mỗi Order chỉ có 1 Payment
        Payment existing =
                paymentService.findByOrderId(payment.getOrderId());

        if(existing != null) return false;

        return paymentService.addPayment(payment);
    }

    @Override
    public boolean updatePayment(Payment payment) {
        if(payment == null || payment.getOrderId() <= 0 || payment.getId() <= 0) return false;
        Payment existing = paymentService.findById(payment.getId());
        if(existing == null) return false;
        //kiem tra order
        Order order = orderService.findById(payment.getOrderId());
        if(order == null) return false;
        // Kiểm tra payment method
        if (payment.getPaymentMethod() == null
                || payment.getPaymentMethod().trim().isEmpty()) {
            return false;
        }

        // Kiểm tra payment status
        if (payment.getPaymentStatus() == null
                || payment.getPaymentStatus().trim().isEmpty()) {
            return false;
        }

        return paymentService.updatePayment(payment);
    }

    @Override
    public boolean deletePayment(int id) {

        if (id <= 0) {
            return false;
        }

        Payment payment =
                paymentService.findById(id);

        if (payment == null) {
            return false;
        }

        return paymentService.deletePayment(id);
    }
}