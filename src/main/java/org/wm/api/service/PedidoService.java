package org.wm.api.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.wm.api.data.IPedido;
import org.wm.api.model.Cliente;
import org.wm.api.model.Pedido;
import org.wm.api.repository.ClienteRepository;
import org.wm.api.repository.PedidoRepository;

@Service
@RequiredArgsConstructor
public class PedidoService {
    
    private final PedidoRepository repository;
    private final ClienteRepository clienteRepository;
    
    public IPedido crate(IPedido request) {
        Optional<Cliente> opCliente = clienteRepository.findById(request.cliente().id());
        if (opCliente.isEmpty()) {
            throw new IllegalArgumentException("El cliente no existe");
        }
        
        Pedido model = Pedido.builder()
                .cliente(opCliente.get())
                .total(request.total())
                .ubicacion(request.ubicacion())
                .build();
        return IPedido.valueOf(
                repository.save(model)
        );
    }
    
    public List<IPedido> getAll() {
        List<Pedido> models = repository.findAll();
        return models.stream()
                .map((value) -> IPedido.valueOf(value))
                .toList();
    }
    
    public IPedido getById(Long id) {
        Optional<Pedido> model = repository.findById(id);
        if (model.isEmpty()) {
            return null;
        }
        Pedido opModel = model.get();
        return IPedido.valueOf(opModel);
    }
    
    public IPedido update(Long id, IPedido request) {
        Optional<Pedido> myModel = repository.findById(id);
        if (myModel.isEmpty()) {
            return null;
        }
        
        Pedido opModel = myModel.get();
        opModel.setTotal(request.total());
        opModel.setUbicacion(request.ubicacion());
        
        if (request.cliente() != null) {
            Optional<Cliente> opCliente = clienteRepository.findById(request.cliente().id());
            if (opCliente.isEmpty()) {
                throw new IllegalArgumentException("El cliente no existe");
            }
            opModel.setCliente(opCliente.get());
        }
        
        return IPedido.valueOf(
                repository.save(opModel)
        );
    }
    
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
