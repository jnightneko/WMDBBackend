package org.wm.api.data;

import org.wm.api.model.Cliente;

public record ICliente(
    Long id,
    String nombre,
    IUsuario usuario
) {
    public static ICliente valueOf(Cliente model) {
        return new ICliente(
                model.getIdCliente(),
                model.getNombre(),
                IUsuario.valueOf(model.getUsuario())
        );
    }
}
