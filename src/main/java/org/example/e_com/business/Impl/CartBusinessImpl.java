package org.example.e_com.business.Impl;

import org.example.e_com.business.CartBusiness;
import org.example.e_com.model.Cart;
import org.example.e_com.service.CartService;
import org.example.e_com.service.impl.CartServiceImpl;

import java.util.List;

public class CartBusinessImpl implements CartBusiness {
    private final CartService cartService;

    public CartBusinessImpl() {
        this.cartService = new CartServiceImpl() ;
    }

    @Override
    public List<Cart> getAllCart() {
        return cartService.getAllCart();
    }

    @Override
    public Cart findById(int id) {
        if (id <= 0) {
            return null;
        }

        return cartService.findById(id);
    }

    @Override
    public Cart findByUserId(int id) {
        if(id <= 0) return null;
        for(Cart cart : cartService.getAllCart()){
            if(cart.getUserId() == id) return cart;
        }
        return null;
    }

    @Override
    public boolean addCart(Cart cart) {
        if(cart == null) return false;
        if(cart.getUserId() <= 0) return false;
        // Một user chỉ nên có một cart
        Cart existingCart = findByUserId(cart.getUserId());
        if (existingCart != null) {
            return false;
        }

        return cartService.addCart(cart);

    }

    @Override
    public boolean updateCart(Cart cart) {
        if (cart == null
                || cart.getId() <= 0
                || cart.getUserId() <= 0) {
            return false;
        }

        Cart existingCart = cartService.findById(cart.getId());

        if (existingCart == null) {
            return false;
        }

        return cartService.updateCart(cart);
    }

    @Override
    public boolean deleteCart(int id) {
        if (id <= 0) {
            return false;
        }

        Cart cart = cartService.findById(id);

        if (cart == null) {
            return false;
        }

        return cartService.deleteCart(id);
    }
}
