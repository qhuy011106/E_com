package org.example.e_com.service.impl;

import org.example.e_com.dao.CartDao;
import org.example.e_com.dao.impl.CartDaoImpl;
import org.example.e_com.model.Cart;
import org.example.e_com.service.CartService;

import java.util.List;

public class CartServiceImpl implements CartService {
    private final CartDao cartdao;
    public CartServiceImpl(){
        this.cartdao = new CartDaoImpl();
    }
    @Override
    public List<Cart> getAllCart() {
        return cartdao.getAllCart();
    }

    @Override
    public Cart findById(int id) {
        if(id <= 0) return null;
        return cartdao.findById(id);
    }

    @Override
    public boolean addCart(Cart cart) {
        if(cart == null || cart.getUserId() <= 0) return false;
        return cartdao.insert(cart) > 0;
    }

    @Override
    public boolean updateCart(Cart cart) {
        if(cart == null || cart.getId() <= 0 || cart.getUserId() <= 0) return false;
        return cartdao.update(cart) > 0;
    }

    @Override
    public Cart findByUserId(int userId) {
        if (userId <= 0) {
            return null;
        }

        return cartdao.findByUserId(userId);
    }
    @Override
    public boolean deleteCart(int id) {
        if(id <= 0) return false;
        Cart cart = cartdao.findById(id);
        if(cart == null) return false;
        return cartdao.delete(id) > 0;
    }

}
