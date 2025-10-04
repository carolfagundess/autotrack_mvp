package com.carolina.app.autotrack.dto.fuelRecord;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDate;


public record FuelRecordRequest(Long id,
                                @NotNull(message = "A data não pode ser nula")
                                LocalDate date,
                                @NotNull(message = "A leitura do odômetro não pode ser nula")
                                @PositiveOrZero(message = "O odômetro não pode ser negativo")
                                Long odometerReading,
                                @DecimalMax(value = "3500.00", message = "O número não pode exceder 3500.00")
                                @DecimalMin(value = "0.01", message = "O número deve ser maior que 0.01")
                                BigDecimal liters,
                                @DecimalMax(value = "99.99", message = "O número não pode exceder 99.99")
                                @DecimalMin(value = "0.01", message = "O número deve ser maior que 0.01")
                                BigDecimal pricePerLiter,
                                Boolean fullTank,
                                //precisa ter este id salvo para criar um novo fuelrecord
                                @NotNull(message = "O ID do veículo é obrigatório")
                                Long vehicleId) {
}
