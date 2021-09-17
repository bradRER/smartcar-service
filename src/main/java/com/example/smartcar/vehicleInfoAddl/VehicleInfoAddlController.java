package com.example.smartcar.vehicleInfoAddl;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/vehicle/info/addl")
public class VehicleInfoAddlController {


    private final VehicleInfoAddlService  vehicleInfoAddlService;

    public VehicleInfoAddlController(VehicleInfoAddlService service) {
        this.vehicleInfoAddlService = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getVehicleInfoAddlById(@PathVariable Integer id) {
        Optional<VehicleInfoAddl> vehicleInfoAddl = vehicleInfoAddlService.getVehicleInfoAddlById(id);

        if (vehicleInfoAddl.isPresent())
            return ResponseEntity.ok(vehicleInfoAddl.get().toString());
        else
            return new ResponseEntity<>("Entity not found", HttpStatus.BAD_REQUEST);



//            return ResponseEntity.badRequest();
    }

    @GetMapping("/vin/{vin}")
    public ResponseEntity<String> getVehicleInfoAddlByVinA(@PathVariable String vin) {
        Optional<VehicleInfoAddl> vehicleInfoAddl = vehicleInfoAddlService.getVehicleInfoAddlByVinA(vin);

        if (vehicleInfoAddl.isPresent())
            return ResponseEntity.ok(vehicleInfoAddl.get().toString());
        else
            return new ResponseEntity<>("Entity not found", HttpStatus.BAD_REQUEST);



//        return service.getVehicleInfoAddlByVinA(vin);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteVehicleInfoAddlById(@PathVariable Integer id) {
//        Optional<VehicleInfoAddl> vehicleInfoAddl = service.getVehicleInfoAddlById(id);
//        if (!vehicleInfoAddl.isPresent())
            String success = vehicleInfoAddlService.deleteById(id);

//            if (success == "Ok")
                return  new ResponseEntity<>(success, HttpStatus.OK);
//            else
//                return new ResponseEntity<>(success, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateVehicleInfoAddl(@RequestParam VehicleInfoAddl vehicleInfoAddl) {
        VehicleInfoAddl updated = vehicleInfoAddlService.updateVehicleInfoAddl(vehicleInfoAddl);

        if (updated != null)
            return ResponseEntity.ok(updated.toString());
        else
            return new ResponseEntity<>("Failed to update Entity", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/create/{vin}")
    public ResponseEntity<String> createVehicleInfoAddlByVinA(@PathVariable String vin) throws JsonProcessingException {
        VehicleInfoAddl vehicleInfoAddl = vehicleInfoAddlService.create(vin);

        //repo entries should have year make model
        if (vehicleInfoAddl.getMakeA() != null &&
                vehicleInfoAddl.getModelA() != null &&
                vehicleInfoAddl.getYearA() != null)
            return ResponseEntity.ok(vehicleInfoAddl.toString());
        else
            return new ResponseEntity<>("Invalid vin. Entity not created.", HttpStatus.BAD_REQUEST);

    }

//    @GetMapping("/vin/{vin}")
//    public Optional<VehicleInfoAddl> getVehicleInfoAddlByVin(@PathVariable String vin) {
//        return service.getVehicleInfoAddlByVin(vin);
//    }


}
