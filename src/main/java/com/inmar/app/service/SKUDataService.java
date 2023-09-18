package com.inmar.app.service;

import com.inmar.app.dto.request.MetaDataRequest;
import com.inmar.app.dto.response.SkuDetailsResponse;
import com.inmar.app.exception.MetaDataWithSkuDataNotFoundException;
import com.inmar.app.exception.SkuDataNotFoundException;
import com.inmar.app.jpa.model.SkuData;
import com.inmar.app.mapper.SkuNameMapper;
import com.inmar.app.repository.SkuDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Component
public class SKUDataService {

    Logger logger = LoggerFactory.getLogger(SKUDataService.class);

    @Autowired
    private SkuDataRepository skuDataRepository;
    @Autowired
    private SkuNameMapper skuDetailsMapper;

   /* public String persistSkuData() {
        List<String> rowsOfSkuData = skuDataFromFile();
        logger.info("metadata loaded from file "+ rowsOfSkuData);
        rowsOfSkuData.remove(0);
        if (rowsOfSkuData.isEmpty()) {
            if (rowsOfSkuData.isEmpty()) {

                throw new SkuDataNotFoundException("Sku Data Not found in the given file");
            }
        }
        //Delete, if any skudata exists in Database
        skuDataRepository.deleteAll();
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
        logger.info("exit persistSkuData() without any issue");
        return "SkuData saved successfully";
    }
*/


  /*  public List<String> skuDataFromFile() {
        try {
            ClassPathResource classPathResource = new ClassPathResource("common/skudata.txt");
            byte[] data = FileCopyUtils.copyToByteArray(classPathResource.getInputStream());
            String skuData = new String(data, StandardCharsets.UTF_8);
            if (!StringUtils.isEmpty(skuData)) {
                return new LinkedList<>(Arrays.asList(skuData.split("\\n")));
            }
        } catch (Exception ex) {
            logger.error("SkuData file not identified"+ ex.getMessage());
        }
        return new LinkedList<>();
    }*/

    /**
     * This method is used for SkuData details by using metadata
     * @param metaDataRequest
     * @return
     */
    public SkuDetailsResponse skuDetailsByMetaData(MetaDataRequest metaDataRequest) {
        List<Long> skuDataList = skuDataRepository.findSkuDetailsByMetaDataParameters(metaDataRequest.getLocation(), metaDataRequest.getDepartment(), metaDataRequest.getCategory(), metaDataRequest.getSubCategory());
        if (skuDataList.isEmpty()) {
            logger.error("SkuData details are not found for the given input metadata request");
            throw new MetaDataWithSkuDataNotFoundException("Sku Data Not found for the requested Parameters");
        }
        return skuDetailsMapper.listOfSkuDetails(skuDataList);
    }
}
