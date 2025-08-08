
package com.carolina.app.autotrack.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

/**
 *
 * @author carol
 */
@Entity(name = "tb_fuelrecord")
public class FuelRecord {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)        
    Long id;
    LocalDate date;
    Double odometerReading; //quilometragem atual
    Double liters;
    Double pricePerLiter;  
    Double kmPeLiter; //consumo calculados
    Double costPerKm; //custo calculado
}
