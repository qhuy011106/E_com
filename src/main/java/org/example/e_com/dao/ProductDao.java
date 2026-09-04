package org.example.e_com.dao;

import org.example.e_com.model.Product;

import java.sql.Connection;
import java.util.List;

public interface ProductDao {
    List<Product> getAllProduct();
    Product findById(int id);
    int insert(Product product);
    int update(Product product);
    int delete(int id);
    int update(Product product, Connection conn);

    List<Product> findByCategoryId(int categoryId);
}
