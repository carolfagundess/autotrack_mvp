package com.carolina.app.autotrack.dto;

import com.carolina.app.autotrack.model.Vehicle;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

public record VehicleResponse(@JsonProperty("_id") Long id,
                              String brand,
                              String model,
                              Integer year) {

    public VehicleResponse(Vehicle vehicle) {
        this(vehicle.getId(), vehicle.getBrand(), vehicle.getModel(), vehicle.getYear());
    }
}
