package org.example.e_com.business;

import org.example.e_com.model.Product;

import java.util.List;

public interface ProductBusiness {
    List<Product> getAllProduct();
    Product getProductById(int id);
    boolean addProduct(Product product);
    boolean updateProduct(Product product);
    boolean deleteProduct(int id);

}
