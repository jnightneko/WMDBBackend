package org.wm.api.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "DetallePedido")
@Table(name = "DetallePedido")
public class DetallePedido {
     
    @Id    
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private Long idDetallePedido;
    
    private Integer cantidad;
    
    @ManyToOne
    @JoinColumn(
        name = "idPedido"
    )
    private Pedido pedido;
    
    @ManyToOne
    @JoinColumn(
        name = "idProducto"
    )
    private Producto producto;
}
