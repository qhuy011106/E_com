package org.example.e_com.business.Impl;

import org.example.e_com.business.ProductBusiness;
import org.example.e_com.dto.ProductDTO;
import org.example.e_com.model.Category;
import org.example.e_com.model.Product;
import org.example.e_com.service.CategoryService;
import org.example.e_com.service.ProductService;
import org.example.e_com.service.impl.CategoryServiceImpl;
import org.example.e_com.service.impl.ProductServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductBusinessImpl implements ProductBusiness {

    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductBusinessImpl() {
        this.productService = new ProductServiceImpl();
        this.categoryService = new CategoryServiceImpl();
    }

    // ===== CRUD CŨ =====
    @Override
    public List<Product> getAllProduct() {
        return productService.getAllProduct();
    }

    @Override
    public Product getProductById(int id) {
        if (id <= 0) return null;
        return productService.findById(id);
    }

    @Override
    public boolean addProduct(Product product) {
        if (product == null) return false;
        if (product.getName() == null || product.getName().trim().isEmpty()) return false;
        if (product.getPrice() == null || product.getPrice().compareTo(BigDecimal.ZERO) < 0) return false;
        if (product.getQuantity() < 0) return false;
        if (product.getCategory_id() <= 0) return false;

        return productService.addProduct(product);
    }

    @Override
    public boolean updateProduct(Product product) {
        if (product == null || product.getId() <= 0) return false;
        if (product.getName() == null || product.getName().trim().isEmpty()) return false;
        if (product.getPrice() == null || product.getPrice().compareTo(BigDecimal.ZERO) < 0) return false;
        if (product.getQuantity() < 0) return false;
        if (product.getCategory_id() <= 0) return false;

        return productService.updateProduct(product);
    }

    @Override
    public boolean deleteProduct(int id) {
        if (id <= 0) return false;
        Product product = productService.findById(id);
        if (product == null) return false;
        return productService.deleteProduct(id);
    }

    // ===== SỬ DỤNG DTO =====
    @Override
    public List<ProductDTO> getAllProductDTO() {
        List<Product> products = productService.getAllProduct();
        return convertToDTOList(products);
    }

    @Override
    public ProductDTO getProductDTOById(int id) {
        Product product = productService.findById(id);
        if (product == null) return null;
        return convertToDTO(product);
    }

    @Override
    public List<ProductDTO> getProductsByCategory(int categoryId) {
        // ✅ SỬA: Gọi đúng method findByCategoryId
        List<Product> products = productService.findByCategoryId(categoryId);
        return convertToDTOList(products);
    }

    @Override
    public List<ProductDTO> searchProducts(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllProductDTO();
        }

        List<Product> products = productService.getAllProduct();
        List<ProductDTO> result = new ArrayList<>();
        String searchKey = keyword.trim().toLowerCase();

        for (Product product : products) {
            // ✅ SỬA: So sánh đúng với tên sản phẩm
            if (product.getName().toLowerCase().contains(searchKey)) {
                result.add(convertToDTO(product));
            }
        }

        return result;
    }

    @Override
    public List<ProductDTO> getBestSellingProducts(int limit) {
        // TODO: Cần thêm method thống kê trong DAO
        // Tạm thời trả về tất cả sản phẩm
        List<ProductDTO> all = getAllProductDTO();
        if (all.size() <= limit) {
            return all;  // ✅ SỬA: Thêm return
        }
        return all.subList(0, limit);
    }

    // ===== HELPER METHODS =====
    private ProductDTO convertToDTO(Product product) {
        String categoryName = "Không có danh mục";
        try {
            Category category = categoryService.findById(product.getCategory_id());
            if (category != null) {
                categoryName = category.getName();
            }
        } catch (Exception e) {
            // Bỏ qua nếu không tìm thấy category
        }

        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getQuantity(),
                categoryName
        );
    }

    private List<ProductDTO> convertToDTOList(List<Product> products) {
        List<ProductDTO> dtos = new ArrayList<>();
        for (Product product : products) {
            dtos.add(convertToDTO(product));
        }
        return dtos;
    }
}