package com.carolina.app.autotrack.dto.vehicle;

import com.carolina.app.autotrack.dto.fuelRecord.FuelRecordSummaryResponse;
import com.carolina.app.autotrack.model.Vehicle;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record VehicleResponse(@JsonProperty("_id") Long id,
                              String brand,
                              String model,
                              Integer year,
                              List<FuelRecordSummaryResponse> fuelRecordSummaryResponseList){
}
