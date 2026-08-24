package org.example.e_com.service;

import org.example.e_com.model.Cart;

import java.util.List;

public interface CartService {
    List<Cart> getAllCart();
    Cart findById(int id);
    boolean addCart(Cart cart);
    boolean updateCart(Cart cart);
    boolean deleteCart(int id);
}
