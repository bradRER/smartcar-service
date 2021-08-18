package service;

import com.smartcar.sdk.SmartcarVehicleOptions;
import com.smartcar.sdk.Vehicle;
import com.smartcar.sdk.data.VehicleLocation;
import model.SCVehicleLocation;

public class VehicleLocationService {


    public VehicleLocation getLocation() {
        VehicleLocation location = new VehicleLocation();
        try {
            //get vehicle options to make vehicle
            SmartcarVehicleOptions vehicleOptions = new SmartcarVehicleOptions.Builder().version("2.0").unitSystem(Vehicle.UnitSystem.METRIC).origin("").build();

            // build vehicle
            Vehicle vehicle = new Vehicle("","", vehicleOptions);
            location = vehicle.location();

        } catch (Exception e) {
            //todo error handling
        }
        return location;
    }
}
