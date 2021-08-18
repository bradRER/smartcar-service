package com.example.smartcar.location;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
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
    public void create_location_success() {
        // given
        Location locationRecord = new Location();
        locationRecord.setLatitude(42.010101010);
        locationRecord.setLongitude(-71.0000583618164);
        // when
        doReturn(locationRecord).when(locationRepository).save(any());
        Location created = locationService.createLocation(locationRecord.getLatitude(), locationRecord.getLongitude());
        // then
        org.junit.jupiter.api.Assertions.assertEquals(locationRecord, created);
    }

    @Test
    public void create_location_fails() {
        // given
        doReturn(null).when(locationRepository).save(any());
        // when
        Location created = locationService.createLocation(42D, -71D);
        // then
        org.junit.jupiter.api.Assertions.assertEquals(created, null);
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
        org.junit.jupiter.api.Assertions.assertEquals(locationRecord, created.get());
    }
    @Test
    public void get_location_fails() {
        // given
        doReturn(Optional.empty()).when(locationRepository).findById(1);
        // when
        Optional<Location> gotten = locationService.findById(1);
        // then
        org.junit.jupiter.api.Assertions.assertEquals(gotten,Optional.empty());
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
        org.junit.jupiter.api.Assertions.assertEquals(gotten,"Ok");
    }

    @Test
    public void delete_location_fails() {
        // given
        doReturn(Optional.ofNullable(null)).when(locationRepository).findById(173);
        // when
        String gotten = locationService.deleteById(173);
        // then
        org.junit.jupiter.api.Assertions.assertEquals(gotten,"Error");
    }
}


