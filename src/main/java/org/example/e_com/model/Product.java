package org.example.e_com.model;

import java.math.BigDecimal;

public class Product {
    private int id;
    private String name;
    private BigDecimal price;
    private int quantity;
    private int category_id;
    public Product(){};
    public Product(int id, String name, BigDecimal price, int quantity, int category_id){
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category_id = category_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getCategory_id() {
        return category_id;
    }



    public void setCategory_id(int categories) {
        this.category_id = categories;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", category_id=" + category_id +
                '}';
    }
}
