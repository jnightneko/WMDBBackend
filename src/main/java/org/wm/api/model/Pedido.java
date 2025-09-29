package org.wm.api.model;

import jakarta.persistence.*;
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
    
    @Lob
    private String ubicacion;
    
    @ManyToOne
    @JoinColumn(
        name = "idCliente"
    )
    private Cliente cliente;
}
