package org.example.e_com.business.Impl;

import org.example.e_com.business.CheckoutBusiness;
import org.example.e_com.model.Cart;
import org.example.e_com.model.CartItem;
import org.example.e_com.model.Order;
import org.example.e_com.model.OrderItem;
import org.example.e_com.model.Payment;
import org.example.e_com.model.Product;
import org.example.e_com.service.CartItemService;
import org.example.e_com.service.CartService;
import org.example.e_com.service.OrderItemService;
import org.example.e_com.service.OrderService;
import org.example.e_com.service.PaymentService;
import org.example.e_com.service.ProductService;
import org.example.e_com.service.UserService;
import org.example.e_com.service.impl.CartItemServiceImpl;
import org.example.e_com.service.impl.CartServiceImpl;
import org.example.e_com.service.impl.OrderItemServiceImpl;
import org.example.e_com.service.impl.OrderServiceImpl;
import org.example.e_com.service.impl.PaymentServiceImpl;
import org.example.e_com.service.impl.ProductServiceImpl;
import org.example.e_com.service.impl.UserServiceImpl;
import org.example.e_com.util.DBConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CheckoutBusinessImpl implements CheckoutBusiness {

    private final CartService cartService;
    private final CartItemService cartItemService;
    private final ProductService productService;
    private final OrderService orderService;
    private final OrderItemService orderItemService;
    private final PaymentService paymentService;
    private final UserService userService;

    public CheckoutBusinessImpl() {
        this.cartService = new CartServiceImpl();
        this.cartItemService = new CartItemServiceImpl();
        this.productService = new ProductServiceImpl();
        this.orderService = new OrderServiceImpl();
        this.orderItemService = new OrderItemServiceImpl();
        this.paymentService = new PaymentServiceImpl();
        this.userService = new UserServiceImpl();
    }

    @Override
    public Order checkout(int userId, String paymentMethod) {

        // =========================================
        // 1. KIỂM TRA INPUT
        // =========================================

        if (userId <= 0) {
            return null;
        }

        if (paymentMethod == null
                || paymentMethod.trim().isEmpty()) {
            return null;
        }

        // =========================================
        // 2. KIỂM TRA USER
        // =========================================

        if (userService.findById(userId) == null) {
            return null;
        }

        // =========================================
        // 3. TÌM CART CỦA USER
        // =========================================

        Cart cart = null;

        List<Cart> carts = cartService.getAllCart();

        if (carts == null || carts.isEmpty()) {
            return null;
        }

        for (Cart item : carts) {

            if (item.getUserId() == userId) {
                cart = item;
                break;
            }
        }

        if (cart == null) {
            return null;
        }

        // =========================================
        // 4. LẤY CART ITEM
        // =========================================

        List<CartItem> cartItems =
                cartItemService.findByCartId(cart.getId());

        if (cartItems == null || cartItems.isEmpty()) {
            return null;
        }

        // =========================================
        // 5. KIỂM TRA PRODUCT
        //    + TÍNH TỔNG TIỀN
        // =========================================

        BigDecimal totalAmount = BigDecimal.ZERO;

        /*
         * Lưu Product vào list để tránh phải query
         * Product nhiều lần phía dưới.
         */
        List<Product> products = new java.util.ArrayList<>();

        for (CartItem cartItem : cartItems) {

            // Kiểm tra quantity
            if (cartItem.getQuantity() <= 0) {
                return null;
            }

            // Tìm product
            Product product =
                    productService.findById(
                            cartItem.getProductId()
                    );

            if (product == null) {
                return null;
            }

            // Kiểm tra price
            if (product.getPrice() == null
                    || product.getPrice()
                    .compareTo(BigDecimal.ZERO) < 0) {

                return null;
            }

            // Kiểm tra tồn kho
            if (cartItem.getQuantity()
                    > product.getQuantity()) {

                return null;
            }

            // Tính tiền item
            BigDecimal itemTotal =
                    product.getPrice()
                            .multiply(
                                    BigDecimal.valueOf(
                                            cartItem.getQuantity()
                                    )
                            );

            totalAmount =
                    totalAmount.add(itemTotal);

            products.add(product);
        }

        // =========================================
        // 6. KIỂM TRA TOTAL
        // =========================================

        if (totalAmount.compareTo(BigDecimal.ZERO) <= 0) {
            return null;
        }

        // =========================================
        // 7. BẮT ĐẦU TRANSACTION
        // =========================================

        Connection conn = null;

        try {

            conn = DBConnection.getConnection();

            // Tắt auto commit
            conn.setAutoCommit(false);

            // =====================================
            // 8. TẠO ORDER
            // =====================================

            Order order = new Order();

            order.setUser_id(userId);
            order.setTotal_amount(totalAmount);
            order.setStatus("PENDING");

            /*
             * createOrder trả về generated ID
             */
            int orderId =
                    orderService.createOrder(
                            order,
                            conn
                    );

            if (orderId <= 0) {
                throw new SQLException(
                        "Khong tao duoc Order"
                );
            }

            // Gán ID cho order
            order.setId(orderId);

            // =====================================
            // 9. TẠO ORDER ITEM
            // =====================================

            for (int i = 0; i < cartItems.size(); i++) {

                CartItem cartItem =
                        cartItems.get(i);

                Product product =
                        products.get(i);

                OrderItem orderItem =
                        new OrderItem();

                orderItem.setOrderId(orderId);

                orderItem.setProductId(
                        product.getId()
                );

                orderItem.setQuantity(
                        cartItem.getQuantity()
                );

                /*
                 * Lưu giá tại thời điểm mua.
                 */
                orderItem.setPrice(
                        product.getPrice()
                );

                int itemResult =
                        orderItemService.insert(
                                orderItem,
                                conn
                        );

                if (itemResult <= 0) {
                    throw new SQLException(
                            "Khong tao duoc OrderItem"
                    );
                }
            }

            // =====================================
            // 10. TRỪ TỒN KHO
            // =====================================

            for (int i = 0; i < cartItems.size(); i++) {

                CartItem cartItem =
                        cartItems.get(i);

                Product product =
                        products.get(i);

                int newQuantity =
                        product.getQuantity()
                                - cartItem.getQuantity();

                if (newQuantity < 0) {
                    throw new SQLException(
                            "Khong du ton kho"
                    );
                }

                product.setQuantity(newQuantity);

                int updateResult =
                        productService.updateProduct(
                                product,
                                conn
                        );

                if (updateResult <= 0) {
                    throw new SQLException(
                            "Khong cap nhat duoc Product"
                    );
                }
            }

            // =====================================
            // 11. TẠO PAYMENT
            // =====================================

            Payment payment =
                    new Payment();

            payment.setOrderId(orderId);

            payment.setPaymentMethod(
                    paymentMethod.trim()
            );

            payment.setPaymentStatus(
                    "PENDING"
            );

            int paymentResult =
                    paymentService.createPayment(
                            payment,
                            conn
                    );

            if (paymentResult <= 0) {
                throw new SQLException(
                        "Khong tao duoc Payment"
                );
            }

            // =====================================
            // 12. XÓA CART ITEM
            // =====================================

            int deletedRows = cartItemService.deleteByCartId(
                    cart.getId(),
                    conn
            );

            if (deletedRows != cartItems.size()) {
                throw new SQLException("Khong xoa duoc CartItem");
            }

            // =====================================
            // 13. COMMIT
            // =====================================

            conn.commit();

            return order;

        } catch (Exception e) {

            // =====================================
            // 14. ROLLBACK
            // =====================================

            if (conn != null) {

                try {
                    conn.rollback();
                } catch (SQLException rollbackException) {
                    rollbackException.printStackTrace();
                }
            }

            e.printStackTrace();

            return null;

        } finally {

            // =====================================
            // 15. ĐÓNG CONNECTION
            // =====================================

            if (conn != null) {

                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}