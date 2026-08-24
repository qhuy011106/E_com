package org.example.e_com.business.Impl;

import org.example.e_com.business.ProductBusiness;
import org.example.e_com.model.Product;
import org.example.e_com.service.ProductService;
import org.example.e_com.service.impl.ProductServiceImpl;

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
        if(id <= 0) return null;
        return productService.findById(id);
    }

    @Override
    public boolean addProduct(Product product) {
        if (product == null) {
            return false;
        }

        if (product.getName() == null
                || product.getName().trim().isEmpty()) {
            return false;
        }

        if (product.getPrice() < 0) {
            return false;
        }

        if (product.getQuantity() < 0) {
            return false;
        }

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

        if (product.getName() == null
                || product.getName().trim().isEmpty()) {
            return false;
        }

        if (product.getPrice() < 0
                || product.getQuantity() < 0
                || product.getCategory_id() <= 0) {
            return false;
        }
        return productService.updateProduct(product);
    }

    @Override
    public boolean deleteProduct(int id) {
        if(id <= 0) return false;
        Product product = productService.findById(id);
        if(product == null) return false;
        return productService.deleteProduct(id);
    }
}
