package com.carolina.app.autotrack.dto.fuelRecord;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FuelRecordSummaryResponse(Long id, LocalDate date, Long odometerReading, BigDecimal liters,
                                        BigDecimal pricePerLiter) {
}
