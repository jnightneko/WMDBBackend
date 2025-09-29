package org.wm.api.model;

import jakarta.persistence.*;
import java.util.List;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Producto")
@Table(name = "Producto")
public class Producto {
    
    @Id    
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    @Column(
        unique = true,
        nullable = false
    )
    private Long idProducto;
    
    @Column(
        length = 50
    )
    private String nombre;
    private String imagen;
    
    private Integer cantidad;
    private Float precio;
}
