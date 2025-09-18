package org.wm.api.dto;

import com.fasterxml.jackson.annotation.*;
import java.util.ArrayList;
import java.util.List;
import org.wm.api.model.Pedido;
import org.wm.api.model.Producto;

public record DTOProducto(
    @JsonInclude(
        JsonInclude.Include.NON_NULL
    )
    Long id,
    String nombre,
    int cantidad,
    float precio,
    @JsonInclude(
        JsonInclude.Include.NON_NULL
    )
    List<DTOPedido> pedidos
) {
    public static DTOProducto valueOf(Producto producto) {
        if (producto == null) {
            return null;
        }
        
        List<Pedido> pedidos = producto.getPedidos();
        List<DTOPedido> listPedidos = new ArrayList<>();
        
        //if (pedidos != null) {
        //    listPedidos.addAll(pedidos.stream()
        //            .map((value) -> DTOPedido.valueOf(value))
        //            .toList());
        //}
        
        return new DTOProducto(
            producto.getIdProducto(),
            producto.getNombre(),
            producto.getCantidad(),
            producto.getPrecio(),
            listPedidos
        );
    }
}
