
package com.carolina.app.autotrack.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author carol
 */
@Entity
@Table(name = "tb_vehicle")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)        
    Long id;
    @Column(length = 200, nullable = false)
    String brand;
    @Column(length = 20, nullable = false)
    String model;
    @Column(nullable = false) @Max(2100)
    Integer year;

    @Builder.Default
    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FuelRecord> fuelRecords = new ArrayList<FuelRecord>();
}
