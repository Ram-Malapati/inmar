package com.inmar.app.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MetaDataRequest {
    private String location;
    private String department;
    private String category;
    private String subCategory;


}
