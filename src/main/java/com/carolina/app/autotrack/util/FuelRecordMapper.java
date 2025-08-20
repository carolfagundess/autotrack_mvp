package com.carolina.app.autotrack.util;

import com.carolina.app.autotrack.dto.FuelRecordRequest;
import com.carolina.app.autotrack.dto.FuelRecordResponse;
import com.carolina.app.autotrack.dto.VehicleResponse;
import com.carolina.app.autotrack.model.FuelRecord;
import org.springframework.stereotype.Component;

@Component
public class FuelRecordMapper {

    public FuelRecord toEntity(FuelRecordRequest fuelRecordRequest) {
        if (fuelRecordRequest == null) {
            return null;
        }
        FuelRecord fuelRecord = new FuelRecord();
        fuelRecord.setDate(fuelRecordRequest.date());
        fuelRecord.setOdometerReading(fuelRecordRequest.odometerReading());
        fuelRecord.setLiters(fuelRecordRequest.liters());
        fuelRecord.setPricePerLiter(fuelRecordRequest.pricePerLiter());
        return fuelRecord;
    }

    public FuelRecordResponse toResponse(FuelRecord fuelRecord) {
        if (fuelRecord == null) {
            return null;
        }
        return new FuelRecordResponse(fuelRecord.getId(),
                fuelRecord.getDate(),
                fuelRecord.getOdometerReading(),
                fuelRecord.getLiters(),
                fuelRecord.getPricePerLiter(),
                new VehicleResponse(fuelRecord.getVehicle()));
    }
}
