package com.inmar.app.dto.request;

import lombok.Data;

@Data
public class LocationRequest {
    private long locationId;
    private String location;
    private String description;
}
