package com.inmar.app.repository;

import com.inmar.app.jpa.model.SkuData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkuDataRepository extends JpaRepository<SkuData, Long> {
    @Query(value = "select sku.skuDataId from SkuData sku WHERE sku.location = :location AND sku.department = :department AND sku.category = :category AND sku.subcategory = :subcategory")
    public List<Long> findSkuDetailsByMetaDataParameters(@Param("location") String location, @Param("department") String department, @Param("category") String category, @Param("subcategory") String subcategory);
}
