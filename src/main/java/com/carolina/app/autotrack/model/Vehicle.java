
package com.carolina.app.autotrack.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

import java.io.Serializable;


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

    @NotNull
    @NotBlank
    @Column(length = 200, nullable = false)
    String brand;
    @NotNull
    @NotBlank
    @Column(length = 20, nullable = false)
    String model;
    @Positive(message = "O valor deve ser positivo")
    @Min(value = 1900, message = "O ano mínimo é 1900")
    Integer year;
}
