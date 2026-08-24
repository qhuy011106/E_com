package org.example.e_com.business;

import org.example.e_com.model.CartItem;

import java.util.List;

public interface CartItemBusiness {
    List<CartItem> getAllCartItem();

    CartItem getCartItemById(int id);

    List<CartItem> getCartItemsByCartId(int cartId);

    boolean addCartItem(CartItem cartItem);

    boolean updateCartItem(CartItem cartItem);

    boolean deleteCartItem(int id);
}
