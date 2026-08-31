package org.example.e_com.dao.impl;

import org.example.e_com.dao.CartItemDao;
import org.example.e_com.model.CartItem;
import org.example.e_com.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class

CartItemDaoImpl implements CartItemDao {

    @Override
    public List<CartItem> getallCartItem() {
        List<CartItem> cartitems = new ArrayList<>();

        try {
            Connection conn = DBConnection.getConnection();

            String sql = "SELECT * FROM cart_items";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                cartitems.add(new CartItem(
                        rs.getInt("id"),
                        rs.getInt("cart_id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity")
                ));
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cartitems;
    }

    @Override
    public CartItem findById(int id) {
        CartItem cartItem = null;

        try {
            Connection conn = DBConnection.getConnection();

            String sql = "SELECT * FROM cart_items WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cartItem = new CartItem(
                        rs.getInt("id"),
                        rs.getInt("cart_id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity")
                );
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cartItem;
    }

    @Override
    public List<CartItem> findByCartId(int cartId) {
        List<CartItem> cartitems = new ArrayList<>();

        try {
            Connection conn = DBConnection.getConnection();

            String sql = "SELECT * FROM cart_items WHERE cart_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, cartId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                cartitems.add(new CartItem(
                        rs.getInt("id"),
                        rs.getInt("cart_id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity")
                ));
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cartitems;
    }

    @Override
    public int insert(CartItem cartItem) {
        int row = 0;

        try {
            Connection conn = DBConnection.getConnection();

            String sql = """
                    INSERT INTO cart_items(cart_id, product_id, quantity)
                    VALUES (?, ?, ?)
                    """;

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, cartItem.getCartId());
            ps.setInt(2, cartItem.getProductId());
            ps.setInt(3, cartItem.getQuantity());

            row = ps.executeUpdate();

            ps.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return row;
    }

    @Override
    public int update(CartItem cartItem) {
        int row = 0;

        try {
            Connection conn = DBConnection.getConnection();

            String sql = """
                    UPDATE cart_items
                    SET cart_id = ?,
                        product_id = ?,
                        quantity = ?
                    WHERE id = ?
                    """;

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, cartItem.getCartId());
            ps.setInt(2, cartItem.getProductId());
            ps.setInt(3, cartItem.getQuantity());
            ps.setInt(4, cartItem.getId());

            row = ps.executeUpdate();

            ps.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return row;
    }

    @Override
    public int delete(int id) {
        int row = 0;

        try {
            Connection conn = DBConnection.getConnection();

            String sql = "DELETE FROM cart_items WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            row = ps.executeUpdate();

            ps.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return row;
    }

    @Override
    public int deleteByCartId(int cartId, Connection conn) {

        String sql = "DELETE FROM cart_items WHERE cart_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cartId);

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Loi khi xoa CartItem trong transaction", e);
        }
    }
}