package org.example.e_com.dao.impl;

import org.example.e_com.dao.CartDao;
import org.example.e_com.model.Cart;
import org.example.e_com.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDaoImpl implements CartDao {

    @Override
    public List<Cart> getAllCart() {

        List<Cart> carts = new ArrayList<>();

        try {
            Connection conn = DBConnection.getConnection();

            String sql = "SELECT * FROM cart";

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Cart cart = new Cart(
                        rs.getInt("id"),
                        rs.getInt("user_id")
                );

                carts.add(cart);
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return carts;
    }

    @Override
    public Cart findById(int id) {

        Cart cart = null;

        try {
            Connection conn = DBConnection.getConnection();

            String sql = "SELECT * FROM cart WHERE id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                cart = new Cart(
                        rs.getInt("id"),
                        rs.getInt("user_id")
                );
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cart;
    }

    @Override
    public Cart findByUserId(int userId) {

        Cart cart = null;

        try {
            Connection conn = DBConnection.getConnection();

            String sql = "SELECT * FROM cart WHERE user_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                cart = new Cart(
                        rs.getInt("id"),
                        rs.getInt("user_id")
                );
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cart;
    }

    @Override
    public int insert(Cart cart) {

        int row = 0;

        try {
            Connection conn = DBConnection.getConnection();

            String sql = """
                    INSERT INTO cart(user_id)
                    VALUES (?)
                    """;

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, cart.getUserId());

            row = ps.executeUpdate();

            ps.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return row;
    }

    @Override
    public int update(Cart cart) {

        int row = 0;

        try {
            Connection conn = DBConnection.getConnection();

            String sql = """
                    UPDATE cart
                    SET user_id = ?
                    WHERE id = ?
                    """;

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, cart.getUserId());
            ps.setInt(2, cart.getId());

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

            String sql = "DELETE FROM cart WHERE id = ?";

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
}