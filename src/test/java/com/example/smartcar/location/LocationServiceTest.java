package com.example.smartcar.location;

import com.smartcar.sdk.SmartcarException;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class LocationServiceTest {


    @InjectMocks
    LocationService locationService;

    @Mock
    LocationRepository locationRepository;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this); // .initMocks(this);
    }

    @Test
    public void create_location_success() throws SmartcarException {
        // given
        Location locationRecord = new Location();
        String vehicleNumber = "numbers are strings";
        String accessToken = "let me in";
        Timestamp accessTokenExpiration = Timestamp.from(Instant.now());
        String refreshToken = "please let me in";
        Timestamp refreshTokenExpiration = Timestamp.from(Instant.now());
        Integer makeModelDataSource = 2; // 1 for smartcar
        // when
        doReturn(locationRecord).when(locationRepository).save(any());
        Location created = locationService.createLocation(vehicleNumber,accessToken,accessTokenExpiration,refreshToken,refreshTokenExpiration,makeModelDataSource);
        // then
        Assertions.assertEquals(locationRecord, created);
    }

    @Test
    public void create_location_fails() throws SmartcarException {
        // given
        Location locationRecord = new Location();
        String vehicleNumber = "numbers are strings";
        String accessToken = "let me in";
        Timestamp accessTokenExpiration = Timestamp.from(Instant.now());
        String refreshToken = "please let me in";
        Timestamp refreshTokenExpiration = Timestamp.from(Instant.now());
        Integer makeModelDataSource = 2; // 1 for smartcar
        // when
        doReturn(null).when(locationRepository).save(any());
        Location created = locationService.createLocation(vehicleNumber,accessToken,accessTokenExpiration,refreshToken,refreshTokenExpiration,makeModelDataSource);
        // then
        Assertions.assertEquals(null, created);
    }

    @Test
    public void get_location_success() {
        // given
        Date date = new Date(System.currentTimeMillis());
        Location locationRecord = new Location(1,42,-71,date,"12",12,date,null,null,12,"12");
        // when
        doReturn(Optional.ofNullable(locationRecord)).when(locationRepository).findById(1);
        Optional<Location> created = locationService.findById(1);
        // then
        Assertions.assertEquals(locationRecord, created.get());
    }
    @Test
    public void get_location_fails() {
        // given
        doReturn(Optional.empty()).when(locationRepository).findById(1);
        // when
        Optional<Location> gotten = locationService.findById(1);
        // then
        Assertions.assertEquals(gotten,Optional.empty());
    }

    @Test
    public void get_location_by_telematics_request_id_success() {
        // given
        Date date = new Date(System.currentTimeMillis());
        Location locationRecord = new Location(1,42,-71,date,"12",12,date,null,null,12,"12");
        // when
        doReturn(Optional.ofNullable(locationRecord)).when(locationRepository).findByTelematicsRequestId(1);
        Optional<Location> created = locationService.findByTelematicsRequestId(1);
        // then
        Assertions.assertEquals(locationRecord, created.get());
    }
    @Test
    public void get_location_telematics_request_id_fails() {
        // given
        doReturn(Optional.empty()).when(locationRepository).findByTelematicsRequestId(1);
        // when
        Optional<Location> gotten = locationService.findByTelematicsRequestId(1);
        // then
        Assertions.assertEquals(gotten,Optional.empty());
    }

    @Test
    public void update_location_success() {
        // given
        Date date = new Date(System.currentTimeMillis());
        Location locationRecord = new Location(1,42,-71,date,"12",12,date,null,null,12,"12");
        doReturn(locationRecord).when(locationRepository).update(locationRecord);
        // when
        Location gotten = locationService.updateLocation(locationRecord);
        // then
        Assertions.assertEquals(gotten,locationRecord);
    }

    @Test
    public void update_location_fails() {
        // given
        Date date = new Date(System.currentTimeMillis());
        Location locationRecord = new Location(1,42,-71,date,"12",12,date,null,null,12,"12");
        doReturn(null).when(locationRepository).update(locationRecord);
        // when
        Location gotten = locationService.updateLocation(locationRecord);
        // then
        Assertions.assertEquals(gotten,null);
    }

    @Test
    public void delete_location_success() {
        // given
        Date date = new Date(System.currentTimeMillis());
        Location locationRecord = new Location(173,42,-71,date,"12",12,date,null,null,12,"12");
        // when
        doReturn(Optional.ofNullable(locationRecord)).when(locationRepository).findById(locationRecord.getId());
        String gotten = locationService.deleteById(locationRecord.getId());
        // then
        Assertions.assertEquals(gotten,"Ok");
    }

    @Test
    public void delete_location_fails() {
        // given
        doReturn(Optional.ofNullable(null)).when(locationRepository).findById(173);
        // when
        String gotten = locationService.deleteById(173);
        // then
        Assertions.assertEquals(gotten,"Error");
    }
}


