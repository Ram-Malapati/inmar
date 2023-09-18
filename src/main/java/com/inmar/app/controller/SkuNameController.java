package com.inmar.app.controller;

import com.inmar.app.dto.request.MetaDataRequest;
import com.inmar.app.service.SKUDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/skudata")
public class SkuNameController {

    @Autowired
    private SKUDataService skuDataService;

 /*   @GetMapping("/load")
    public ResponseEntity<?> loadSkuData(){

        return new ResponseEntity<>(skuDataService.persistSkuData(), HttpStatus.OK);
    }
*/
    @PostMapping("/")
    public ResponseEntity<?> getSkuDetailsByMetaData(@RequestBody MetaDataRequest request){

        return new ResponseEntity<>(skuDataService.skuDetailsByMetaData(request), HttpStatus.OK);
    }
}
