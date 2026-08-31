package org.example.e_com.business.Impl;

import org.example.e_com.business.ProductBusiness;
import org.example.e_com.model.Product;
import org.example.e_com.service.ProductService;
import org.example.e_com.service.impl.ProductServiceImpl;

import java.math.BigDecimal;
import java.util.List;

public class ProductBusinessImpl implements ProductBusiness {

    private final ProductService productService;

    public ProductBusinessImpl() {
        this.productService = new ProductServiceImpl();
    }

    @Override
    public List<Product> getAllProduct() {
        return productService.getAllProduct();
    }

    @Override
    public Product getProductById(int id) {
        if (id <= 0) {
            return null;
        }

        return productService.findById(id);
    }

    @Override
    public boolean addProduct(Product product) {

        if (product == null) {
            return false;
        }

        // Kiểm tra tên
        if (product.getName() == null
                || product.getName().trim().isEmpty()) {
            return false;
        }

        // Kiểm tra price
        if (product.getPrice() == null
                || product.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            return false;
        }

        // Kiểm tra quantity
        if (product.getQuantity() < 0) {
            return false;
        }

        // Kiểm tra category
        if (product.getCategory_id() <= 0) {
            return false;
        }

        return productService.addProduct(product);
    }

    @Override
    public boolean updateProduct(Product product) {

        if (product == null
                || product.getId() <= 0) {
            return false;
        }

        // Kiểm tra tên
        if (product.getName() == null
                || product.getName().trim().isEmpty()) {
            return false;
        }

        // Kiểm tra price
        if (product.getPrice() == null
                || product.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            return false;
        }

        // Kiểm tra quantity
        if (product.getQuantity() < 0) {
            return false;
        }

        // Kiểm tra category
        if (product.getCategory_id() <= 0) {
            return false;
        }

        return productService.updateProduct(product);
    }

    @Override
    public boolean deleteProduct(int id) {

        if (id <= 0) {
            return false;
        }

        Product product =
                productService.findById(id);

        if (product == null) {
            return false;
        }

        return productService.deleteProduct(id);
    }
}