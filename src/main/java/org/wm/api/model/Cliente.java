package org.wm.api.model;

import jakarta.persistence.*;
import java.util.List;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Cliente")
@Table(name = "Cliente")
public class Cliente {
    
    @Id    
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private Long idCliente;
    
    @Column(
        length = 50
    )
    private String nombre;
    
    @OneToMany(
        mappedBy = "cliente"
    )
    private List<Pedido> pedidos;
}
