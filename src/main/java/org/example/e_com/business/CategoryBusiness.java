package org.example.e_com.business;

import org.example.e_com.model.Category;

import java.util.List;

public interface CategoryBusiness {

    List<Category> getAllCategory();

    Category getCategoryById(int id);

    boolean addCategory(Category category);

    boolean updateCategory(Category category);

    boolean deleteCategory(int id);
}