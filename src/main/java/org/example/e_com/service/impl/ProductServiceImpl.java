package org.example.e_com.service.impl;

import org.example.e_com.dao.ProductDao;
import org.example.e_com.dao.impl.ProductDaoImpl;
import org.example.e_com.model.Product;
import org.example.e_com.service.ProductService;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;
    public ProductServiceImpl(){
        this.productDao = new ProductDaoImpl();
    }
    @Override
    public List<Product> getAllProduct() {
        return productDao.getAllProduct();
    }

    @Override
    public Product findById(int id) {
        if(id <= 0) return null;
        return productDao.findById(id);
    }

    @Override
    public boolean addProduct(Product product) {
        if(product == null || product.getId() <= 0 || product.getName() == null || product.getName().trim().isEmpty() ||product.getPrice().compareTo(BigDecimal.ZERO) < 0 || product.getQuantity() < 0 ) return false;
        return productDao.insert(product) > 0;
    }

    @Override
    public boolean updateProduct(Product product) {
        if(product == null || product.getName() == null || product.getName().trim().isEmpty() ||product.getPrice().compareTo(BigDecimal.ZERO) < 0 || product.getQuantity() < 0 ) return false;
        return productDao.update(product) > 0;
    }

    @Override
    public boolean deleteProduct(int id) {
        if(id < 0) return false;
        Product product = productDao.findById(id);
        if(product == null) return false;
        return productDao.delete(id) > 0;
    }

    @Override
    public int updateProduct(Product product, Connection conn) {

        if (product == null || conn == null) {
            return 0;
        }

        if (product.getId() <= 0
                || product.getName() == null
                || product.getName().trim().isEmpty()
                || product.getPrice() == null
                || product.getPrice().compareTo(BigDecimal.ZERO) < 0
                || product.getQuantity() < 0
                || product.getCategory_id() <= 0) {

            return 0;
        }

        return productDao.update(product, conn);
    }

    @Override
    public List<Product> findByCategoryId(int categoryId) {
        if (categoryId <= 0) return List.of();  // Trả về list rỗng
        return productDao.findByCategoryId(categoryId);
    }
}
