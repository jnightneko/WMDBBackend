package org.wm.api.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Rol")
@Table(name = "Rol")
public class Rol {
    
    @Id    
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private Long idRol;
    
    private String nombre;
}
