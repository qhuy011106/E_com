package org.example.e_com.business;

import org.example.e_com.model.Cart;

import java.util.List;

public interface CartBusiness {
    List<Cart> getAllCart();
    Cart findById(int id);
    Cart findByUserId(int id);
    boolean addCart(Cart cart);
    boolean updateCart(Cart cart);
    boolean deleteCart(int id);

}
