package com.inmar.app.service;

import com.inmar.app.dto.response.MetadataDetailsResponse;
import com.inmar.app.dto.response.MetadataResponse;
import com.inmar.app.jpa.model.*;
import com.inmar.app.repository.MetaDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MetaDataServiceTest {
    @Mock
    private MetaDataRepository metaDataRepository;
    @InjectMocks
    private MetaDataService metaDataService;
    @Mock
    private LocationService locationService;
    @Mock
    private CategoryService categoryService;
    @Mock
    private SubCategoryService subCategoryService;
    @Mock
    private DepartmentService departmentService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void testMetaDataResponseByParameters() {
        MetadataDetailsResponse metadataDetailsResponse = new MetadataDetailsResponse();
        metadataDetailsResponse.setMetadataDetails(Arrays.asList(new MetadataResponse(1, "Perimeter", "Bakery", "Bakery Bread", "Bagels")));
        Mockito.when(metaDataRepository.findMetaDataDetails(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(Arrays.asList(populateMetaDataDetails().get(0),populateMetaDataDetails().get(1)));
        Location loc = new Location();
        loc.setLocationId(1);
        loc.setDescription("Test Description");
        loc.setLocation("Perimeter");
        Mockito.when(locationService.getLocationById(Mockito.anyLong())).thenReturn(loc);
        Department dep = new Department();
        dep.setDepartment("Bakery");
        dep.setDescription("Test Description");
        dep.setDepartmentId(1);

        Mockito.when(departmentService.getDepartmentById(Mockito.anyLong())).thenReturn(dep);
        Category cat = new Category();
        cat.setCategory("Bakery Bread");
        Mockito.when(categoryService.getCategoryById(Mockito.anyLong())).thenReturn(cat);
        SubCategory sub = new SubCategory();
        sub.setSubCategory("Bagels");
        Mockito.when(subCategoryService.getSubCategoryById(Mockito.anyLong())).thenReturn(sub);
        MetadataDetailsResponse metaData = metaDataService.getMetaDataFromParameters(1L, 1L, 1L, 1L);
        assertEquals(2, metaData.getMetadataDetails().size());

    }

    private List<MetaData> populateMetaDataDetails() {
        List<MetaData> metadataList = new ArrayList<>();
        List<String> rowsOfMetaData = metaDataService.getMetaDataFromFile();
        rowsOfMetaData.remove(0);
        if (!rowsOfMetaData.isEmpty()) {
            //Delete meteData and its related data if any exists in Database
            rowsOfMetaData.stream().filter(metaDataRow -> !StringUtils.isEmpty(metaDataRow)).forEach(metaDataRow -> {
                List<String> columns = Arrays.asList(metaDataRow.split(","));
                MetaData metaData = new MetaData();
                if (!columns.isEmpty()) {
                    metaData.setLocation(columns.get(0));
                    metaData.setDepartment(columns.get(1));
                    metaData.setCategory(columns.get(2));
                    metaData.setSubcategory(columns.get(3));
                    metadataList.add(metaData);
                }
            });
        }
        return metadataList;
    }

}
