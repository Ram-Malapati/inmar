package com.inmar.app.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class LocationsResponse {
    private List<LocationResponse> locations;
}
