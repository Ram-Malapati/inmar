package com.inmar.app.service;

import com.inmar.app.dto.response.*;
import com.inmar.app.exception.MetaDataNotFoundException;
import com.inmar.app.jpa.model.*;
import com.inmar.app.repository.MetaDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class MetaDataService {

    Logger logger = LoggerFactory.getLogger(MetaDataService.class);
    @Autowired
    private MetaDataRepository metaDataRepository;
    @Autowired
    private LocationService locationService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SubCategoryService subCategoryService;


    /**
     * this method used for getDepartmentFromMetadata
     * @param locationId
     * @return
     */
    public DepartmentsResponse getDepartmentFromMetadata(long locationId) {
        logger.info("entered into getDepartmentFromMetadata()");
        DepartmentsResponse response = new DepartmentsResponse();
        Location locationData = locationService.getLocationById(locationId);
        if (Objects.nonNull(locationData)) {
            List<String> metadata = metaDataRepository.findDepartmentsFromMetaData(locationData.getLocation())
                    .stream()
                    .distinct()
                    .collect(Collectors.toList());
            List<Department> departments = metadata
                    .stream()
                    .filter(meta -> meta != null && !StringUtils.isEmpty(meta))
                    .map(meta -> departmentService.getDepartmentByName(meta))
                    .collect(Collectors.toList());
            List<DepartmentResponse> departmentResponses = departments
                    .stream()
                    .map(dept -> new DepartmentResponse(dept.getDepartmentId(), dept.getDepartment(), dept.getDescription()))
                    .collect(Collectors.toList());
            response.setDepartments(departmentResponses);
        } else {
            validateMetadata(new ArrayList<>());
        }
        logger.info("fetched departments data and it returns the DepartmentsResponse object");
        return response;
    }

    public CategoriesResponse getCategoriesFromMetadata(long locationId, long departmentId) {
        logger.info("entered into getCategoriesFromMetadata()");
        CategoriesResponse response = new CategoriesResponse();
        Location locationData = locationService.getLocationById(locationId);
        Department departmentData = departmentService.getDepartmentById(departmentId);
        if (Objects.nonNull(locationData) && Objects.nonNull(departmentData)) {
            List<String> metadata = metaDataRepository.findCategoriesFromMetaData(locationData.getLocation(), departmentData.getDepartment())
                    .stream()
                    .distinct()
                    .collect(Collectors.toList());
            List<Category> categories = metadata
                    .stream()
                    .filter(meta -> Objects.nonNull(meta) && !StringUtils.isEmpty(meta))
                    .map(meta -> categoryService.getCategoryByName(meta))
                    .collect(Collectors.toList());
            List<CategoryResponse> departmentResponses = categories
                    .stream()
                    .map(cat -> new CategoryResponse(cat.getCategoryId(), cat.getCategory(), cat.getDescription()))
                    .collect(Collectors.toList());
            response.setCategories(departmentResponses);
        } else {
            validateMetadata(new ArrayList<>());
        }
        logger.info("fetched categories data and it returns the CategoriesResponse object");
        return response;
    }

    /**
     *
     * @param locationId
     * @param departmentId
     * @param categoryId
     * @param subCategoryId
     * @return
     */
    public MetadataDetailsResponse getMetaDataFromParameters(long locationId, long departmentId, long categoryId, long subCategoryId) {
        logger.info("entered into getMetaDataFromParameters()");
        MetadataDetailsResponse response = new MetadataDetailsResponse();
        Location locationData = locationService.getLocationById(locationId);
        Department departmentData = departmentService.getDepartmentById(departmentId);
        Category categoryData = categoryService.getCategoryById(categoryId);
        SubCategory subCategoryData = subCategoryService.getSubCategoryById(subCategoryId);
        if (Objects.nonNull(locationData) && Objects.nonNull(departmentData) && Objects.nonNull(categoryData) && Objects.nonNull(subCategoryData)) {
            List<MetaData> metadata = metaDataRepository.findMetaDataDetails(locationData.getLocation(), departmentData.getDepartment(), categoryData.getCategory(), subCategoryData.getSubCategory());
            List<MetadataResponse> metaDataResponse = metadata
                    .stream()
                    .map(meta -> new MetadataResponse(meta.getMetaDataId(), meta.getLocation(), meta.getDepartment(), meta.getCategory(), meta.getSubcategory()))
                    .collect(Collectors.toList());
            validateMetadata(metaDataResponse);
            response.setMetadataDetails(metaDataResponse);
        } else {
            validateMetadata(new ArrayList<>());
        }
        logger.info("fetched metadata data and it returns the metaDataResponse object");
        return response;
    }

    private void validateMetadata(List<MetadataResponse> metadata) {
        if (metadata.isEmpty()) {
            logger.error("metadata details are not found for the given input request");
            throw new MetaDataNotFoundException("Metadata Not found for the requested Parameters");
        }

    }


}
