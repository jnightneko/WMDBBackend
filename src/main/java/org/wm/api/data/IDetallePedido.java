package org.wm.api.data;

import java.util.Date;
import org.wm.api.model.DetallePedido;

public record IDetallePedido(
    Long id,
    int cantidad,
    IPedido pedido,
    IProducto producto,
    Date createdAt,
    Date updatedAt
) {
    public static IDetallePedido valueOf(DetallePedido model) {
        if (model == null) {
            return null;
        }
        return new IDetallePedido(
                model.getIdDetallePedido(),
                model.getCantidad(),
                IPedido.valueOf(model.getPedido()),
                IProducto.valueOf(model.getProducto()),
                model.getCreatedAt(),
                model.getUpdatedAt()
        );
    }
}
