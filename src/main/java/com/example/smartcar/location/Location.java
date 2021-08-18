package com.example.smartcar.location;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Auditable;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Optional;


@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Entity
@Table(name="location")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Location {

    @Id
    @SequenceGenerator(name="location_id_seq",
            sequenceName="location_id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="location_id_seq")
    private int id;

    private double latitude;

    private double longitude;


    @Column(name="sc_event_time", nullable = false)
    private Date scEventTime;

    @Column(name="sc_request_id", nullable = false, length = 40)
    private String scRequestId;

    @CreatedBy
    @Column(name="created_by", nullable = false)
    protected Integer createdBy;

    @CreatedDate
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
