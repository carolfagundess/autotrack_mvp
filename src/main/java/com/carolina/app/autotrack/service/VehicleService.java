package com.carolina.app.autotrack.service;

import com.carolina.app.autotrack.dto.fuelRecord.FuelRecordSummaryResponse;
import com.carolina.app.autotrack.dto.vehicle.VehicleDetailsResponse;
import com.carolina.app.autotrack.dto.vehicle.VehiclePatchRequest;
import com.carolina.app.autotrack.dto.vehicle.VehicleRequest;
import com.carolina.app.autotrack.dto.vehicle.VehicleResponse;
import com.carolina.app.autotrack.model.Vehicle;
import com.carolina.app.autotrack.repository.VehicleRepository;
import com.carolina.app.autotrack.util.FuelRecordMapper;
import com.carolina.app.autotrack.util.VehicleMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author carol
 */
@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;
    private final FuelRecordMapper fuelRecordMapper;

    //AJUSTADO
    @Transactional
    public VehicleResponse save(VehicleRequest request) {
        Vehicle vehicle = vehicleMapper.toEntity(request);
        Vehicle vehicleSaved = vehicleRepository.save(vehicle);
        return vehicleMapper.toResponse(vehicleSaved);
    }

    //AJUSTADO
    @Transactional(readOnly = true)
    public List<VehicleResponse> getAll() {
        List<Vehicle> listVehicles = vehicleRepository.findAll();
        // Mapeia a lista de entidades para uma lista de DTOs de resposta
        return listVehicles.stream()
                .map(vehicleMapper::toResponse)
                .collect(Collectors.toList());
    }

    //AJUSTADO - usado no fuelrecord **validar
    @Transactional(readOnly = true)
    public VehicleResponse getById(Long id) {
        Vehicle foundVehicle = getVehicleOrThrow(id);
        return vehicleMapper.toResponse(foundVehicle);
    }

    @Transactional(readOnly = true)
    public VehicleDetailsResponse getVehicleDetails(Long id) {
        Vehicle foundVehicle = getVehicleOrThrow(id);

        List<FuelRecordSummaryResponse> fuelRecordSummaryResponseList = foundVehicle.getFuelRecords().stream()
                .map(fuelRecordMapper::toSummaryResponse)
                .toList();

        return new VehicleDetailsResponse(
                foundVehicle.getId(),
                foundVehicle.getBrand(),
                foundVehicle.getModel(),
                foundVehicle.getYear(),
                fuelRecordSummaryResponseList
        );
    }

    //AJUSTADO
    @Transactional
    public VehicleResponse update(Long id, VehicleRequest request) {
        Vehicle vehicleToUpdate = getVehicleOrThrow(id);

        Vehicle vehicleRequest = vehicleMapper.toEntity(request);
        vehicleToUpdate.setBrand(vehicleRequest.getBrand());
        vehicleToUpdate.setModel(vehicleRequest.getModel());
        vehicleToUpdate.setYear(vehicleRequest.getYear());

        return vehicleMapper.toResponse(vehicleRepository.save(vehicleToUpdate));
    }

    //AJUSTADO //recupera o objeto e envia para o metodo do mapper
    @Transactional
    public VehicleResponse patch(Long id, VehiclePatchRequest vehiclePatchRequest) {
        Vehicle vehicleToUpdate = getVehicleOrThrow(id);
        vehicleMapper.patchEntity(vehiclePatchRequest, vehicleToUpdate);
        Vehicle updatedVehicle = vehicleRepository.saveAndFlush(vehicleToUpdate);
        return vehicleMapper.toResponse(updatedVehicle);
    }

    @Transactional
    public void delete(Long id) {
        if (!vehicleRepository.existsById(id)) {
            throw new EntityNotFoundException("Vehicle not found with id: " + id);
        }
        vehicleRepository.deleteById(id);
    }

    /**
     * Método auxiliar interno para buscar um veículo ou lançar exceção
     *
     * @param id O ID do veículo a ser buscado.
     * @throws EntityNotFoundException Se o veículo não for encontrado.
     */
    private Vehicle getVehicleOrThrow(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found with id: " + id));
    }

    /**
     * Método auxiliar externo para buscar um veiculo, uso em services externos
     */
    public Vehicle getVehicleEntityById(Long id) {
        return getVehicleOrThrow(id);
    }
}
