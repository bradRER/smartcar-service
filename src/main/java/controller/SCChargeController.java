package controller;

import com.smartcar.sdk.data.ActionResponse;
import com.smartcar.sdk.data.VehicleBattery;
import com.smartcar.sdk.data.VehicleBatteryCapacity;
import com.smartcar.sdk.data.VehicleCharge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import service.SCChargeService;

@Controller
@RequestMapping("/charge/")
public class SCChargeController {

    @Autowired
    SCChargeService chargeService;

    @RequestMapping("capacity")
    public VehicleBatteryCapacity getCapacity() {
        return chargeService.getCapacity();
    }


    @RequestMapping("battery")
    public VehicleBattery getBattery() {
        return chargeService.getBattery();
    }


    @RequestMapping("charge")
    public VehicleCharge getCharge() {
        return chargeService.getCharge();
    }


    @PostMapping("start")
    public ActionResponse startCharge() {
        return chargeService.startCharge();
    }

    @PostMapping("stop")
    public ActionResponse stopCharge() {
        return chargeService.stopCharge();
    }
}
