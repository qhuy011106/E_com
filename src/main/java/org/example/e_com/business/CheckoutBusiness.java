package org.example.e_com.business;

import org.example.e_com.model.Order;

public interface CheckoutBusiness {
    Order checkout(int userId, String paymentMethod);
}
