package com.inmar.app.repository;

import com.inmar.app.jpa.model.Category;
import com.inmar.app.jpa.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    SubCategory findBySubCategory(String subCategory);

}
