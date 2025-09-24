package org.wm.api.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.wm.api.model.Cliente;
import org.wm.api.model.Usuario;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByUsuario(Usuario usuario);
}
