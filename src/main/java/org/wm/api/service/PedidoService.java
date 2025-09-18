package org.wm.api.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.wm.api.dto.DTOPedido;
import org.wm.api.model.Cliente;
import org.wm.api.model.Pedido;
import org.wm.api.model.Producto;
import org.wm.api.repo.ClienteRepository;
import org.wm.api.repo.PedidoRepository;
import org.wm.api.repo.ProductoRepository;

@Service
@RequiredArgsConstructor
public class PedidoService {
    
    private final PedidoRepository repository;
    private final ClienteRepository clienteRepository;
    private final ProductoRepository productoRepository;
    
    public List<DTOPedido> getAllPedido() {
        List<Pedido> pedido = repository.findAll();
        return pedido.stream()
                .map((value) -> DTOPedido.valueOf(value))
                .toList();
    }
    
    public DTOPedido getPedidoById(Long id) {
        Pedido pedido = repository.findById(id)
                                    .orElseThrow();
        return DTOPedido.valueOf(pedido);
    }
    
    public DTOPedido createPedido(DTOPedido request) {
        Cliente cliente = clienteRepository.findById(request.cliente().id())
                .orElseThrow();
        
        Pedido pedido = Pedido.builder()
                .cliente(cliente)
                .total(request.total())
                .productos(request.productos().stream().map((value) -> {
                    Producto producto = productoRepository.findById(value.id())
                            .orElseThrow();                    
                    return producto;
                }).toList())
                .build();
        return DTOPedido.valueOf(
            repository.save(pedido)
       );
    }
    
    public DTOPedido updatePedido(Long id, DTOPedido request) {
        Pedido pedido = repository.findById(id)
                .orElseThrow();        
       return DTOPedido.valueOf(
            repository.save(pedido)
       );
    }
    
    public void deletePedido(Long id) {
        repository.deleteById(id);
    }
}
