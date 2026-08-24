package org.example.e_com.service.impl;

import org.example.e_com.dao.CategoryDao;
import org.example.e_com.dao.impl.CategoryDaoImpl;
import org.example.e_com.model.Category;
import org.example.e_com.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
   private final  CategoryDao categorydao;
   public CategoryServiceImpl(){
       this.categorydao = new CategoryDaoImpl() {
       };
   }

    @Override
    public List<Category> getAllCategories() {
        return categorydao.getAllCategory();
    }

    @Override
    public Category findById(int id) {

       if(id <= 0 ) return null;
       return categorydao.findById(id);
    }

    @Override
    public Boolean addCategory(Category category) {
        if(category == null ||  category.getName() == null || category.getName().trim().isEmpty() ) return null;
        return categorydao.insert(category) > 0;
    }

    @Override
    public Boolean updateCategory(Category category) {
        if(category == null || category.getId() <= 0|| category.getName() == null || category.getName().trim().isEmpty() ) return null;
        return categorydao.update(category) > 0;

    }

    @Override
    public Boolean delete(int id) {
        if(id <= 0 ) return false;
        Category category = categorydao.findById(id);
        if(category == null) return false;
        return categorydao.delete(id) > 0;
    }
}
