package org.example.e_com.dao;

import org.example.e_com.model.CartItem;
import java.sql.Connection;
import java.util.List;

public interface CartItemDao {
    List<CartItem> getAllCartItem();
    CartItem findById(int id);
    List<CartItem> findByCartId(int cartId);
    CartItem findByCartIdAndProductId(int cartId, int productId);   // ← THÊM
    int insert(CartItem cartItem);
    int update(CartItem cartItem);
    int delete(int id);
    int deleteByCartId(int cartId);                                 // ← THÊM
    int deleteByCartId(int cartId, Connection conn);                // ← THÊM
}