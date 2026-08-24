package org.example.e_com.service;

import org.example.e_com.model.Category;

import java.util.ArrayList;
import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    Category findById(int id);
    Boolean addCategory(Category category);
    Boolean updateCategory(Category category);
    Boolean delete(int id);

}
