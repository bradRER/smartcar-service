package com.example.smartcar.location;

import com.smartcar.sdk.SmartcarException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;


public class LocationControllerTest {


    private LocationService locationService = Mockito.mock(LocationService.class);

    private LocationController locationController;

    @BeforeEach
    void initUseCase() {
        locationController = new LocationController(locationService);
    }


    @Test
    public void create_location_success() throws SmartcarException {
        // given
        String vehicleNumber = "numbers are strings";
        String accessToken = "let me in";
        Timestamp accessTokenExpiration = Timestamp.from(Instant.now());
        String refreshToken = "please let me in";
        Timestamp refreshTokenExpiration = Timestamp.from(Instant.now());
        Integer makeModelDataSource = 2; // 1 for smartcar

        Date date = new Date(System.currentTimeMillis());
        Location locationRecord = new Location(1,42,-71,date,"12",12,date,null,null,12,"12");
        // when
        doReturn(locationRecord).when(locationService).createLocation(vehicleNumber, accessToken,accessTokenExpiration,refreshToken,refreshTokenExpiration,makeModelDataSource);
        ResponseEntity<String> created = locationController.createLocation(vehicleNumber, accessToken,accessTokenExpiration,refreshToken,refreshTokenExpiration,makeModelDataSource);
        // then
        Assertions.assertEquals(created.getBody(), locationRecord.toString() );
    }
    @Test
    public void create_location_fail() throws SmartcarException {
        // given
        String vehicleNumber = "numbers are strings";
        String accessToken = "let me in";
        Timestamp accessTokenExpiration = Timestamp.from(Instant.now());
        String refreshToken = "please let me in";
        Timestamp refreshTokenExpiration = Timestamp.from(Instant.now());
        Integer makeModelDataSource = 2; // 1 for smartcar

        Date date = new Date(System.currentTimeMillis());
        Location locationRecord = new Location(1,42,-71,date,"12",12,date,null,null,12,"12");
        // when
        doReturn(null).when(locationService).createLocation(vehicleNumber, accessToken,accessTokenExpiration,refreshToken,refreshTokenExpiration,makeModelDataSource);
        ResponseEntity<String> created = locationController.createLocation(vehicleNumber, accessToken,accessTokenExpiration,refreshToken,refreshTokenExpiration,makeModelDataSource);
        // then
        Assertions.assertEquals(created.getBody(), "Unable to create Entity" );
    }

    @Test
    public void get_location_success() {
        // given
        Date date = new Date(System.currentTimeMillis());
        Location locationRecord = new Location(1,42,-71,date,"12",12,date,null,null,12,"12");
        // when
        doReturn(Optional.ofNullable(locationRecord)).when(locationService).findById(locationRecord.getId());
        ResponseEntity<String> location = locationController.getLocation(locationRecord.getId());
        // then
        Assertions.assertEquals(location.getBody(),locationRecord.toString());
    }
    @Test
    public void get_location_fails() {
        // given
        doReturn(Optional.empty()).when(locationService).findById(173);
        // when
        ResponseEntity<String> gotten = locationController.getLocation(173);
        // then
        Assertions.assertEquals(gotten.getBody(),"Entity not found");
    }

    @Test
    public void get_location_by_telematics_id_success() {
        // given
        Date date = new Date(System.currentTimeMillis());
        Location locationRecord = new Location(1,42,-71,date,"12",12,date,null,null,12,"12");
        // when
        doReturn(Optional.ofNullable(locationRecord)).when(locationService).findByTelematicsRequestId(locationRecord.getTelematicsRequestId());
        ResponseEntity<String> location = locationController.getLocationByTelematicsRequestId(locationRecord.getTelematicsRequestId());
        // then
        Assertions.assertEquals(location.getBody(),locationRecord.toString());
    }
    @Test
    public void get_location_by_telematics_id_fails() {
        // given
        doReturn(Optional.empty()).when(locationService).findByTelematicsRequestId(173);
        // when
        ResponseEntity<String> gotten = locationController.getLocationByTelematicsRequestId(173);
        // then
        Assertions.assertEquals(gotten.getBody(),"Entity not found");
    }

    @Test
    public void update_location_success() {
        // given
        Date date = new Date(System.currentTimeMillis());
        Location locationRecord = new Location(1,42,-71,date,"12",12,date,null,null,12,"12");
        doReturn(locationRecord).when(locationService).updateLocation(locationRecord);
        // when
        ResponseEntity<String> gotten = locationController.updateLocation(locationRecord);
        // then
        Assertions.assertEquals(gotten.getBody(),locationRecord.toString());
    }

    @Test
    public void update_location_fails() {
        // given
        Date date = new Date(System.currentTimeMillis());
        Location locationRecord = new Location(1,42,-71,date,"12",12,date,null,null,12,"12");
        doReturn(null).when(locationService).updateLocation(locationRecord);
        // when
        ResponseEntity<String> gotten = locationController.updateLocation(locationRecord);
        // then
        Assertions.assertEquals(gotten.getBody(),"Failed to update Entity");
    }



    @Test
    public void delete_location_success() {
        // given
        Date date = new Date(System.currentTimeMillis());
        Location locationRecord = new Location(173,42,-71,date,"12",12,date,null,null,12,"12");
        // when
        doReturn("Ok").when(locationService).deleteById(locationRecord.getId());
        ResponseEntity<String> gotten = locationController.deleteLocation(locationRecord.getId());
        // then
        Assertions.assertEquals(gotten.getBody(),"Ok");
    }

    @Test
    public void delete_location_fails() {
        // given
        doReturn("Error").when(locationService).deleteById(173);
        // when
        ResponseEntity<String> gotten = locationController.deleteLocation(173);
        // then
        Assertions.assertEquals(gotten.getBody(),"Error");
    }


}
