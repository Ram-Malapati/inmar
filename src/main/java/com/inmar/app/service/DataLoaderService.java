package com.inmar.app.service;

import com.inmar.app.jpa.model.MetaData;
import com.inmar.app.jpa.model.SkuData;
import com.inmar.app.repository.MetaDataRepository;
import com.inmar.app.repository.SkuDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Component
public class DataLoaderService implements ApplicationRunner {
    Logger logger = LoggerFactory.getLogger(DataLoaderService.class);

    @Autowired
    private SkuDataRepository skuDataRepository;

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




    public void run(ApplicationArguments args) {

        locationService.deleteAllLocations();
        departmentService.deleteAllDepartments();
        categoryService.deleteAllCategories();
        subCategoryService.deleteAllSubCategories();
        metaDataRepository.deleteAll();
        List<String> rowsOfMetaData = getMetaDataFromFile();
        rowsOfMetaData.remove(0);
        rowsOfMetaData.stream().filter(metaDataRow -> !StringUtils.isEmpty(metaDataRow)).forEach(metaDataRow -> {
            List<String> columns = Arrays.asList(metaDataRow.split(","));
            MetaData metaData = new MetaData();
            if (!columns.isEmpty()) {
                metaData.setLocation(columns.get(0));
                metaData.setDepartment(columns.get(1));
                metaData.setCategory(columns.get(2));
                metaData.setSubcategory(columns.get(3));
                metaDataRepository.save(metaData);
                logger.info("metadata object saved successfully");

            }
        });
        List<String> rowsOfSkuData = skuDataFromFile();
        rowsOfSkuData.remove(0);
        rowsOfSkuData.stream().filter(skuDataRow -> !StringUtils.isEmpty(skuDataRow)).forEach(skuDataRow -> {
            List<String> columns = Arrays.asList(skuDataRow.split(","));
            SkuData skuData = new SkuData();
            if (!columns.isEmpty()) {
                skuData.setSkuDataId(Integer.parseInt(columns.get(0)));
                skuData.setSkuName(columns.get(1));
                skuData.setLocation(columns.get(2));
                skuData.setDepartment(columns.get(3));
                skuData.setCategory(columns.get(4));
                skuData.setSubcategory(columns.get(5));
                skuDataRepository.save(skuData);
            }
        });
       departmentService.saveAllDepartments(metaDataRepository.getAllDepartments());
       locationService.saveAllLocations(metaDataRepository.getAllLocations());
        categoryService.saveAllCategories(metaDataRepository.getAllCategories());
        subCategoryService.getAllSubCategories(metaDataRepository.getAllSubCategories());
    }

    /**
     * this method used for fetch details from file
     * @return
     */
    public List<String> getMetaDataFromFile() {
        try {
            ClassPathResource classPathResource = new ClassPathResource("common/metadata.txt");
            byte[] data = FileCopyUtils.copyToByteArray(classPathResource.getInputStream());
            String metaData = new String(data, StandardCharsets.UTF_8);
            if (!StringUtils.isEmpty(metaData)) {
                return new LinkedList<>(Arrays.asList(metaData.split("\\n")));
            }
        } catch (IOException ex) {
            logger.error("metadata file not identified" + ex.getMessage());
        }

        return new LinkedList<>();
    }
    /**
     * this method used for fetch details from file
     * @return
     */
    public List<String> skuDataFromFile() {
        try {
            ClassPathResource classPathResource = new ClassPathResource("common/skudata.txt");
            byte[] data = FileCopyUtils.copyToByteArray(classPathResource.getInputStream());
            String skuData = new String(data, StandardCharsets.UTF_8);
            if (!StringUtils.isEmpty(skuData)) {
                return new LinkedList<>(Arrays.asList(skuData.split("\\n")));
            }
        } catch (Exception ex) {
            logger.error("SkuData file not identified" + ex.getMessage());
        }
        return new LinkedList<>();
    }
}
