
package com.example.smartcar.location;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface LocationRepository extends CrudRepository<Location, Integer> {

//    Iterable<Location> findAll(Sort var1);

//    Optional<Location> findById(Integer id);


}
