package org.example.e_com;

import org.example.e_com.business.CheckoutBusiness;
import org.example.e_com.business.Impl.CheckoutBusinessImpl;
import org.example.e_com.model.Order;

import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        CheckoutBusiness checkoutBusiness =
                new CheckoutBusinessImpl();

        while (true) {

            System.out.println();
            System.out.println("================================");
            System.out.println("       E-COM TEST SYSTEM");
            System.out.println("================================");
            System.out.println("1. Test Auth");
            System.out.println("2. Test User");
            System.out.println("3. Test Category");
            System.out.println("4. Test Product");
            System.out.println("5. Test Cart");
            System.out.println("6. Test CartItem");
            System.out.println("7. Test Order");
            System.out.println("8. Test OrderItem");
            System.out.println("9. Test Payment");
            System.out.println("10. Test Review");
            System.out.println("11. Test Checkout");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    testAuth();
                    break;

                case 2:
                    testUser();
                    break;

                case 3:
                    testCategory();
                    break;

                case 4:
                    testProduct();
                    break;

                case 5:
                    testCart();
                    break;

                case 6:
                    testCartItem();
                    break;

                case 7:
                    testOrder();
                    break;

                case 8:
                    testOrderItem();
                    break;

                case 9:
                    testPayment();
                    break;

                case 10:
                    testReview();
                    break;

                case 11:
                    testCheckout(checkoutBusiness);
                    break;

                case 0:
                    System.out.println("Thoat chuong trinh!");
                    return;

                default:
                    System.out.println("Lua chon khong hop le!");
            }
        }
    }

    // =====================================================
    // AUTH
    // =====================================================

    private static void testAuth() {

        System.out.println();
        System.out.println("===== AUTH TEST =====");

        // Đặt code test Auth cũ của bạn vào đây

        System.out.println("Auth test");
    }

    // =====================================================
    // USER
    // =====================================================

    private static void testUser() {

        System.out.println();
        System.out.println("===== USER TEST =====");

        // Đặt code User test cũ của bạn vào đây

        System.out.println("User test");
    }

    // =====================================================
    // CATEGORY
    // =====================================================

    private static void testCategory() {

        System.out.println();
        System.out.println("===== CATEGORY TEST =====");

        // Đặt code Category test cũ của bạn vào đây

        System.out.println("Category test");
    }

    // =====================================================
    // PRODUCT
    // =====================================================

    private static void testProduct() {

        System.out.println();
        System.out.println("===== PRODUCT TEST =====");

        // Đặt code Product test cũ của bạn vào đây

        System.out.println("Product test");
    }

    // =====================================================
    // CART
    // =====================================================

    private static void testCart() {

        System.out.println();
        System.out.println("===== CART TEST =====");

        // Đặt code Cart test cũ của bạn vào đây

        System.out.println("Cart test");
    }

    // =====================================================
    // CART ITEM
    // =====================================================

    private static void testCartItem() {

        System.out.println();
        System.out.println("===== CART ITEM TEST =====");

        // Đặt code CartItem test cũ của bạn vào đây

        System.out.println("CartItem test");
    }

    // =====================================================
    // ORDER
    // =====================================================

    private static void testOrder() {

        System.out.println();
        System.out.println("===== ORDER TEST =====");

        // Đặt code Order test cũ của bạn vào đây

        System.out.println("Order test");
    }

    // =====================================================
    // ORDER ITEM
    // =====================================================

    private static void testOrderItem() {

        System.out.println();
        System.out.println("===== ORDER ITEM TEST =====");

        // Đặt code OrderItem test cũ của bạn vào đây

        System.out.println("OrderItem test");
    }

    // =====================================================
    // PAYMENT
    // =====================================================

    private static void testPayment() {

        System.out.println();
        System.out.println("===== PAYMENT TEST =====");

        // Đặt code Payment test cũ của bạn vào đây

        System.out.println("Payment test");
    }

    // =====================================================
    // REVIEW
    // =====================================================

    private static void testReview() {

        System.out.println();
        System.out.println("===== REVIEW TEST =====");

        // Đặt code Review test cũ của bạn vào đây

        System.out.println("Review test");
    }

    // =====================================================
    // CHECKOUT
    // =====================================================

    private static void testCheckout(
            CheckoutBusiness checkoutBusiness) {

        System.out.println();
        System.out.println("===== CHECKOUT TEST =====");

        System.out.print("Nhap User ID: ");
        int userId = sc.nextInt();
        sc.nextLine();

        System.out.print("Nhap phuong thuc thanh toan: ");
        String paymentMethod = sc.nextLine();

        Order order =
                checkoutBusiness.checkout(
                        userId,
                        paymentMethod
                );

        if (order == null) {

            System.out.println();
            System.out.println("Checkout that bai!");

        } else {

            System.out.println();
            System.out.println("Checkout thanh cong!");
            System.out.println("--------------------------------");
            System.out.println("Order ID: "
                    + order.getId());
            System.out.println("User ID: "
                    + order.getUser_id());
            System.out.println("Total amount: "
                    + order.getTotal_amount());
            System.out.println("Status: "
                    + order.getStatus());
            System.out.println("--------------------------------");
        }
    }
}