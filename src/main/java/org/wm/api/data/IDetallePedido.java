package org.wm.api.data;

import org.wm.api.model.DetallePedido;

public record IDetallePedido(
    Long id,
    int cantidad,
    IPedido pedido,
    IProducto producto
) {
    public static IDetallePedido valueOf(DetallePedido model) {
        if (model == null) {
            return null;
        }
        return new IDetallePedido(
                model.getIdDetallePedido(),
                model.getCantidad(),
                IPedido.valueOf(model.getPedido()),
                IProducto.valueOf(model.getProducto())
        );
    }
}
