package org.wm.api.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.wm.api.model.Cliente;
import org.wm.api.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    public List<Pedido> findAllByCliente(Cliente cliente);
}
