
package com.example.smartcar.location;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface LocationRepository extends CrudRepository<Location, Integer> {

    Optional<Location> findByTelematicsRequestId(Integer telematicsRequestId);

    Location update(Location updated);

}
