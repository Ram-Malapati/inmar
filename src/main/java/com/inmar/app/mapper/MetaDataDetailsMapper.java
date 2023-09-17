package com.inmar.app.mapper;

import com.inmar.app.dto.response.LocationResponse;
import com.inmar.app.dto.response.LocationsResponse;
import com.inmar.app.jpa.model.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MetaDataDetailsMapper {

    Logger logger = LoggerFactory.getLogger(MetaDataDetailsMapper.class);

    /**
     * This method used for fetch location Details
     * @param locations
     * @return
     */
    public LocationsResponse mapListOfLocationDetails(List<Location> locations) {
        logger.info("entered into mapListOfLocationDetails() ");
        LocationsResponse locationsResponse=new LocationsResponse();
        if(!locations.isEmpty()) {
            List<LocationResponse> locationList=locations
                    .stream()
                    .filter(location -> location != null && !StringUtils.isEmpty(location.getLocation()))
                    .map(location -> new LocationResponse(location.getLocationId(),location.getLocation(),location.getDescription()))
                    .collect(Collectors.toList());
            locationsResponse.setLocations(locationList);
        }
        else{
            logger.info("exit with empty locationsResponse" );
            locationsResponse.setLocations(new ArrayList<>());
        }
        return locationsResponse;
    }
}
