package com.carolina.app.autotrack.dto;

import com.carolina.app.autotrack.model.Vehicle;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record VehicleRequest(@NotBlank @NotNull String brand,
                             @NotBlank @NotNull String model,
                             @Positive(message = "O valor deve ser positivo")
                             @Min(value = 1950, message = "O ano mínimo é 1950")
                             Integer year) {

    public Vehicle toEntity() {
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand(brand);
        vehicle.setModel(model);
        vehicle.setYear(year);
        return vehicle;
    }

}
