package com.carolina.app.autotrack.service;

import com.carolina.app.autotrack.dto.fuelRecord.FuelRecordPatchRequest;
import com.carolina.app.autotrack.dto.fuelRecord.FuelRecordRequest;
import com.carolina.app.autotrack.dto.fuelRecord.FuelRecordResponse;
import com.carolina.app.autotrack.model.FuelRecord;
import com.carolina.app.autotrack.model.Vehicle;
import com.carolina.app.autotrack.repository.FuelRecordRepository;
import com.carolina.app.autotrack.util.FuelRecordMapper;
import com.carolina.app.autotrack.util.VehicleMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class FuelRecordService {

    private final FuelRecordRepository fuelRecordRepository;
    private final VehicleService vehicleService;
    private final FuelRecordMapper fuelRecordMapper;
    private final VehicleMapper vehicleMapper;



    public FuelRecordResponse save(FuelRecordRequest fuelRecordRequest) {
        Vehicle foundVehicle = vehicleService.getVehicleEntityById(fuelRecordRequest.vehicleId());
        FuelRecord fuelRecordToSave = fuelRecordMapper.toEntity(fuelRecordRequest, foundVehicle);
        FuelRecord fuelRecord = fuelRecordRepository.save(fuelRecordToSave);
        return fuelRecordMapper.toResponse(fuelRecord, vehicleMapper.toSummaryResponse(fuelRecord.getVehicle()));
    }

    //AJUSTADO
    public List<FuelRecordResponse> getAll() {
        // 1. Encontra todas as entidades FuelRecord
        List<FuelRecord> listFuelRecords = fuelRecordRepository.findAll();
        // 2. Mapeia cada entidade para o DTO de resposta
        return listFuelRecords.stream()
                .map(fuelRecord -> {
                    // A. Pega a entidade Vehicle do FuelRecord
                    Vehicle vehicle = fuelRecord.getVehicle();
                    // B. Usa o VehicleMapper para criar o DTO de resumo
                    // C. Usa o FuelRecordMapper para criar o DTO de resposta,
                    // passando o DTO de resumo do veículo
                    return fuelRecordMapper.toResponse(fuelRecord,  vehicleMapper.toSummaryResponse(vehicle));
                })
                .collect(Collectors.toList());
    }

    //AJUSTADO
    public FuelRecordResponse getById(Long id) {
        FuelRecord fuelRecord = getFuelRecordOrThrowNotFound(id);
        Vehicle vehicle = fuelRecord.getVehicle();
        //ENVIANDO UM SUMMARY RESPONSE VEHICLE
        return fuelRecordMapper.toResponse(fuelRecord, vehicleMapper.toSummaryResponse(vehicle));
    }

    public FuelRecordResponse update(Long id, FuelRecordRequest fuelRecordRequest) {
        FuelRecord fuelRecordToUpdated =  getFuelRecordOrThrowNotFound(id);

        Vehicle foundVehicle = vehicleService.getVehicleEntityById(fuelRecordRequest.vehicleId());

        fuelRecordToUpdated.setDate(fuelRecordRequest.date());
        fuelRecordToUpdated.setOdometerReading(fuelRecordRequest.odometerReading());
        fuelRecordToUpdated.setLiters(fuelRecordRequest.liters());
        fuelRecordToUpdated.setPricePerLiter(fuelRecordRequest.pricePerLiter());
        fuelRecordToUpdated.setVehicle(foundVehicle);

        return fuelRecordMapper.toResponse(fuelRecordRepository.saveAndFlush(fuelRecordToUpdated), vehicleMapper.toSummaryResponse(fuelRecordToUpdated.getVehicle()));
    }

    public FuelRecordResponse patch(Long id, FuelRecordPatchRequest request) {
        FuelRecord fuelRecord = getFuelRecordOrThrowNotFound(id);
        fuelRecordMapper.patchEntity(request, fuelRecord);
        FuelRecord updatedFuelRecord = fuelRecordRepository.saveAndFlush(fuelRecord);
        return fuelRecordMapper.toResponse(updatedFuelRecord,  vehicleMapper.toSummaryResponse(fuelRecord.getVehicle()));
    }

    public void deleteById(Long id){
        if (!fuelRecordRepository.existsById(id)){
            throw new EntityNotFoundException("FuelRecord not found with id: " + id);
        }
        fuelRecordRepository.deleteById(id);
    }


    /**Método auxiliar interno para buscar um fuelrecord ou retornar nulo**/
    private FuelRecord getFuelRecordOrThrowNotFound(Long id) {
        return fuelRecordRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fuel record not found with id: " + id));
    }
}
