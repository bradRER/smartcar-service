package com.example.smartcar.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/location")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getLocation(@PathVariable Integer id) {
//        String requestUrl = "https://api.smartcar.com/v1.0/vehicles/{id}/"; // manual hhtp call

        //get vehicle options to make vehicle
//        SmartcarVehicleOptions vehicleOptions = new SmartcarVehicleOptions.Builder().version("2.0").unitSystem(Vehicle.UnitSystem.METRIC).origin("").build();
//
//        // build vehicle
//        Vehicle vehicle = new Vehicle("","", vehicleOptions);

        // our com.example.smartcar.service calls
        Optional<Location> location = locationService.findById(id);

        if (location.isPresent())
            return ResponseEntity.ok(location.get().toString());
        else
            return new ResponseEntity<>("Entity not found.", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteLocation(@PathVariable Integer id) {
//        Optional<Location> location = locationService.findById(id);
//        if (!location.isPresent())
//            locationService.deleteById(id);

        return ResponseEntity.ok(locationService.deleteById(id));
    }

    // todo make request body
    @PostMapping("/create")
    public ResponseEntity<String> createLocation(@RequestParam Double latitude, @RequestParam Double longitude) {
        Location resp = locationService.createLocation(latitude, longitude);
        if (resp != null)
            return new ResponseEntity<>(resp.toString(), HttpStatus.OK);
        else
            return new ResponseEntity<>("Unable to create Entity", HttpStatus.BAD_REQUEST);


    }


}
