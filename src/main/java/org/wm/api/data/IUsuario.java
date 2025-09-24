package org.wm.api.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.wm.api.model.Usuario;

public record IUsuario(
    Long id,
    @JsonInclude(
        JsonInclude.Include.NON_NULL
    )
    String nombre,
    @JsonInclude(
        JsonInclude.Include.NON_NULL
    )
    String contrasenia,
    @JsonInclude(
        JsonInclude.Include.NON_NULL
    )
    IRol rol
) {
    public static IUsuario valueOf(Usuario model) {
        if (model == null) {
            return null;
        }        
        return new IUsuario(
                model.getIdUsuario(),
                model.getNombre(),
                null,
                IRol.valueOf(model.getRol())
        );
    }
}
