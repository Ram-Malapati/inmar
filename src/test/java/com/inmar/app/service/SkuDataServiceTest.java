package com.inmar.app.service;

import com.inmar.app.dto.request.MetaDataRequest;
import com.inmar.app.dto.response.SkuDataResponse;
import com.inmar.app.dto.response.SkuDetailsResponse;
import com.inmar.app.mapper.SkuNameMapper;
import com.inmar.app.repository.SkuDataRepository;
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

public class SkuDataServiceTest {
    @Mock
    private SkuDataRepository skuDataRepository;
    @Mock
    private SkuNameMapper skuDetailsMapper;
    @InjectMocks
    private SKUDataService skuDataService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void testSkuDetailsResponseByMetaData() {
        SkuDetailsResponse skuDetailsResponse=new SkuDetailsResponse();
        skuDetailsResponse.setSkuDetailsResponse(Arrays.asList(populateSkuDetails().get(0),populateSkuDetails().get(13)));
        Mockito.when(skuDetailsMapper.listOfSkuDetails(Mockito.anyList())).thenReturn(skuDetailsResponse);
        Mockito.when(skuDataRepository.findSkuDetailsByMetaDataParameters("Perimeter", "Bakery", "Bakery Bread", "Bagels")).thenReturn(Arrays.asList(1L,13L));
        MetaDataRequest request = new MetaDataRequest("Perimeter", "Bakery", "Bakery Bread", "Bagels");
        SkuDetailsResponse skus = skuDataService.skuDetailsByMetaData(request);
        assertEquals(2, skus.getSkuDetailsResponse().size());

    }

    private List<SkuDataResponse> populateSkuDetails() {
        List<SkuDataResponse> skuDataList = new ArrayList<>();
        List<String> rowsOfSkuData = skuDataService.skuDataFromFile();
        rowsOfSkuData.remove(0);
        if (!rowsOfSkuData.isEmpty()) {
            rowsOfSkuData.stream().filter(skuDataRow -> !StringUtils.isEmpty(skuDataRow)).forEach(skuDataRow -> {
                List<String> columns = Arrays.asList(skuDataRow.split(","));
                SkuDataResponse skuData = new SkuDataResponse();
                if (!columns.isEmpty()) {
                    skuData.setId(Integer.parseInt(columns.get(0)));
                    skuDataList.add(skuData);
                }
            });
        }
        System.out.println(skuDataList);
        return skuDataList;
    }

}
