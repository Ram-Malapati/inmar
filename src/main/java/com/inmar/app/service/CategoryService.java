package com.inmar.app.service;

import com.inmar.app.jpa.model.Category;
import com.inmar.app.jpa.model.Department;
import com.inmar.app.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category getCategoryByName(String categoryName) {

        return categoryRepository.findByCategory(categoryName);
    }

    public Category getCategoryById(long id) {

        return categoryRepository.findById(id).orElse(null);
    }

    /**
     * this method used for saveCategory data
     * @param categoryName
     */
    public void saveCategory(String categoryName) {
        Category category = new Category();
        Category existedCategory = categoryRepository.findByCategory(categoryName);
        if (Objects.isNull(existedCategory)) {
            category.setCategory(categoryName);
            category.setDescription("Test Description for" + categoryName);
            categoryRepository.save(category);
        }
    }

    public void deleteAllCategories() {
        categoryRepository.deleteAll();
    }

    public void saveAllCategories(Set<String> allCategories) {
        for(String category:allCategories){
            saveCategory(category);
        }
    }
}
