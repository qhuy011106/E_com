package org.example.e_com.dao.impl;

import org.example.e_com.dao.PaymentDao;
import org.example.e_com.model.Payment;
import org.example.e_com.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDaoImpl implements PaymentDao {

    @Override
    public List<Payment> getAllPayment() {

        List<Payment> payments = new ArrayList<>();

        try {
            Connection conn = DBConnection.getConnection();

            String sql = "SELECT * FROM payments";

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                payments.add(new Payment(
                        rs.getInt("id"),
                        rs.getInt("order_id"),
                        rs.getString("payment_method"),
                        rs.getString("payment_status"),
                        rs.getString("transaction_code"),
                        rs.getTimestamp("paid_at")
                ));
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return payments;
    }

    @Override
    public Payment findById(int id) {

        Payment payment = null;

        try {
            Connection conn = DBConnection.getConnection();

            String sql = "SELECT * FROM payments WHERE id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                payment = new Payment(
                        rs.getInt("id"),
                        rs.getInt("order_id"),
                        rs.getString("payment_method"),
                        rs.getString("payment_status"),
                        rs.getString("transaction_code"),
                        rs.getTimestamp("paid_at")
                );
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return payment;
    }

    @Override
    public Payment findByOrderId(int orderId) {

        Payment payment = null;

        try {
            Connection conn = DBConnection.getConnection();

            String sql = "SELECT * FROM payments WHERE order_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orderId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                payment = new Payment(
                        rs.getInt("id"),
                        rs.getInt("order_id"),
                        rs.getString("payment_method"),
                        rs.getString("payment_status"),
                        rs.getString("transaction_code"),
                        rs.getTimestamp("paid_at")
                );
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return payment;
    }

    @Override
    public int insert(Payment payment) {

        int row = 0;

        try {
            Connection conn = DBConnection.getConnection();

            String sql = """
                    INSERT INTO payments
                    (order_id, payment_method, payment_status, transaction_code, paid_at)
                    VALUES (?, ?, ?, ?, ?)
                    """;

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, payment.getOrderId());
            ps.setString(2, payment.getPaymentMethod());
            ps.setString(3, payment.getPaymentStatus());
            ps.setString(4, payment.getTransactionCode());

            if (payment.getPaidAt() != null) {
                ps.setTimestamp(5, payment.getPaidAt());
            } else {
                ps.setNull(5, java.sql.Types.TIMESTAMP);
            }

            row = ps.executeUpdate();

            ps.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return row;
    }

    @Override
    public int update(Payment payment) {

        int row = 0;

        try {
            Connection conn = DBConnection.getConnection();

            String sql = """
                    UPDATE payments
                    SET order_id = ?,
                        payment_method = ?,
                        payment_status = ?,
                        transaction_code = ?,
                        paid_at = ?
                    WHERE id = ?
                    """;

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, payment.getOrderId());
            ps.setString(2, payment.getPaymentMethod());
            ps.setString(3, payment.getPaymentStatus());
            ps.setString(4, payment.getTransactionCode());

            if (payment.getPaidAt() != null) {
                ps.setTimestamp(5, payment.getPaidAt());
            } else {
                ps.setNull(5, java.sql.Types.TIMESTAMP);
            }

            ps.setInt(6, payment.getId());

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

            String sql = "DELETE FROM payments WHERE id = ?";

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