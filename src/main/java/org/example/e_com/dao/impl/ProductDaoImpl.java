package org.example.e_com.dao.impl;

import org.example.e_com.dao.ProductDao;
import org.example.e_com.model.Product;
import org.example.e_com.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    @Override
    public List<Product> getAllProduct() {
        List<Product> p = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "select * from products";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id =  rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                int category_id = rs.getInt("category_id");
                Product a = new Product(id,name,price,quantity,category_id);
                p.add(a);
            }
            rs.close();
            ps.close();
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return p;
    }

    @Override
    public Product findById(int id) {
        Product pro =   null;
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "select * from products where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                pro = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getInt("category_id")
                );
            }
            rs.close();
            ps.close();
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return pro;
    }

    @Override
    public int insert(Product product) {
        int row = 0;
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "insert into products(id,name,price,quantity,category_id) values (?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, product.getId());
            ps.setString(2,product.getName());
            ps.setDouble(3,product.getPrice());
            ps.setInt(4,product.getQuantity());
            ps.setInt(5,product.getCategory_id());
            row = ps.executeUpdate();
            ps.close();
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }

        return row;
    }

    @Override
    public int update(Product product) {
        int row = 0;
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "update products set name = ?, price = ?, quantity = ?, category_id = ? where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, product.getName());
            ps.setDouble(2,product.getPrice());
            ps.setInt(3, product.getQuantity());
            ps.setInt(4,product.getCategory_id());
            ps.setInt(5, product.getId());
            row = ps.executeUpdate();
            ps.close();
            conn.close();

        }catch(SQLException e){
            e.printStackTrace();
        }

        return row;
    }

    @Override
    public int delete(int id) {
        int row = 0;
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "delete from products where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            row = ps.executeUpdate();
            ps.close();
            conn.close();

    }catch(SQLException e){
            e.printStackTrace();
        }
        return row;
    }
}
