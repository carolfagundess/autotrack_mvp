
package com.carolina.app.autotrack.model;

import jakarta.persistence.*;
import lombok.*;


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
    Integer year;

}
