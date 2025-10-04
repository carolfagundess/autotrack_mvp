package com.carolina.app.autotrack.dto.fuelRecord;

import com.carolina.app.autotrack.dto.vehicle.VehicleSummaryResponse;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FuelRecordResponse(Long id,
                                 LocalDate date,
                                 Long odometerReading,
                                 BigDecimal liters,
                                 BigDecimal pricePerLiter,
                                 Boolean fullTank,
                                 //DTO resumido para expor o carro em questão
                                 VehicleSummaryResponse vehicleResponse) {

}
