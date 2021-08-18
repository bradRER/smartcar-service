package com.example.smartcar.vehicleInfoAddl;

//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleInfoAddlRepository extends CrudRepository<VehicleInfoAddl, Integer> {
    Optional<VehicleInfoAddl> findByVinA(String vin);

//    @Query(value = 'select id, vin_a, make_a, model_a, year_a, mfg_name, series, vehicle_type, body_class, doors, wheel_base, steering_location, nbr_of_seats, nbr_of_seat_rows, transmission_style, transmission_speeds, axles, fuel_type_primary, electrification_level, other_engine_info, created_at, created_by, modified_at, modified_by, telematics_request_id, telematics_request_server_id from vehicle_info_addl where vin_a = ?1')
//    public Optional<VehicleInfoAddl> findByVinA(String vin){}

//    @Query("SELECT b FROM vehicle_info_addl b")
//    List<VehicleInfoAddlRepository> findAllBooks();

//    @Query(value = "select via.id, via.vin_a, via.make_a, via.model_a, via.year_a, via.mfg_name, via.series, via.trim, via.vehicle_type, via.body_class, via.doors, via.wheel_base, via.steering_location, via.nbr_of_seats, via.nbr_of_seat_rows, via.transmission_style, via.transmission_speeds, via.axles, via.fuel_type_primary, via.electrification_level, via.other_engine_info, via.created_at, via.created_by, via.modified_at, via.modified_by, via.telematics_request_id, via.telematics_request_server_id from vehicle_info_addl via where vin_a = ?1")
//    public Optional<VehicleInfoAddl> findByVinA(String vin);

}
