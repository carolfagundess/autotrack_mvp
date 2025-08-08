
package com.carolina.app.autotrack.service;

import com.carolina.app.autotrack.model.Vehicle;
import com.carolina.app.autotrack.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author carol
 */
@Service
public class VehicleService {

    private VehicleRepository vehicleRepository;

    @Autowired
    public void setVehicleRepository(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicleById(Long id){
        return vehicleRepository.findById(id).orElseThrow(() -> new RuntimeException("Veículo não encontrado"));
    }
}
