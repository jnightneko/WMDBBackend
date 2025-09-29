package org.wm.api.data;

import org.wm.api.model.Producto;

public record IProducto(
    Long id,
    String nombre,
    String ruta,
    int cantidad,
    float precio
) {
    public static IProducto valueOf(Producto model) {
        if (model == null) {
            return null;
        }
        return new IProducto(
                model.getIdProducto(),
                model.getNombre(),
                model.getImagen(),
                model.getCantidad(),
                model.getPrecio());
    }
}
