package org.example.e_com.service;

import org.example.e_com.model.Product;

import java.sql.Connection;
import java.util.List;

public interface ProductService {

    List<Product> getAllProduct();

    Product findById(int id);

    boolean addProduct(Product product);

    boolean updateProduct(Product product);

    boolean deleteProduct(int id);
    int updateProduct(Product product, Connection conn);
    List<Product> findByCategoryId(int categoryId);
}