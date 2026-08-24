package org.example.e_com.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Order {
    private int id;
    private int user_id;
    private BigDecimal total_amount;
    private String status;
    private Timestamp createAt;
    public Order(){};

    public Order(int id, int user_id, BigDecimal total_amount,String status, Timestamp createAt) {
        this.id = id;
        this.user_id = user_id;
        this.total_amount = total_amount;
        this.status = status;
        this.createAt = createAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public BigDecimal getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(BigDecimal total_amount) {
        this.total_amount = total_amount;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", total_amount=" + total_amount +
                ", status='" + status + '\'' +
                ", createAt=" + createAt +
                '}';
    }


}
