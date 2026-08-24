package org.example.e_com.service.impl;

import org.example.e_com.dao.CartItemDao;
import org.example.e_com.dao.impl.CartItemDaoImpl;
import org.example.e_com.model.CartItem;
import org.example.e_com.service.CartItemService;

import java.util.List;

public class CartItemServiceImpl implements CartItemService {

    private final CartItemDao cartItemDao;

    public CartItemServiceImpl() {
        this.cartItemDao = new CartItemDaoImpl();
    }

    @Override
    public List<CartItem> getAllCartItem() {
        return cartItemDao.getallCartItem();
    }

    @Override
    public CartItem findById(int id) {

        if (id <= 0) {
            return null;
        }

        return cartItemDao.findById(id);
    }

    @Override
    public List<CartItem> findByCartId(int cartId) {

        if (cartId <= 0) {
            return List.of();
        }

        return cartItemDao.findByCartId(cartId);
    }

    @Override
    public boolean addCartItem(CartItem cartItem) {

        if (cartItem == null
                || cartItem.getCartId() <= 0
                || cartItem.getProductId() <= 0
                || cartItem.getQuantity() <= 0) {

            return false;
        }

        return cartItemDao.insert(cartItem) > 0;
    }

    @Override
    public boolean updateCartItem(CartItem cartItem) {

        if (cartItem == null
                || cartItem.getId() <= 0
                || cartItem.getCartId() <= 0
                || cartItem.getProductId() <= 0
                || cartItem.getQuantity() <= 0) {

            return false;
        }

        return cartItemDao.update(cartItem) > 0;
    }

    @Override
    public boolean deleteCartItem(int id) {

        if (id <= 0) {
            return false;
        }

        CartItem cartItem = cartItemDao.findById(id);

        if (cartItem == null) {
            return false;
        }

        return cartItemDao.delete(id) > 0;
    }
}