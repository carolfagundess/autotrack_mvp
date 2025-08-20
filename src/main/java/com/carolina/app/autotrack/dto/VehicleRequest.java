package com.carolina.app.autotrack.dto;

import com.carolina.app.autotrack.model.Vehicle;
import jakarta.validation.constraints.*;

public record VehicleRequest(@NotBlank @NotNull String brand, @NotBlank @NotNull String model,
                             @Positive(message = "O valor deve ser positivo") @Min(value = 1950, message = "O ano mínimo é 1950") Integer year) {

    public Vehicle toEntity() {
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand(brand);
        vehicle.setModel(model);
        vehicle.setYear(year);
        return vehicle;
    }

}
