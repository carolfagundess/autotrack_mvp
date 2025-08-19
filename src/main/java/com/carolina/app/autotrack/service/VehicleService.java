
package com.carolina.app.autotrack.service;

import com.carolina.app.autotrack.model.Vehicle;
import com.carolina.app.autotrack.repository.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author carol
 */
@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) { this.vehicleRepository = vehicleRepository;}

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicleById(Long id){
        return vehicleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Veículo não encontrado"));
    }

    public Vehicle saveVehicle(Vehicle vehicle){
        return vehicleRepository.save(vehicle);
    }

    public Vehicle updateVehicle(Long id, Vehicle vehicleRequest){
        Vehicle vehicleToUpdate = getVehicleById(id);

        vehicleToUpdate.setBrand(vehicleRequest.getBrand());
        vehicleToUpdate.setModel(vehicleRequest.getModel());
        vehicleToUpdate.setYear(vehicleRequest.getYear());

        return vehicleRepository.save(vehicleToUpdate);
    }

    public void deleteVehicle(Long id){
        if(!vehicleRepository.existsById(id)){
            throw new EntityNotFoundException("Veículo não encontrado");
        }
        vehicleRepository.deleteById(id);
    }
}
