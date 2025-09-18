package org.wm.api.model;

import jakarta.persistence.*;
import java.util.List;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Pedido")
@Table(name = "Pedido")
public class Pedido {
    
    @Id    
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private Long idPedido;
    
    @Column(
        length = 50
    )
    private Float total;
    
    
    @ManyToOne
    @JoinColumn(
        name = "idCliente"
    )
    private Cliente cliente;
    
    @ManyToMany(
        targetEntity = Producto.class
    )
    @JoinTable(
        name = "DetallePedido",
        joinColumns = @JoinColumn(
            name = "idPedido"
        ),
        inverseJoinColumns = @JoinColumn(
            name = "idProducto"
        )
    )
    private List<Producto> productos;
}
