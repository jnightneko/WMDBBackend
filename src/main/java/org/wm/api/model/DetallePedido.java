package org.wm.api.model;

import jakarta.persistence.*;
import java.util.Date;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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
    
    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;
}
