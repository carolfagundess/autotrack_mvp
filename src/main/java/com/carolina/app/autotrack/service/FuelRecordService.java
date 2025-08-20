package com.carolina.app.autotrack.service;

import com.carolina.app.autotrack.dto.fuelRecord.FuelRecordRequest;
import com.carolina.app.autotrack.dto.fuelRecord.FuelRecordResponse;
import com.carolina.app.autotrack.model.FuelRecord;
import com.carolina.app.autotrack.model.Vehicle;
import com.carolina.app.autotrack.repository.FuelRecordRepository;
import com.carolina.app.autotrack.util.FuelRecordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class FuelRecordService {

    private final FuelRecordRepository fuelRecordRepository;
    private final VehicleService vehicleService;
    private final FuelRecordMapper fuelRecordMapper;

    public FuelRecordResponse save(FuelRecordRequest fuelRecordRequest) {
        Vehicle vehicle = vehicleService.getById(fuelRecordRequest.vehicleId());
        FuelRecord fuelRecord = fuelRecordMapper.toEntity(fuelRecordRequest,vehicle);
        fuelRecordRepository.saveAndFlush(fuelRecord);
        return fuelRecordMapper.toResponse(fuelRecord);
    }
}
