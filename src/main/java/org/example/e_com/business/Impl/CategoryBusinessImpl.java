package org.example.e_com.business.Impl;

import org.example.e_com.business.CategoryBusiness;
import org.example.e_com.model.Category;
import org.example.e_com.service.CategoryService;
import org.example.e_com.service.impl.CategoryServiceImpl;

import java.util.List;

public class CategoryBusinessImpl implements CategoryBusiness {

    private final CategoryService categoryService;

    public CategoryBusinessImpl() {
        this.categoryService = new CategoryServiceImpl();
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryService.getAllCategories();
    }

    @Override
    public Category getCategoryById(int id) {

        if (id <= 0) {
            return null;
        }

        return categoryService.findById(id);
    }

    @Override
    public boolean addCategory(Category category) {

        if (category == null) {
            return false;
        }

        // Kiểm tra tên
        if (category.getName() == null
                || category.getName().trim().isEmpty()) {
            return false;
        }

        // Kiểm tra description
        if (category.getDescription() == null
                || category.getDescription().trim().isEmpty()) {
            return false;
        }

        // Kiểm tra trùng tên
        for (Category item : categoryService.getAllCategories()) {

            if (item.getName().equalsIgnoreCase(
                    category.getName().trim())) {

                return false;
            }
        }

        return categoryService.addCategory(category);
    }

    @Override
    public boolean updateCategory(Category category) {

        if (category == null
                || category.getId() <= 0) {
            return false;
        }

        // Kiểm tra tên
        if (category.getName() == null
                || category.getName().trim().isEmpty()) {
            return false;
        }

        // Kiểm tra description
        if (category.getDescription() == null
                || category.getDescription().trim().isEmpty()) {
            return false;
        }

        // Kiểm tra Category tồn tại
        Category existing =
                categoryService.findById(category.getId());

        if (existing == null) {
            return false;
        }

        // Kiểm tra trùng tên với Category khác
        for (Category item : categoryService.getAllCategories()) {

            if (item.getId() != category.getId()
                    && item.getName().equalsIgnoreCase(
                    category.getName().trim())) {

                return false;
            }
        }

        return categoryService.updateCategory(category);
    }

    @Override
    public boolean deleteCategory(int id) {

        if (id <= 0) {
            return false;
        }

        Category category =
                categoryService.findById(id);

        if (category == null) {
            return false;
        }

        return categoryService.delete(id);
    }
}