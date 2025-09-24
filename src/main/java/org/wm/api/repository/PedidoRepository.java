package org.wm.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wm.api.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    
}
