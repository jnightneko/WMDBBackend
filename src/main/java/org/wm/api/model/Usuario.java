package org.wm.api.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Usuario")
@Table(name = "Usuario")
public class Usuario {
    
    @Id    
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private Long idUsuario;
    
    private String nombre;
    private String contrasenia;
    
    @ManyToOne
    @JoinColumn(
        name = "idRol"
    )
    private Rol rol;
}
