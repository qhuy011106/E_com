package org.example.e_com.dao.impl;

import org.example.e_com.dao.OrderDao;
import org.example.e_com.model.Order;
import org.example.e_com.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    @Override
    public List<Order> getAllOrder() {
        List<Order> orders = new ArrayList<>();

        try {
            Connection conn = DBConnection.getConnection();

            String sql = "SELECT * FROM orders";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                orders.add(new Order(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getBigDecimal("total_amount"),
                        rs.getString("status"),
                        rs.getTimestamp("created_at")
                ));
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    @Override
    public Order findById(int id) {
        Order order = null;

        try {
            Connection conn = DBConnection.getConnection();

            String sql = "SELECT * FROM orders WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                order = new Order(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getBigDecimal("total_amount"),
                        rs.getString("status"),
                        rs.getTimestamp("created_at")
                );
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }

    @Override
    public List<Order> findByUserId(int userId) {
        List<Order> orders = new ArrayList<>();

        try {
            Connection conn = DBConnection.getConnection();

            String sql = "SELECT * FROM orders WHERE user_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                orders.add(new Order(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getBigDecimal("total_amount"),
                        rs.getString("status"),
                        rs.getTimestamp("created_at")
                ));
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    @Override
    public int insert(Order order) {
        int row = 0;

        try {
            Connection conn = DBConnection.getConnection();

            String sql = """
                    INSERT INTO orders(user_id, total_amount, status)
                    VALUES (?, ?, ?)
                    """;

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, order.getUser_id());
            ps.setBigDecimal(2, order.getTotal_amount());
            ps.setString(3, order.getStatus());

            row = ps.executeUpdate();

            ps.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return row;
    }

    @Override
    public int update(Order order) {
        int row = 0;

        try {
            Connection conn = DBConnection.getConnection();

            String sql = """
                    UPDATE orders
                    SET user_id = ?,
                        total_amount = ?,
                        status = ?
                    WHERE id = ?
                    """;

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, order.getUser_id());
            ps.setBigDecimal(2, order.getTotal_amount());
            ps.setString(3, order.getStatus());
            ps.setInt(4, order.getId());

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

            String sql = "DELETE FROM orders WHERE id = ?";
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