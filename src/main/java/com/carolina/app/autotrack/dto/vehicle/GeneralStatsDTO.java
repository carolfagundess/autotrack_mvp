package com.carolina.app.autotrack.dto.vehicle;

import java.math.BigDecimal;

public record GeneralStatsDTO(Long id, String brand, String model, Integer year,
                              BigDecimal averageFuelConsumption, BigDecimal averageCostPerKilometer,
                              BigDecimal totalDistance,
                              BigDecimal totalCost,
                              BigDecimal totalConsumption

) {
}
