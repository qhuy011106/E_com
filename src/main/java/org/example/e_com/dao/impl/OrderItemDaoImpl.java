package org.example.e_com.dao.impl;

import org.example.e_com.dao.OrderItemDao;
import org.example.e_com.model.OrderItem;
import org.example.e_com.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDaoImpl implements OrderItemDao {

    @Override
    public List<OrderItem> getAllOrderItem() {

        List<OrderItem> orderItems = new ArrayList<>();

        try {
            Connection conn = DBConnection.getConnection();

            String sql = "SELECT * FROM order_items";

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                orderItems.add(new OrderItem(
                        rs.getInt("id"),
                        rs.getInt("order_id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getBigDecimal("price")
                ));
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderItems;
    }

    @Override
    public OrderItem findById(int id) {

        OrderItem orderItem = null;

        try {
            Connection conn = DBConnection.getConnection();

            String sql = "SELECT * FROM order_items WHERE id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                orderItem = new OrderItem(
                        rs.getInt("id"),
                        rs.getInt("order_id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getBigDecimal("price")
                );
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderItem;
    }

    @Override
    public List<OrderItem> findByOrderId(int orderId) {

        List<OrderItem> orderItems = new ArrayList<>();

        try {
            Connection conn = DBConnection.getConnection();

            String sql = "SELECT * FROM order_items WHERE order_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orderId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                orderItems.add(new OrderItem(
                        rs.getInt("id"),
                        rs.getInt("order_id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getBigDecimal("price")
                ));
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderItems;
    }

    @Override
    public int insert(OrderItem orderItem) {

        int row = 0;

        try {
            Connection conn = DBConnection.getConnection();

            String sql = """
                    INSERT INTO order_items
                    (order_id, product_id, quantity, price)
                    VALUES (?, ?, ?, ?)
                    """;

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, orderItem.getOrderId());
            ps.setInt(2, orderItem.getProductId());
            ps.setInt(3, orderItem.getQuantity());
            ps.setBigDecimal(4, orderItem.getPrice());

            row = ps.executeUpdate();

            ps.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return row;
    }

    @Override
    public int update(OrderItem orderItem) {

        int row = 0;

        try {
            Connection conn = DBConnection.getConnection();

            String sql = """
                    UPDATE order_items
                    SET order_id = ?,
                        product_id = ?,
                        quantity = ?,
                        price = ?
                    WHERE id = ?
                    """;

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, orderItem.getOrderId());
            ps.setInt(2, orderItem.getProductId());
            ps.setInt(3, orderItem.getQuantity());
            ps.setBigDecimal(4, orderItem.getPrice());
            ps.setInt(5, orderItem.getId());

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

            String sql = "DELETE FROM order_items WHERE id = ?";

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
    public int insert(OrderItem orderItem, Connection conn) {

        String sql = """
            INSERT INTO order_items
            (order_id, product_id, quantity, price)
            VALUES (?, ?, ?, ?)
            """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderItem.getOrderId());
            ps.setInt(2, orderItem.getProductId());
            ps.setInt(3, orderItem.getQuantity());
            ps.setBigDecimal(4, orderItem.getPrice());

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Loi khi tao OrderItem", e);
        }
    }
}