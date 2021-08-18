package controller;

import com.smartcar.sdk.AuthClient;
import com.smartcar.sdk.Smartcar;
import com.smartcar.sdk.SmartcarVehicleOptions;
import com.smartcar.sdk.Vehicle;
import com.smartcar.sdk.data.VehicleLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.VehicleLocationService;

@Controller
@RequestMapping("/location/")
public class smartcarLocationController {

    @Autowired
    VehicleLocationService vehicleLocationService;

    @RequestMapping("/get/location")
    public VehicleLocation getLocation() {
        String requestUrl = "https://api.smartcar.com/v1.0/vehicles/{id}/location"; // manual hhtp call

        //get vehicle options to make vehicle
//        SmartcarVehicleOptions vehicleOptions = new SmartcarVehicleOptions.Builder().version("2.0").unitSystem(Vehicle.UnitSystem.METRIC).origin("").build();
//
//        // build vehicle
//        Vehicle vehicle = new Vehicle("","", vehicleOptions);

        // our service calls
        VehicleLocation location = vehicleLocationService.getLocation();
        return location;
    }
}
