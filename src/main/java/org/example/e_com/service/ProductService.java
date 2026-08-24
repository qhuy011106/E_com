package org.example.e_com.service;

import org.example.e_com.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProduct();

    Product findById(int id);

    boolean addProduct(Product product);

    boolean updateProduct(Product product);

    boolean deleteProduct(int id);
}