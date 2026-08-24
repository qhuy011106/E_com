package org.example.e_com.dao;

import org.example.e_com.model.Cart;

import java.util.List;

public interface CartDao {

    List<Cart> getAllCart();

    Cart findById(int id);

    Cart findByUserId(int userId);

    int insert(Cart cart);

    int update(Cart cart);

    int delete(int id);
}