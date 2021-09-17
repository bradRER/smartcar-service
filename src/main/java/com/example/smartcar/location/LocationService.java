package com.example.smartcar.location;

import com.smartcar.sdk.SmartcarException;
import com.smartcar.sdk.SmartcarVehicleOptions;
import com.smartcar.sdk.Vehicle;
import com.smartcar.sdk.data.VehicleLocation;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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

    public Location createLocation(String vehicleNumber, String accessToken, Timestamp accessTokenExpiration, String refreshToken, Timestamp refreshTokenExpiration, Integer makeModelDataSource) throws SmartcarException {

        // assuming 1 is smartcar
        if (makeModelDataSource == 1) {

            SmartcarVehicleOptions vehicleOptions = new SmartcarVehicleOptions.Builder().version("2.0").unitSystem(Vehicle.UnitSystem.METRIC).origin("").build();
//
//        // build vehicle
            Vehicle vehicle = new Vehicle(vehicleNumber,accessToken, vehicleOptions);
           VehicleLocation location = vehicle.location();
        } else {
//          todo
//          this will be where OEM API calls will be made
//          logic for OEMs and fallback SC calls will be made here

        }


        Location location = new Location();


        Date date = new Date(System.currentTimeMillis());
        location.setCreatedAt(date);
        location.setCreatedBy(7);


        location.setScEventTime(date);
        location.setScRequestId("17");
        location.setTelematicsRequestId(25);
        location.setTelematicsRequestServerId("A");
        //get vehicle options to make vehicle

        // our com.example.smartcar.service calls

        return locationRepository.save(location);

    }

    public String deleteById(Integer id) {
        Optional<Location> location = locationRepository.findById(id);

        if (location.isPresent() ) {
            locationRepository.delete(location.get());
            return "Ok";
        } else return "Error";
    }

    public Optional<Location> findByTelematicsRequestId(Integer telematicsRequestId) {
        return locationRepository.findByTelematicsRequestId(telematicsRequestId);
    }

    public Location updateLocation(Location updated) {

        return locationRepository.update(updated);
    }


}
