package com.carolina.app.autotrack.dto.vehicle;

import com.carolina.app.autotrack.dto.fuelRecord.FuelRecordSummaryResponse;

import java.util.List;

public record VehicleDetailsResponse(Long id, String brand, String model, Integer year,
                                     List<FuelRecordSummaryResponse> fuelRecords) {
}
