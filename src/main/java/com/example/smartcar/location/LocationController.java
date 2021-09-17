package com.example.smartcar.location;

import com.smartcar.sdk.SmartcarException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Optional;

@RestController
@RequestMapping("/location")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }


    // todo make request body
    @PostMapping("/create/")
    public ResponseEntity<String> createLocation(@RequestParam String vehicleNumber, @RequestParam String accessToken, @RequestParam Timestamp accessTokenExpiration, @RequestParam String refreshToken, @RequestParam Timestamp refreshTokenExpiration, @RequestParam Integer makeModelDataSource) {
        Location resp = null;
        try{
            resp = locationService.createLocation(vehicleNumber, accessToken,accessTokenExpiration,refreshToken,refreshTokenExpiration,makeModelDataSource);
        } catch (SmartcarException e) {
            // todo error logging
        }
        if (resp != null){
            // todo send status code update to telematics request service
            return new ResponseEntity<>(resp.toString(), HttpStatus.OK);
        }
        else {
            // todo send status code update to telematics request service
            return new ResponseEntity<>("Unable to create Entity", HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getLocation(@PathVariable Integer id) {
        Optional<Location> location = locationService.findById(id);

        if (location.isPresent())
            return ResponseEntity.ok(location.get().toString());
        else
            return new ResponseEntity<>("Entity not found", HttpStatus.BAD_REQUEST);
    }


    @GetMapping("/telematics/{telematicsRequestId}")
    public ResponseEntity<String> getLocationByTelematicsRequestId(@PathVariable Integer telematicsRequestId) {
        Optional<Location> location = locationService.findByTelematicsRequestId(telematicsRequestId);
        if (location.isPresent())
            return ResponseEntity.ok(location.get().toString());
        else
            return new ResponseEntity<>("Entity not found", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateLocation(@RequestParam Location updated) {
        Location location = locationService.updateLocation(updated);
        if (location != null)
            return ResponseEntity.ok(location.toString());
        else
            return new ResponseEntity<>("Failed to update Entity", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteLocation(@PathVariable Integer id) {
        Optional<Location> location = locationService.findById(id);
        if (!location.isPresent())
            locationService.deleteById(id);

        return ResponseEntity.ok(locationService.deleteById(id));
    }



}
