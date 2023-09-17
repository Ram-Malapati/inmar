package com.inmar.app.mapper;

import com.inmar.app.dto.response.SkuDataResponse;
import com.inmar.app.dto.response.SkuDetailsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkuNameMapper {
    Logger logger = LoggerFactory.getLogger(SkuNameMapper.class);

    /**
     * This method used for fetch list of sku details
     * @param skus
     * @return
     */
    public SkuDetailsResponse listOfSkuDetails(List<Long> skus) {
        logger.info("entered into listOfSkuDetails()");
        SkuDetailsResponse skuDetailsResponse = new SkuDetailsResponse();
        if (!skus.isEmpty()) {
            List<SkuDataResponse> skuList = skus
                    .stream()
                    .map(SkuDataResponse::new)
                    .collect(Collectors.toList());
            skuDetailsResponse.setSkuDetailsResponse(skuList);
        }
        else{
            logger.info("exit with empty skuDetailsResponse" );
            skuDetailsResponse.setSkuDetailsResponse(new ArrayList<>());
        }
        return skuDetailsResponse;
    }
}
