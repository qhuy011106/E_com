package org.example.e_com.business;

import org.example.e_com.dto.ProductDTO;
import org.example.e_com.model.Product;

import java.util.List;

public interface ProductBusiness {
    List<Product> getAllProduct();
    Product getProductById(int id);
    boolean addProduct(Product product);
    boolean updateProduct(Product product);
    boolean deleteProduct(int id);
    //them dto
    List<ProductDTO> getAllProductDTO();
    ProductDTO getProductDTOById(int id);
    List<ProductDTO> getProductsByCategory(int categoryId);
    List<ProductDTO> searchProducts(String keyword);
    List<ProductDTO> getBestSellingProducts(int limit);
}
