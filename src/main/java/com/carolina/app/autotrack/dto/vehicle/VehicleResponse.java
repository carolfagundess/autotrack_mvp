package com.carolina.app.autotrack.dto.vehicle;

import com.fasterxml.jackson.annotation.JsonProperty;

public record VehicleResponse(@JsonProperty("_id") Long id,
                              String brand,
                              String model,
                              Integer year) {
}
