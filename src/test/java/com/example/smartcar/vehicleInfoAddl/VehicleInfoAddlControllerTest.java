package com.example.smartcar.vehicleInfoAddl;

import com.example.smartcar.location.Location;
import com.example.smartcar.location.LocationController;
import com.example.smartcar.location.LocationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
//import org.junit.Test;
import org.mockito.Mockito;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;

public class VehicleInfoAddlControllerTest {

    private VehicleInfoAddlService vehicleInfoAddlService = Mockito.mock(VehicleInfoAddlService.class);

    private VehicleInfoAddlController vehicleInfoAddlController;

    @BeforeEach
    void initUseCase() {
        vehicleInfoAddlController = new VehicleInfoAddlController(vehicleInfoAddlService);
    }

//    @Test
//    public void contextLoads() throws Exception {
//        Assertions.assertThat(vehicleInfoAddlController).isNotNull();
//    }

    @Test
    public void create_vehicle_info_addl_success() throws JsonProcessingException {
        // given
        Date date = new Date(System.currentTimeMillis());
        VehicleInfoAddl vehicleInfoAddl = new VehicleInfoAddl(1,"fasdg","ferrari", "F40",1991,"ferrari","f40","the best one", "really cool","coupe",2,109.3F,"right",1,1,"",1,1,"","","",1,date,null,null,1,"a");
        // when
        doReturn(vehicleInfoAddl).when(vehicleInfoAddlService).create("fasdg");
        ResponseEntity<String> created = vehicleInfoAddlController.createVehicleInfoAddlByVinA("fasdg");
        // then
        Assertions.assertEquals(created.getBody(), vehicleInfoAddl.toString() );
    }

    @Test
    public void create_vehicle_info_addl_fails() throws JsonProcessingException {
        // given
        Date date = new Date(System.currentTimeMillis());
        VehicleInfoAddl vehicleInfoAddl = new VehicleInfoAddl(1,"fasdg",null, null,null,"ferrari","f40","the best one", "really cool","coupe",2,109.3F,"right",1,1,"",1,1,"","","",1,date,null,null,1,"a");
        // when
        doReturn(vehicleInfoAddl).when(vehicleInfoAddlService).create("fasdg");
        ResponseEntity<String> created = vehicleInfoAddlController.createVehicleInfoAddlByVinA("fasdg");
        // then
//        org.junit.jupiter.api.Assertions.assertEquals(created.getBody(), "Invalid vin. Entity not created." );
        Assertions.assertEquals(created.getStatusCode(), HttpStatus.BAD_REQUEST );
    }

    @Test
    public void get_vehicle_info_addl_by_vin_success() {
        // given
        Date date = new Date(System.currentTimeMillis());
        VehicleInfoAddl vehicleInfoAddl = new VehicleInfoAddl(1,"fasdg","ferrari", "F40",1991,"ferrari","f40","the best one", "really cool","coupe",2,109.3F,"right",1,1,"",1,1,"","","",1,date,null,null,1,"a");
        // when
        doReturn(Optional.ofNullable(vehicleInfoAddl)).when(vehicleInfoAddlService).getVehicleInfoAddlByVinA("fasdg");
        ResponseEntity<String> created = vehicleInfoAddlController.getVehicleInfoAddlByVinA("fasdg");
        // then
        Assertions.assertEquals(created.getBody(), vehicleInfoAddl.toString() );
    }

    @Test
    public void get_vehicle_info_addl_by_vin_fails() {
        // given
        doReturn(Optional.ofNullable(null)).when(vehicleInfoAddlService).getVehicleInfoAddlByVinA("fasdg");
        // when
        ResponseEntity<String> created = vehicleInfoAddlController.getVehicleInfoAddlByVinA("fasdg");
        // then
//        org.junit.jupiter.api.Assertions.assertEquals(created.getBody(), "Entity not found" );
        Assertions.assertEquals(created.getStatusCode(), HttpStatus.BAD_REQUEST );
    }

    @Test
    public void get_vehicle_info_addl_by_id_success() {
        // given
        Date date = new Date(System.currentTimeMillis());
        VehicleInfoAddl vehicleInfoAddl = new VehicleInfoAddl(1,"fasdg","ferrari", "F40",1991,"ferrari","f40","the best one", "really cool","coupe",2,109.3F,"right",1,1,"",1,1,"","","",1,date,null,null,1,"a");
        // when
        doReturn(Optional.ofNullable(vehicleInfoAddl)).when(vehicleInfoAddlService).getVehicleInfoAddlById(1);
        ResponseEntity<String> created = vehicleInfoAddlController.getVehicleInfoAddlById(1);
        // then
        Assertions.assertEquals(created.getBody(), vehicleInfoAddl.toString() );
    }

    @Test
    public void get_vehicle_info_addl_by_id_fails() {
        // given
        doReturn(Optional.ofNullable(null)).when(vehicleInfoAddlService).getVehicleInfoAddlById(1);
        // when
        ResponseEntity<String> created = vehicleInfoAddlController.getVehicleInfoAddlById(1);
        // then
//        org.junit.jupiter.api.Assertions.assertEquals(created.getBody(), "Entity not found" );
        Assertions.assertEquals(created.getStatusCode(), HttpStatus.BAD_REQUEST );
    }

    @Test
    public void delete_vehicle_info_addl_by_id_success() {
        // given
        doReturn("Ok").when(vehicleInfoAddlService).deleteById(1);
        // when
        ResponseEntity<String> created = vehicleInfoAddlController.deleteVehicleInfoAddlById(1);
        // then
        Assertions.assertEquals(created.getBody(), "Ok" );
    }

    @Test
    public void update_vehicle_info_addl_success() {
        // given
        Date date = new Date(System.currentTimeMillis());
        VehicleInfoAddl vehicleInfoAddl = new VehicleInfoAddl(1,"fasdg","ferrari", "F40",1991,"ferrari","f40","the best one", "really cool","coupe",2,109.3F,"right",1,1,"",1,1,"","","",1,date,null,null,1,"a");
        doReturn(vehicleInfoAddl).when(vehicleInfoAddlService).updateVehicleInfoAddl(vehicleInfoAddl);
        // when
        ResponseEntity<String> gotten = vehicleInfoAddlController.updateVehicleInfoAddl(vehicleInfoAddl);
        // then
        Assertions.assertEquals(gotten.getBody(),vehicleInfoAddl.toString());
    }

    @Test
    public void update_vehicle_info_addl_fails() {
        // given
        Date date = new Date(System.currentTimeMillis());
        VehicleInfoAddl vehicleInfoAddl = new VehicleInfoAddl(1,"fasdg","ferrari", "F40",1991,"ferrari","f40","the best one", "really cool","coupe",2,109.3F,"right",1,1,"",1,1,"","","",1,date,null,null,1,"a");
        doReturn(null).when(vehicleInfoAddlService).updateVehicleInfoAddl(vehicleInfoAddl);
        // when
        ResponseEntity<String> gotten = vehicleInfoAddlController.updateVehicleInfoAddl(vehicleInfoAddl);
        // then
        Assertions.assertEquals(gotten.getBody(),"Failed to update Entity");
    }

    @Test
    public void delete_vehicle_info_addl_by_id_fails() {
        // given
        doReturn("Error").when(vehicleInfoAddlService).deleteById(1);
        // when
        ResponseEntity<String> created = vehicleInfoAddlController.deleteVehicleInfoAddlById(1);
        // then
        Assertions.assertEquals(created.getStatusCode(), HttpStatus.BAD_REQUEST );
    }
}
