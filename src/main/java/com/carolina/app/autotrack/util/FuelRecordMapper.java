package com.carolina.app.autotrack.util;

import com.carolina.app.autotrack.dto.fuelRecord.FuelRecordRequest;
import com.carolina.app.autotrack.dto.fuelRecord.FuelRecordResponse;
import com.carolina.app.autotrack.dto.fuelRecord.FuelRecordSummaryResponse;
import com.carolina.app.autotrack.model.FuelRecord;
import com.carolina.app.autotrack.model.Vehicle;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
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

    public FuelRecordResponse toResponse(FuelRecord fuelRecord) {
        return new FuelRecordResponse(fuelRecord.getId(),
                fuelRecord.getDate(),
                fuelRecord.getOdometerReading(),
                fuelRecord.getLiters(),
                fuelRecord.getPricePerLiter(),
                //usa o metodo do vehicle mapper para gerar um resumo do veiculo associado ao fuelrecord
                VehicleMapper.toSummaryResponse(fuelRecord.getVehicle()));
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
}
