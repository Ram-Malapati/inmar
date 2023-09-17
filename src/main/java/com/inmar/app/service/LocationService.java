package com.inmar.app.service;

import com.inmar.app.dto.response.LocationsResponse;
import com.inmar.app.exception.MetaDataNotFoundException;
import com.inmar.app.jpa.model.Location;
import com.inmar.app.mapper.MetaDataDetailsMapper;
import com.inmar.app.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class LocationService {
    @Autowired
    private MetaDataDetailsMapper locationDetailsMapper;
    @Autowired
    private LocationRepository locationRepository;

    public LocationsResponse getAvailableLocations() {
        List<Location> locs=locationRepository.findAll();
        if(locs.isEmpty()){
            throw new MetaDataNotFoundException("Locations related data not found");
        }
        return locationDetailsMapper.mapListOfLocationDetails(locs);
    }

    public Location getLocationById(long id) {
        return locationRepository.findById(id).orElse(null);
    }

    /**
     * This method used for saveAllLocation data
     * @param locationName
     */
    public void saveAllLocation(String locationName) {
        Location location = new Location();
        Location existedLocation = locationRepository.findByLocation(locationName);
        if (Objects.isNull(existedLocation)) {
            location.setLocation(locationName);
            location.setDescription("Test Description for" + locationName);
            locationRepository.save(location);
        }
    }
    public void saveAllLocations(Set<String> locations) {

        for(String location:locations){
            saveAllLocation(location);
        }

    }


    public void deleteAllLocations() {
        locationRepository.deleteAll();
    }
}
