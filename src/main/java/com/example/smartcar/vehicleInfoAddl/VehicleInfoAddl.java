package com.example.smartcar.vehicleInfoAddl;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Entity
@Table(name="vehicle_info_addl")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class VehicleInfoAddl {

    @Id
    @SequenceGenerator(name="vehicle_info_addl_id_seq",
            sequenceName="vehicle_info_addl_id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="vehicle_info_addl_id_seq")
    private int id;


    @Column(name="vin_a", nullable = false)
    private String vinA;

    @Column(name="make_a")
    private String makeA;

    @Column(name="model_a")
    private String modelA;

    @Column(name="year_a")
    private Integer yearA;

    @Column(name="mfg_name")
    private String mfgName;
    private String series;
    private String trim;

    @Column(name="vehicle_type")
    private String vehicleType;

    @Column(name="body_class")
    private String bodyClass;
    private Integer doors;

    @Column(name="wheel_base")
    private Float wheelBase;

    @Column(name="steering_location")
    private String steeringLocation;

    @Column(name="nbr_of_seats")
    private Integer nbrOfSeats;

    @Column(name="nbr_of_seat_rows")
    private Integer nbrOfSeatRows;

    @Column(name="transmission_style")
    private String transmissionStyle;

    @Column(name="transmission_speeds")
    private Integer transmissionSpeeds;
    private Integer axles;

    @Column(name="fuel_type_primary")
    private String fuelTypePrimary;

    @Column(name="electrification_level")
    private String electrificationLevel;

    @Column(name="other_engine_info")
    private String otherEngineInfo;

    @CreatedBy
    @Column(name="created_by", nullable = false)
    protected Integer createdBy;

    @CreatedDate()
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at", nullable = false)
    protected Date createdAt;

    @LastModifiedBy
    @Column(name="modified_by")
    protected Integer modifiedBy;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="modified_at")
    protected Date modifiedAt;

    @Column(name="telematics_request_id", nullable = false)
    private int telematicsRequestId;

    @Column(name="telematics_request_server_id", nullable = false, length = 15)
    private String telematicsRequestServerId;



}
