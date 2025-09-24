package org.wm.api.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.wm.api.data.IProducto;
import org.wm.api.model.Producto;
import org.wm.api.repository.ProductoRepository;

@Service
@RequiredArgsConstructor
public class ProductoService {
    
    private final ProductoRepository repository;
    public IProducto crate(IProducto request) {        
        Producto model = Producto.builder()
                .nombre(request.nombre())
                .cantidad(request.cantidad())
                .precio(request.precio())
                .build();
        return IProducto.valueOf(
                repository.save(model)
        );
    }
    
    public List<IProducto> getAll() {
        List<Producto> models = repository.findAll();
        return models.stream()
                .map((value) -> IProducto.valueOf(value))
                .toList();
    }
    
    public IProducto getById(Long id) {
        Optional<Producto> model = repository.findById(id);
        if (model.isEmpty()) {
            return null;
        }
        Producto opModel = model.get();
        return IProducto.valueOf(opModel);
    }
    
    public IProducto update(Long id, IProducto request) {
        Optional<Producto> myModel = repository.findById(id);
        if (myModel.isEmpty()) {
            return null;
        }
        
        Producto opModel = myModel.get();
        opModel.setNombre(request.nombre());
        opModel.setCantidad(request.cantidad());
        opModel.setPrecio(request.precio());
        
        return IProducto.valueOf(
                repository.save(opModel)
        );
    }
    
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
