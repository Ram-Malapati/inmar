package com.inmar.app.service;

import com.inmar.app.jpa.model.SubCategory;
import com.inmar.app.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
public class SubCategoryService {
    @Autowired
    private SubCategoryRepository subCategoryRepository;

    /**
     * This method used for save the subCategory data
     * @param subCategoryName
     */
    public void saveSubCategoryData(String subCategoryName){
        SubCategory subCategory=new SubCategory();
        SubCategory existedSubCategory=subCategoryRepository.findBySubCategory(subCategoryName);
        if(Objects.isNull(existedSubCategory)) {
            subCategory.setSubCategory(subCategoryName);
            subCategory.setDescription("Test Description for" + subCategoryName);
            subCategoryRepository.save(subCategory);
        }
    }

    public SubCategory getSubCategoryByName(String name) {
        return subCategoryRepository.findBySubCategory(name);
    }

    public SubCategory getSubCategoryById(long id) {
        return subCategoryRepository.findById(id).orElse(null);
    }

    public void deleteAllSubCategories(){
        subCategoryRepository.deleteAll();
    }

    public void getAllSubCategories(Set<String> allSubCategories) {
        for(String subCategory:allSubCategories){
            saveSubCategoryData(subCategory);
        }
    }
}
