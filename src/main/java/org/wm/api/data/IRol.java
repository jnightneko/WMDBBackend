package org.wm.api.data;

import org.wm.api.model.Rol;

public record IRol(
    Long id,
    String nombre
) {
    public static IRol valueOf(Rol model) {
        if (model == null) {
            return null;
        }
        return new IRol(model.getIdRol(), model.getNombre());
    }
}
