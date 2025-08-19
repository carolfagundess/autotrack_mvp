package com.carolina.app.autotrack.service;

import com.carolina.app.autotrack.model.Vehicle;
import com.carolina.app.autotrack.repository.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author carol
 */
@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> getAll() {
        return vehicleRepository.findAll();
    }

    public Vehicle getById(Long id) {
        return vehicleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Veículo não encontrado"));
    }

    public Vehicle save(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public Vehicle update(Long id, Vehicle vehicleRequest) {
        Vehicle vehicleToUpdate = getById(id);

        vehicleToUpdate.setBrand(vehicleRequest.getBrand());
        vehicleToUpdate.setModel(vehicleRequest.getModel());
        vehicleToUpdate.setYear(vehicleRequest.getYear());

        return vehicleRepository.save(vehicleToUpdate);
    }

    public Vehicle patch(Long id, @Valid Vehicle vehicle) {
        return null;
    }

    public void delete(Long id) {
        if (!vehicleRepository.existsById(id)) {
            throw new EntityNotFoundException("Veículo não encontrado");
        }
        vehicleRepository.deleteById(id);
    }
}
