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
