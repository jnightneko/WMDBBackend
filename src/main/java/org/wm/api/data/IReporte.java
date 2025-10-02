package org.wm.api.data;

import java.util.List;

public record IReporte(
   ICliente cliente,
   List<IDetallePedido> detalles,
   int pedidosRealizado,
   int productosComprados
) {
    
}
