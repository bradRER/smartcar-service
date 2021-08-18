package com.example.smartcar.location;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class LocationService {


    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }


    public Optional<Location> findById(Integer id) {
        return locationRepository.findById(id);
//        return location;
    }

    public Location createLocation(Double latitude, Double longitude) {
        // get auth data

        // get smartcar data

//        create location record instance

        Location location = new Location();
        location.setLatitude(latitude);
        location.setLongitude(longitude);

        // created/modified
        Date date = new Date(System.currentTimeMillis());
        location.setCreatedAt(date);
        location.setCreatedBy(7);


        location.setScEventTime(date);
        location.setScRequestId("17");
        location.setTelematicsRequestId(25);
        location.setTelematicsRequestServerId("A");

        // save location into repository
        return locationRepository.save(location);
//        if (created != null)
//            return created;
//        else
//            return null;

//        return locationRepository.save(location);
    }

    public String deleteById(Integer id) {
        Optional<Location> location = locationRepository.findById(id);

        if (location.isPresent() ) {
            locationRepository.delete(location.get());
            return "Ok";
        } else return "Error";
    }

}
