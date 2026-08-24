package org.example.e_com.dao;

import org.example.e_com.model.CartItem;

import java.util.List;

public interface CartItemDao {
    List<CartItem> getallCartItem();
    CartItem findById(int id);
    List<CartItem> findByCartId(int cartId);
    int insert(CartItem cartItem);
    int update(CartItem cartItem);
    int delete(int id);

}
