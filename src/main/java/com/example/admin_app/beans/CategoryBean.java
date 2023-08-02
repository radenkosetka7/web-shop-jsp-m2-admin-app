package com.example.admin_app.beans;

import com.example.admin_app.dao.CategoryDAO;
import com.example.admin_app.dto.Category;

import java.io.Serializable;
import java.util.List;

public class CategoryBean implements Serializable {

    public CategoryBean() {
    }

    public boolean insertCategory(Category category)
    {
        return CategoryDAO.insertCategory(category);
    }

    public boolean updateCategory(Category category)
    {
        return CategoryDAO.updateCategory(category);
    }

    public List<Category> getAllCategories()
    {
        return CategoryDAO.getAllCategories();
    }

    public Category getAllCategoryById(Integer id)
    {
        return CategoryDAO.getAllCategoryById(id);
    }

    public void deleteCategory(Category category)
    {
        CategoryDAO.deleteCategory(category);
    }
}
