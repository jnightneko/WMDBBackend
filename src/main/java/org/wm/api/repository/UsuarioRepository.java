package org.wm.api.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.wm.api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    List<Usuario> findAllByNombre(String nombre);
}
