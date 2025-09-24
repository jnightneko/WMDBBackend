package org.wm.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wm.api.model.DetallePedido;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Long> {
    
}
