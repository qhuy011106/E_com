package org.example.e_com.business;

import org.example.e_com.dto.CartDTO;
import org.example.e_com.model.Cart;

import java.util.List;

public interface CartBusiness {
    List<Cart> getAllCart();
    Cart findById(int id);
    Cart findByUserId(int id);
    boolean addCart(Cart cart);
    boolean updateCart(Cart cart);
    boolean deleteCart(int id);

    CartDTO getCartDTOByUserId(int userId);
    boolean addToCart(int userId, int productId, int quantity);
    boolean updateCartItem(int cartItemId, int quantity);
    boolean removeFromCart(int cartItemId);
    boolean clearCart(int userId);
}
