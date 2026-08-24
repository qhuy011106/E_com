package org.example.e_com.service;

import org.example.e_com.model.CartItem;
import org.example.e_com.model.Category;

import java.util.List;

public interface CartItemService {
    List<CartItem> getAllCartItem();
    CartItem findById(int id);
    List<CartItem> findByCartId(int id);
    boolean addCartItem(CartItem cartitem);
    boolean updateCartItem(CartItem cartitem);
    boolean deleteCartItem(int id);

}
