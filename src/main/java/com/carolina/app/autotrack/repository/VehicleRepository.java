
package com.carolina.app.autotrack.repository;

import com.carolina.app.autotrack.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carol
 */
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long>{
    
}
