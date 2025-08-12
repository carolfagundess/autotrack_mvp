package com.carolina.app.autotrack.model.dto;


import com.carolina.app.autotrack.model.Vehicle;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleRequestDTO implements Serializable {

    @NotNull
    @NotBlank
    private String brand;
    @NotNull
    @NotBlank
    @Column(length = 200, nullable = false)
    private String model;
    @Positive(message = "O valor deve ser positivo")
    @Min(value = 1900, message = "O ano mínimo é 1900")
    private Integer year;

    public Vehicle toEntity() {
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand(brand);
        vehicle.setModel(model);
        vehicle.setYear(year);
        return vehicle;
    }


}

