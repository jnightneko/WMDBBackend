package org.wm.api.data;

import java.util.List;

public record IClientePedido(
    IUsuario usuario,
    List<IClientePProducto> productos,
    String ubicacion
) {

}
