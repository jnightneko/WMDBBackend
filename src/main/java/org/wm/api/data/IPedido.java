package org.wm.api.data;

import org.wm.api.model.Pedido;

public record IPedido(
    Long id,
    float total,
    ICliente cliente,
    String ubicacion,
    String foto
) {
    public static IPedido valueOf(Pedido model) {
        if (model == null) {
            return null;
        }
        return new IPedido(
                model.getIdPedido(),
                model.getTotal(),
                ICliente.valueOf(model.getCliente()),
                model.getUbicacion(),
                model.getFoto()
        );
    }
}
