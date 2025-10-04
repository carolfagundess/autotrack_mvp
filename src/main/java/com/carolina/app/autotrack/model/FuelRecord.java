
package com.carolina.app.autotrack.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author carol
 */
@Entity(name = "tb_fuelrecord")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FuelRecord {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)        
    Long id;
    @NotNull(message = "A data não pode ser nula")
    LocalDate date;
    @NotNull(message = "A leitura do odômetro não pode ser nula")
    @PositiveOrZero(message = "O odômetro não pode ser negativo")
    Long odometerReading;
    @DecimalMax(value = "3500.00", message = "O número não pode exceder 3500.00")
    @DecimalMin(value = "0.01", message = "O número deve ser maior que 0.01")
    @Column(precision = 6, scale = 2, nullable = false)
    BigDecimal liters;
    @DecimalMax(value = "99.99", message = "O número não pode exceder 99.99")
    @DecimalMin(value = "0.01", message = "O número deve ser maior que 0.01")
    @Column(precision = 4, scale = 2, nullable = false)
    BigDecimal pricePerLiter;
    Boolean fullTank;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id") //chave estrangeira na tabela
    private Vehicle vehicle;

}
