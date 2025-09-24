package org.wm.api.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.wm.api.data.IDetallePedido;
import org.wm.api.model.DetallePedido;
import org.wm.api.model.Pedido;
import org.wm.api.model.Producto;
import org.wm.api.repository.DetallePedidoRepository;
import org.wm.api.repository.PedidoRepository;
import org.wm.api.repository.ProductoRepository;

@Service
@RequiredArgsConstructor
public class DetallePedidoService {
    
    private final DetallePedidoRepository repository;
    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;
    
    public IDetallePedido crate(IDetallePedido request) {
        Optional<Pedido> optPedido = pedidoRepository.findById(request.pedido().id());
        Optional<Producto> opProducto = productoRepository.findById(request.producto().id());
        
        if (optPedido.isEmpty()) {
            throw new IllegalArgumentException("El pedido no existe");
        }
        if (opProducto.isEmpty()) {
            throw new IllegalArgumentException("El producto no existe");
        }
        
        DetallePedido model = DetallePedido.builder()
                .cantidad(request.cantidad())
                .pedido(optPedido.get())
                .producto(opProducto.get())
                .build();
        return IDetallePedido.valueOf(
                repository.save(model)
        );
    }
    
    public List<IDetallePedido> getAll() {
        List<DetallePedido> models = repository.findAll();
        return models.stream()
                .map((value) -> IDetallePedido.valueOf(value))
                .toList();
    }
    
    public IDetallePedido getById(Long id) {
        Optional<DetallePedido> model = repository.findById(id);
        if (model.isEmpty()) {
            return null;
        }
        DetallePedido opModel = model.get();
        return IDetallePedido.valueOf(opModel);
    }
    
    public IDetallePedido update(Long id, IDetallePedido request) {
        Optional<DetallePedido> myModel = repository.findById(id);
        if (myModel.isEmpty()) {
            return null;
        }
        
        DetallePedido opModel = myModel.get();
        opModel.setCantidad(request.cantidad());
        
        if (request.pedido() != null) {
            Optional<Pedido> optPedido = pedidoRepository.findById(request.pedido().id());
            if (optPedido.isEmpty()) {
                throw new IllegalArgumentException("El pedido no existe");
            }
            opModel.setPedido(optPedido.get());
        }
        if (request.producto() != null) {
            Optional<Producto> opProducto = productoRepository.findById(request.producto().id());
            if (opProducto.isEmpty()) {
                throw new IllegalArgumentException("El producto no existe");
            }
            opModel.setProducto(opProducto.get());
        }
        
        return IDetallePedido.valueOf(
                repository.save(opModel)
        );
    }
    
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
