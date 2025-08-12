package com.carolina.app.autotrack.model.dto;


import com.carolina.app.autotrack.model.Vehicle;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VehicleResponseDTO {

    private Long id;
    @NotNull
    @NotBlank
    @Column(length = 200, nullable = false)
    private String brand;
    @NotNull
    @NotBlank
    @Column(length = 20, nullable = false)
    private String model;
    @Positive(message = "O valor deve ser positivo")
    @Min(value = 1900, message = "O ano mínimo é 1900")
    private Integer year;

    public VehicleResponseDTO(Vehicle vehicle) {
        this.id = vehicle.getId();
        this.brand = vehicle.getBrand();
        this.model = vehicle.getModel();
        this.year = vehicle.getYear();
    }
}
