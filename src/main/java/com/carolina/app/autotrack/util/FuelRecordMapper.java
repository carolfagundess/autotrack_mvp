package com.carolina.app.autotrack.util;

import com.carolina.app.autotrack.dto.fuelRecord.FuelRecordPatchRequest;
import com.carolina.app.autotrack.dto.fuelRecord.FuelRecordRequest;
import com.carolina.app.autotrack.dto.fuelRecord.FuelRecordResponse;
import com.carolina.app.autotrack.dto.fuelRecord.FuelRecordSummaryResponse;
import com.carolina.app.autotrack.dto.vehicle.VehicleSummaryResponse;
import com.carolina.app.autotrack.model.FuelRecord;
import com.carolina.app.autotrack.model.Vehicle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FuelRecordMapper {

    public FuelRecord toEntity(FuelRecordRequest fuelRecordRequest, Vehicle vehicle) {

        FuelRecord fuelRecord = new FuelRecord();
        fuelRecord.setDate(fuelRecordRequest.date());
        fuelRecord.setOdometerReading(fuelRecordRequest.odometerReading());
        fuelRecord.setLiters(fuelRecordRequest.liters());
        fuelRecord.setPricePerLiter(fuelRecordRequest.pricePerLiter());
        fuelRecord.setVehicle(vehicle);
        return fuelRecord;
    }

    public FuelRecordResponse toResponse(FuelRecord fuelRecord, VehicleSummaryResponse vehicleSummaryResponse) {
        Vehicle vehicle = fuelRecord.getVehicle();
        // Retorna o DTO de resposta completo
        return new FuelRecordResponse(
                fuelRecord.getId(),
                fuelRecord.getDate(),
                fuelRecord.getOdometerReading(),
                fuelRecord.getLiters(),
                fuelRecord.getPricePerLiter(),
                vehicleSummaryResponse
        );
    }

    //cria um FuelRecord Resumido
    public FuelRecordSummaryResponse toSummaryResponse(FuelRecord fuelRecord) {
        return new FuelRecordSummaryResponse(fuelRecord.getId(), fuelRecord.getDate(), fuelRecord.getOdometerReading(), fuelRecord.getLiters(), fuelRecord.getPricePerLiter());
    }

    //converte FuelRecords em uma lista de FuelRecords resumidas
    public List<FuelRecordSummaryResponse> toSummaryResponseList(List<FuelRecord> fuelRecords) {
        if (fuelRecords == null) {
            return List.of();
        }
        return fuelRecords.stream().map(this::toSummaryResponse).collect(Collectors.toList());
    }

    public void patchEntity(FuelRecordPatchRequest fuelRecordRequest, FuelRecord fuelRecord) {
        if (fuelRecordRequest.date() != null) {
            fuelRecord.setDate(fuelRecordRequest.date());
        }
        if (fuelRecordRequest.odometerReading() != null) {
            fuelRecord.setOdometerReading(fuelRecordRequest.odometerReading());
        }
        if (fuelRecordRequest.liters() != null) {
            fuelRecord.setLiters(fuelRecordRequest.liters());
        }
        if (fuelRecordRequest.pricePerLiter() != null) {
            fuelRecord.setPricePerLiter(fuelRecordRequest.pricePerLiter());
        }
    }

}
