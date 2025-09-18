package org.wm.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wm.api.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    
}
