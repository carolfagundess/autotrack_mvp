
package com.carolina.app.autotrack.service;

import com.carolina.app.autotrack.model.Vehicle;
import com.carolina.app.autotrack.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Vehicle saveVehicle(Vehicle vehicle){
        return vehicleRepository.save(vehicle);
    }

    public Vehicle UpdateVehicle(Long id, Vehicle vehicle){
        Vehicle vehicleSaved = getVehicleById(id);
        Vehicle vehicleNew = Vehicle.builder()
                .id(vehicleSaved.getId())
                .brand(vehicle.getBrand() != null ? vehicle.getBrand() : vehicleSaved.getBrand())
                .year(vehicle.getYear() != null ? vehicle.getYear() : vehicleSaved.getYear())
                .build();
        return vehicleRepository.saveAndFlush(vehicleNew);
    }
}
