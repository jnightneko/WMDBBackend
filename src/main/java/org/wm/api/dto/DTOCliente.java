package org.wm.api.dto;

import com.fasterxml.jackson.annotation.*;
import java.util.ArrayList;
import java.util.List;
import org.wm.api.model.Cliente;
import org.wm.api.model.Pedido;

public record DTOCliente(
    @JsonInclude(
        JsonInclude.Include.NON_NULL
    )
    Long id,
    String nombre,
    @JsonInclude(
        JsonInclude.Include.NON_NULL
    )
    List<DTOPedido> pedidos
) {    
    public static DTOCliente valueOf(Cliente cliente) {
        if (cliente == null) {
            return null;
        }
        
        List<Pedido> pedidos = cliente.getPedidos();
        List<DTOPedido> listPedidos = new ArrayList<>();
        
        //if (pedidos != null) {
        //    listPedidos.addAll(pedidos.stream()
        //            .map((value) -> DTOPedido.valueOf(value))
        //            .toList());
        //}
        
        return new DTOCliente(cliente.getIdCliente(), cliente.getNombre(), listPedidos);
    }
}
