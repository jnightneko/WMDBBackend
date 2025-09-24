package org.wm.api.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.wm.api.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {

    public Optional<Rol> findByNombre(String nombre);
    
}
