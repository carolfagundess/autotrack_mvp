package com.carolina.app.autotrack.service;

import com.carolina.app.autotrack.dto.fuelRecord.FuelRecordSummaryResponse;
import com.carolina.app.autotrack.dto.vehicle.*;
import com.carolina.app.autotrack.model.FuelRecord;
import com.carolina.app.autotrack.model.Vehicle;
import com.carolina.app.autotrack.repository.VehicleRepository;
import com.carolina.app.autotrack.util.FuelRecordMapper;
import com.carolina.app.autotrack.util.VehicleMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
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

    // TO DO
    public GeneralStatsDTO calculateVehiclePerformance(Long id) {
        Vehicle vehicle = getVehicleOrThrow(id);
        List<FuelRecord> fuelRecords = vehicle.getFuelRecords();

        //calculando o total de litros usados
        BigDecimal totalLiters = fuelRecords.stream()
                .map(FuelRecord::getLiters)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        //calculando o custo com base nos litros multiplicados pelo preco da gasolina no dia
        BigDecimal totalCost = fuelRecords.stream()
                .map(fuelRecord -> fuelRecord.getLiters().multiply(fuelRecord.getPricePerLiter()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        fuelRecords.sort(Comparator.comparing(FuelRecord::getDate));

        //calculando a distancia
        BigDecimal totalDistance = BigDecimal.valueOf(fuelRecords.getLast().getOdometerReading())
                .subtract(BigDecimal.valueOf(fuelRecords.getFirst().getOdometerReading()));

        BigDecimal averageFuelConsumption = (totalLiters.compareTo(BigDecimal.ZERO) > 0)
                ? totalDistance.divide(totalLiters, 2, RoundingMode.HALF_UP)
                : BigDecimal.ZERO;

        BigDecimal averageCostPerKm = (totalDistance.compareTo(BigDecimal.ZERO) > 0)
                ? totalCost.divide(totalDistance, 2, RoundingMode.HALF_UP)
                : BigDecimal.ZERO; //
        return new GeneralStatsDTO(
                vehicle.getId(),
                vehicle.getBrand(),
                vehicle.getModel(),
                vehicle.getYear(),
                averageFuelConsumption,
                averageCostPerKm,
                totalDistance,
                totalCost,
                totalLiters
        );
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
