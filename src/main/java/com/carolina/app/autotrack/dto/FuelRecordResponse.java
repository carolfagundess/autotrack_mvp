package com.carolina.app.autotrack.dto;

import com.carolina.app.autotrack.model.Vehicle;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FuelRecordResponse(Long id,
                                 LocalDate date,
                                 Long odometerReading,
                                 BigDecimal liters,
                                 BigDecimal pricePerLiter,
                                 VehicleResponse vehicle) {

}
