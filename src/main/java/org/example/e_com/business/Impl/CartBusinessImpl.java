package org.example.e_com.business.Impl;

import org.example.e_com.business.CartBusiness;
import org.example.e_com.dto.CartDTO;
import org.example.e_com.dto.CartItemDTO;
import org.example.e_com.model.Cart;
import org.example.e_com.model.CartItem;
import org.example.e_com.model.Product;
import org.example.e_com.model.User;
import org.example.e_com.service.CartItemService;
import org.example.e_com.service.CartService;
import org.example.e_com.service.ProductService;
import org.example.e_com.service.UserService;
import org.example.e_com.service.impl.CartItemServiceImpl;
import org.example.e_com.service.impl.CartServiceImpl;
import org.example.e_com.service.impl.ProductServiceImpl;
import org.example.e_com.service.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class CartBusinessImpl implements CartBusiness {
    private final CartService cartService;
    private final CartItemService cartItemService;
    private final ProductService productService;
    private final UserService userService;

    public CartBusinessImpl() {
        this.cartService = new CartServiceImpl() ;
        this.cartItemService = new CartItemServiceImpl();
        this.productService = new ProductServiceImpl();
        this.userService = new UserServiceImpl();
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

    @Override
    public CartDTO getCartDTOByUserId(int userId) {
        // 1. Kiểm tra user
        User user = userService.findById(userId);
        if (user == null) {
            return null;
        }

        // 2. Tìm cart của user (hoặc tạo mới)
        Cart cart = findByUserId(userId);
        if (cart == null) {
            cart = new Cart();
            cart.setUserId(userId);
            if (!cartService.addCart(cart)) {
                return null;
            }
            cart = findByUserId(userId);
        }

        // 3. Lấy danh sách cart items
        List<CartItem> cartItems = cartItemService.findByCartId(cart.getId());

        // 4. Tạo CartDTO
        CartDTO cartDTO = new CartDTO(cart.getId(), userId, user.getFullname());

        if (cartItems == null || cartItems.isEmpty()) {
            cartDTO.setItems(new ArrayList<>());
            return cartDTO;
        }

        // 5. Chuyển đổi CartItem -> CartItemDTO
        List<CartItemDTO> itemDTOs = new ArrayList<>();
        for (CartItem item : cartItems) {
            Product product = productService.findById(item.getProductId());
            if (product == null) continue;

            CartItemDTO itemDTO = new CartItemDTO(
                    item.getId(),
                    product.getId(),
                    product.getName(),
                    item.getQuantity(),
                    product.getPrice()
            );
            itemDTO.setMaxQuantity(product.getQuantity());
            itemDTOs.add(itemDTO);
        }

        cartDTO.setItems(itemDTOs);
        return cartDTO;
    }

    @Override
    public boolean addToCart(int userId, int productId, int quantity) {
        // 1. Kiểm tra user
        if (userService.findById(userId) == null) {
            return false;
        }

        // 2. Kiểm tra sản phẩm
        Product product = productService.findById(productId);
        if (product == null) {
            return false;
        }

        // 3. Kiểm tra số lượng
        if (quantity <= 0) {
            return false;
        }

        if (quantity > product.getQuantity()) {
            return false;
        }

        // 4. Tìm cart của user (hoặc tạo mới)
        Cart cart = findByUserId(userId);
        if (cart == null) {
            cart = new Cart();
            cart.setUserId(userId);
            if (!cartService.addCart(cart)) {
                return false;
            }
            cart = findByUserId(userId);
        }

        // 5. Kiểm tra sản phẩm đã có trong cart chưa
        CartItem existingItem = cartItemService.findByCartIdAndProductId(cart.getId(), productId);

        if (existingItem != null) {
            // Cập nhật số lượng
            int newQuantity = existingItem.getQuantity() + quantity;
            if (newQuantity > product.getQuantity()) {
                return false;
            }
            existingItem.setQuantity(newQuantity);
            return cartItemService.updateCartItem(existingItem);
        } else {
            // Thêm mới
            CartItem cartItem = new CartItem();
            cartItem.setCartId(cart.getId());
            cartItem.setProductId(productId);
            cartItem.setQuantity(quantity);
            return cartItemService.addCartItem(cartItem);
        }
    }


    @Override
    public boolean updateCartItem(int cartItemId, int quantity) {
        if (quantity <= 0) {
            return removeFromCart(cartItemId);
        }

        CartItem cartItem = cartItemService.findById(cartItemId);
        if (cartItem == null) return false;

        Product product = productService.findById(cartItem.getProductId());
        if (product == null) return false;

        if (quantity > product.getQuantity()) return false;

        cartItem.setQuantity(quantity);
        return cartItemService.updateCartItem(cartItem);
    }

    @Override
    public boolean removeFromCart(int cartItemId) {
        if (cartItemId <= 0) return false;
        return cartItemService.deleteCartItem(cartItemId);
    }

    @Override
    public boolean clearCart(int userId) {
        Cart cart = findByUserId(userId);
        if (cart == null) return false;
        return cartItemService.clearCart(cart.getId());
    }
}
