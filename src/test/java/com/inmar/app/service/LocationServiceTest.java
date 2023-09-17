package com.inmar.app.service;

import com.inmar.app.dto.response.LocationResponse;
import com.inmar.app.dto.response.LocationsResponse;
import com.inmar.app.jpa.model.Location;
import com.inmar.app.mapper.MetaDataDetailsMapper;
import com.inmar.app.repository.LocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocationServiceTest {
    @Mock
    private LocationRepository locationRepository;
    @Mock
    private MetaDataDetailsMapper locationDetailsMapper;
    @InjectMocks
    private LocationService locationService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testLocationDetailsResponse() {
        LocationsResponse response = new LocationsResponse();
        response.setLocations(populateLocationDetails());
        Mockito.when(locationDetailsMapper.mapListOfLocationDetails(Mockito.anyList())).thenReturn(response);
        Mockito.when(locationRepository.findAll()).thenReturn(populateLocations());
        LocationsResponse locs = locationService.getAvailableLocations();
        assertEquals(2, locs.getLocations().size());

    }

    private List<LocationResponse> populateLocationDetails() {
        List<LocationResponse> locations = new ArrayList<>();
        LocationResponse perimeter = new LocationResponse(1, "Perimeter", "Test Description forPerimeter");
        LocationResponse center = new LocationResponse(2, "Center", "Test Description forCenter");
        locations.addAll(Arrays.asList(perimeter, center));
        return locations;
    }

    private List<Location> populateLocations() {
        List<Location> locations = new ArrayList<>();
        Location perimeter = new Location();
        perimeter.setDescription("Test Description forPerimeter");
        perimeter.setLocation("Perimeter");
        perimeter.setLocationId(1);
        Location center = new Location();
        center.setDescription("Test Description forCenter");
        center.setLocation("Center");
        center.setLocationId(2);
        locations.addAll(Arrays.asList(perimeter, center));
        return locations;
    }

}
