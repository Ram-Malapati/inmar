package com.inmar.app.controller;

import com.inmar.app.dto.response.CategoriesResponse;
import com.inmar.app.dto.response.DepartmentsResponse;
import com.inmar.app.dto.response.LocationsResponse;
import com.inmar.app.dto.response.MetadataDetailsResponse;
import com.inmar.app.service.LocationService;
import com.inmar.app.service.MetaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/location")
public class MetaDataController {

    @Autowired
    private LocationService locationService;

    @Autowired
    private MetaDataService metaDataService;

    @GetMapping
    public ResponseEntity<?> getLocationDetails() {
        LocationsResponse response = locationService.getAvailableLocations();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{location_id}/department/{department_id}/category")
    public ResponseEntity<?> getCategories(@PathVariable(value="location_id") long locationId,@PathVariable(value="department_id") long departmentId) {
        CategoriesResponse response = metaDataService.getCategoriesFromMetadata(locationId,departmentId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{location_id}/department")
    public ResponseEntity<?> getDepartments(@PathVariable(value="location_id") long locationId) {
        DepartmentsResponse response = metaDataService.getDepartmentFromMetadata(locationId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

   /* @GetMapping("/load")
    public ResponseEntity<?> loadMetaData(){

        return new ResponseEntity<>(metaDataService.savedMetaData(), HttpStatus.OK);
    }
*/
    @GetMapping("/{location_id}/department/{department_id}/category/{category_id}/subcategory/{subcategory_id}")
    public ResponseEntity<?> getCategoryMetaData(@PathVariable(value="location_id") long locationId, @PathVariable(value="department_id") long departmentId,@PathVariable(value="category_id") long categoryId,@PathVariable(value="subcategory_id") long subCategoryId) {
        MetadataDetailsResponse response = metaDataService.getMetaDataFromParameters(locationId,departmentId,categoryId,subCategoryId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
