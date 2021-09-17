package com.example.smartcar.vehicleInfoAddl;

import com.example.smartcar.location.Location;
import com.example.smartcar.location.LocationRepository;
import com.example.smartcar.location.LocationService;
import com.fasterxml.jackson.core.JsonProcessingException;
//import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class VehicleInfoAddlServiceTest {


    @InjectMocks
    VehicleInfoAddlService vehicleInfoAddlService;

    @Mock
    VehicleInfoAddlRepository vehicleInfoAddlRepository;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    public void contextLoads() throws Exception {
//        Assertions.assertThat(vehicleInfoAddlService).isNotNull();
//    }

    @Test
    public void create_vehicle_info_addl_success() throws JsonProcessingException {
        // given
        Date date = new Date(System.currentTimeMillis());
        VehicleInfoAddl vehicleInfoAddl = new VehicleInfoAddl(1,"1D7HA18D64J249454","DODGE", "Ram",2004,"FCA US LLC",null,"1500", "TRUCK","Pickup",null,null,"right",null,null,null,null,null,"Gasoline",null,"Fuel Delivery / Fuel Injection Type : SMPI, Sales Code:EZA",1,date,null,null,1,"a");
        // when
        doReturn(vehicleInfoAddl).when(vehicleInfoAddlRepository).save(any());
        VehicleInfoAddl created = vehicleInfoAddlService.create("1D7HA18D64J249454"); //random valid vin because testing request
        // then
        Assertions.assertEquals(created.getMakeA(), vehicleInfoAddl.getMakeA() );
    }

    @Test
    public void create_vehicle_info_addl_fails() throws JsonProcessingException {
        // given
//        doReturn(vehicleInfoAddlNull).when(vehicleInfoAddlRepository).save(any()); save call wont be reached because garbage vin
        // when
        VehicleInfoAddl created = vehicleInfoAddlService.create("fasdg");
        // then
        Assertions.assertEquals(created.getMakeA(), null );
    }

    @Test
    public void get_vehicle_info_addl_by_vin_success(){
        // given
        Date date = new Date(System.currentTimeMillis());
        VehicleInfoAddl vehicleInfoAddl = new VehicleInfoAddl(1,"1D7HA18D64J249454","DODGE", "Ram",2004,"FCA US LLC",null,"1500", "TRUCK","Pickup",null,null,"right",null,null,null,null,null,"Gasoline",null,"Fuel Delivery / Fuel Injection Type : SMPI, Sales Code:EZA",1,date,null,null,1,"a");
        // when
        doReturn(Optional.ofNullable( vehicleInfoAddl)).when(vehicleInfoAddlRepository).findByVinA("1D7HA18D64J249454");
        Optional<VehicleInfoAddl> created = vehicleInfoAddlService.getVehicleInfoAddlByVinA("1D7HA18D64J249454");
        // then
        Assertions.assertEquals(created.get(), vehicleInfoAddl );
    }

    @Test
    public void get_vehicle_info_addl_by_vin_fails() {
        // given
        doReturn(Optional.ofNullable( null)).when(vehicleInfoAddlRepository).findByVinA("fasdg");
        // when
        Optional<VehicleInfoAddl> gotten = vehicleInfoAddlService.getVehicleInfoAddlByVinA("fasdg");
        // then
        Assertions.assertEquals(gotten,Optional.empty());
    }

    @Test
    public void get_vehicle_info_addl_by_id_success() {
        // given
        Date date = new Date(System.currentTimeMillis());
        VehicleInfoAddl vehicleInfoAddl = new VehicleInfoAddl(1,"1D7HA18D64J249454","DODGE", "Ram",2004,"FCA US LLC",null,"1500", "TRUCK","Pickup",null,null,"right",null,null,null,null,null,"Gasoline",null,"Fuel Delivery / Fuel Injection Type : SMPI, Sales Code:EZA",1,date,null,null,1,"a");
        // when
        doReturn(Optional.ofNullable( vehicleInfoAddl)).when(vehicleInfoAddlRepository).findById(1);
        Optional<VehicleInfoAddl> created = vehicleInfoAddlService.getVehicleInfoAddlById(1);
        // then
        Assertions.assertEquals(created.get(), vehicleInfoAddl );
    }

    @Test
    public void get_vehicle_info_addl_by_id_fails() {
        // given
        doReturn(Optional.ofNullable( null)).when(vehicleInfoAddlRepository).findById(1);
        // when
        Optional<VehicleInfoAddl> gotten = vehicleInfoAddlService.getVehicleInfoAddlById(1);
        // then
        Assertions.assertEquals(gotten,Optional.empty());
    }

    @org.junit.Test
    public void update_location_success() {
        // given
        Date date = new Date(System.currentTimeMillis());
        VehicleInfoAddl vehicleInfoAddl = new VehicleInfoAddl(1,"1D7HA18D64J249454","DODGE", "Ram",2004,"FCA US LLC",null,"1500", "TRUCK","Pickup",null,null,"right",null,null,null,null,null,"Gasoline",null,"Fuel Delivery / Fuel Injection Type : SMPI, Sales Code:EZA",1,date,null,null,1,"a");
        doReturn(vehicleInfoAddl).when(vehicleInfoAddlRepository).update(vehicleInfoAddl);
        // when
        VehicleInfoAddl gotten = vehicleInfoAddlService.updateVehicleInfoAddl(vehicleInfoAddl);
        // then
        Assertions.assertEquals(gotten,vehicleInfoAddl);
    }

    @org.junit.Test
    public void update_location_fails() {
        // given
        Date date = new Date(System.currentTimeMillis());
        VehicleInfoAddl vehicleInfoAddl = new VehicleInfoAddl(1,"1D7HA18D64J249454","DODGE", "Ram",2004,"FCA US LLC",null,"1500", "TRUCK","Pickup",null,null,"right",null,null,null,null,null,"Gasoline",null,"Fuel Delivery / Fuel Injection Type : SMPI, Sales Code:EZA",1,date,null,null,1,"a");
        doReturn(null).when(vehicleInfoAddlRepository).update(vehicleInfoAddl);
        // when
        VehicleInfoAddl gotten = vehicleInfoAddlService.updateVehicleInfoAddl(vehicleInfoAddl);
        // then
        Assertions.assertEquals(gotten,null);
    }

    @Test
    public void delete_location_success() {
        // given
        Date date = new Date(System.currentTimeMillis());
        VehicleInfoAddl vehicleInfoAddl = new VehicleInfoAddl(1,"1D7HA18D64J249454","DODGE", "Ram",2004,"FCA US LLC",null,"1500", "TRUCK","Pickup",null,null,"right",null,null,null,null,null,"Gasoline",null,"Fuel Delivery / Fuel Injection Type : SMPI, Sales Code:EZA",1,date,null,null,1,"a");
        // when
        doReturn(Optional.ofNullable(vehicleInfoAddl)).when(vehicleInfoAddlRepository).findById(vehicleInfoAddl.getId());
        String gotten = vehicleInfoAddlService.deleteById(vehicleInfoAddl.getId());
        // then
        Assertions.assertEquals(gotten,"Ok");
    }

    @Test
    public void delete_location_fails() {
        // given
        doReturn(Optional.ofNullable(null)).when(vehicleInfoAddlRepository).findById(173);
        // when
        String gotten = vehicleInfoAddlService.deleteById(173);
        // then
        Assertions.assertEquals(gotten,"Error");
    }

}