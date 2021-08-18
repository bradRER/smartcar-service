package service;

import com.smartcar.sdk.SmartcarVehicleOptions;
import com.smartcar.sdk.Vehicle;
import com.smartcar.sdk.data.ActionResponse;
import com.smartcar.sdk.data.VehicleBattery;
import com.smartcar.sdk.data.VehicleBatteryCapacity;
import com.smartcar.sdk.data.VehicleCharge;

public class SCChargeService {


    public VehicleBatteryCapacity getCapacity() {
        VehicleBatteryCapacity capacity = null;
        try {
            //get vehicle options to make vehicle
            SmartcarVehicleOptions vehicleOptions = new SmartcarVehicleOptions.Builder().version("2.0").unitSystem(Vehicle.UnitSystem.METRIC).origin("").build();

            // build vehicle
            Vehicle vehicle = new Vehicle("","", vehicleOptions);
            capacity = vehicle.batteryCapacity();

        } catch (Exception e) {
            //todo error handling
        }
        return capacity;
    }

    public VehicleBattery getBattery() {
        VehicleBattery battery = null;
        try {
            //get vehicle options to make vehicle
            SmartcarVehicleOptions vehicleOptions = new SmartcarVehicleOptions.Builder().version("2.0").unitSystem(Vehicle.UnitSystem.METRIC).origin("").build();

            // build vehicle
            Vehicle vehicle = new Vehicle("","", vehicleOptions);
            battery = vehicle.battery();

        } catch (Exception e) {
            //todo error handling
        }
        return battery;
    }


    public VehicleCharge getCharge() {
        VehicleCharge charge = null;
        try {
            //get vehicle options to make vehicle
            SmartcarVehicleOptions vehicleOptions = new SmartcarVehicleOptions.Builder().version("2.0").unitSystem(Vehicle.UnitSystem.METRIC).origin("").build();

            // build vehicle
            Vehicle vehicle = new Vehicle("","", vehicleOptions);
            charge = vehicle.charge();

        } catch (Exception e) {
            //todo error handling
        }
        return charge;
    }

    public ActionResponse startCharge() {
        ActionResponse response = null;
        try {
            //get vehicle options to make vehicle
            SmartcarVehicleOptions vehicleOptions = new SmartcarVehicleOptions.Builder().version("2.0").unitSystem(Vehicle.UnitSystem.METRIC).origin("").build();

            // build vehicle
            Vehicle vehicle = new Vehicle("","", vehicleOptions);
            response = vehicle.startCharge();

        } catch (Exception e) {
            //todo error handling
        }
        return response;
    }

    public ActionResponse stopCharge() {
        ActionResponse response = null;
        try {
            //get vehicle options to make vehicle
            SmartcarVehicleOptions vehicleOptions = new SmartcarVehicleOptions.Builder().version("2.0").unitSystem(Vehicle.UnitSystem.METRIC).origin("").build();

            // build vehicle
            Vehicle vehicle = new Vehicle("","", vehicleOptions);
            response = vehicle.stopCharge();

        } catch (Exception e) {
            //todo error handling
        }
        return response;
    }

}
