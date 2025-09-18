package org.wm.api.dto;

import com.fasterxml.jackson.annotation.*;
import java.util.ArrayList;
import java.util.List;
import org.wm.api.model.Pedido;
import org.wm.api.model.Producto;

public record DTOPedido(
    @JsonInclude(
        JsonInclude.Include.NON_NULL
    )
    Long id,
    @JsonInclude(
        JsonInclude.Include.NON_NULL
    )
    List<DTOProducto> productos,
    @JsonInclude(
        JsonInclude.Include.NON_NULL
    )
    DTOCliente cliente,
    float total
) {
    public static DTOPedido valueOf(Pedido pedido) {
        if (pedido == null) {
            return null;
        }
        
        List<Producto> productos = pedido.getProductos();
        List<DTOProducto> listProductos = new ArrayList<>();
        
        if (productos != null) {
            listProductos.addAll(productos.stream()
                    .map((value) -> DTOProducto.valueOf(value))
                    .toList());
        }
        
        
        return new DTOPedido(pedido.getIdPedido(), listProductos, DTOCliente.valueOf(pedido.getCliente()), pedido.getTotal());
    }
}
