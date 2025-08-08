
package com.carolina.app.autotrack.model;

import jakarta.persistence.*;
import lombok.*;

/**
 *
 * @author carol
 */
@Entity(name = "tb_vehicle")
@Table
@Getter
@Setter
@NoArgsConstructor   // construtor sem argumentos
@AllArgsConstructor  // construtor com todos os argumentos
@Builder
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)        
    Long id;
    String brand;
    String model;
    Integer year;
    String plate;
}
