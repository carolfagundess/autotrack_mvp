package com.carolina.app.autotrack.service;

import com.carolina.app.autotrack.model.FuelRecord;
import com.carolina.app.autotrack.model.Vehicle;
import com.carolina.app.autotrack.repository.FuelRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FuelRecordService {

    private final FuelRecordRepository fuelRecordRepository;

    public FuelRecord save(FuelRecord fuelRecord) {

    }

    public List<FuelRecord> getFuelRecords(Vehicle vehicle) { return fuelRecordRepository.findAll();}
}
