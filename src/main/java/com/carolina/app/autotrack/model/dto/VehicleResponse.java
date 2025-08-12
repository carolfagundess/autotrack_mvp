package com.carolina.app.autotrack.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

public record VehicleResponse(@JsonProperty("_id") Long id,
                              @NotNull @NotBlank @Length(max = 30) String brand,
                              @NotNull @NotBlank @Length(min = 5, max = 20) String model,
                              @Positive(message = "O valor deve ser positivo")
                              @Min(value = 1950, message = "O ano mínimo é 1950")
                              Integer year) {
}
