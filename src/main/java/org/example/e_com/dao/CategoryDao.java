package org.example.e_com.dao;

import org.example.e_com.model.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> getAllCategory();

    Category findById(int id);

    int insert(Category category);

    int update(Category category);
   int delete(int id);
    }
