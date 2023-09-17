package com.inmar.app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MetadataResponse {
    private long metaDataId;
    private String location;
    private String department;
    private String category;
    private String subCategory;

}
