package org.example.e_com.business.Impl;

import org.example.e_com.business.CartItemBusiness;
import org.example.e_com.model.Cart;
import org.example.e_com.model.CartItem;
import org.example.e_com.model.Product;
import org.example.e_com.service.CartItemService;
import org.example.e_com.service.CartService;
import org.example.e_com.service.ProductService;
import org.example.e_com.service.impl.CartItemServiceImpl;
import org.example.e_com.service.impl.CartServiceImpl;
import org.example.e_com.service.impl.ProductServiceImpl;

import java.util.List;

public class CartItemBusinessImpl implements CartItemBusiness {

    private final CartItemService cartItemService;
    private final CartService cartService;
    private final ProductService productService;

    public CartItemBusinessImpl() {
        this.cartItemService = new CartItemServiceImpl();
        this.cartService = new CartServiceImpl();
        this.productService = new ProductServiceImpl();
    }

    @Override
    public List<CartItem> getAllCartItem() {
        return cartItemService.getAllCartItem();
    }

    @Override
    public CartItem getCartItemById(int id) {

        if (id <= 0) {
            return null;
        }

        return cartItemService.findById(id);
    }

    @Override
    public List<CartItem> getCartItemsByCartId(int cartId) {

        if (cartId <= 0) {
            return List.of();
        }

        return cartItemService.findByCartId(cartId);
    }

    @Override
    public boolean addCartItem(CartItem cartItem) {

        if (cartItem == null) {
            return false;
        }

        // Kiểm tra dữ liệu
        if (cartItem.getCartId() <= 0
                || cartItem.getProductId() <= 0
                || cartItem.getQuantity() <= 0) {

            return false;
        }

        // Kiểm tra Cart
        Cart cart = cartService.findById(cartItem.getCartId());

        if (cart == null) {
            return false;
        }

        // Kiểm tra Product
        Product product =
                productService.findById(cartItem.getProductId());

        if (product == null) {
            return false;
        }

        // Kiểm tra tồn kho
        if (cartItem.getQuantity() > product.getQuantity()) {
            return false;
        }

        // Kiểm tra sản phẩm đã có trong Cart chưa
        for (CartItem item :
                cartItemService.findByCartId(cartItem.getCartId())) {

            if (item.getProductId() == cartItem.getProductId()) {

                int newQuantity =
                        item.getQuantity() + cartItem.getQuantity();

                // Không được vượt quá tồn kho
                if (newQuantity > product.getQuantity()) {
                    return false;
                }

                item.setQuantity(newQuantity);

                return cartItemService.updateCartItem(item);
            }
        }

        // Sản phẩm chưa có → thêm mới
        return cartItemService.addCartItem(cartItem);
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

        // Kiểm tra CartItem tồn tại
        CartItem existing =
                cartItemService.findById(cartItem.getId());

        if (existing == null) {
            return false;
        }

        // Kiểm tra Product
        Product product =
                productService.findById(cartItem.getProductId());

        if (product == null) {
            return false;
        }

        // Không được vượt quá tồn kho
        if (cartItem.getQuantity() > product.getQuantity()) {
            return false;
        }

        // UPDATE
        return cartItemService.updateCartItem(cartItem);
    }

    @Override
    public boolean deleteCartItem(int id) {

        if (id <= 0) {
            return false;
        }

        CartItem cartItem =
                cartItemService.findById(id);

        if (cartItem == null) {
            return false;
        }

        return cartItemService.deleteCartItem(id);
    }
}