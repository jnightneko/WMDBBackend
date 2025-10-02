package org.wm.api.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.wm.api.model.DetallePedido;
import org.wm.api.model.Pedido;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Long> {

    public List<DetallePedido> findAllByPedido(Pedido pedido);
    
}
