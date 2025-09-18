package org.wm.api.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.wm.api.dto.DTOProducto;
import org.wm.api.model.Producto;
import org.wm.api.repo.ProductoRepository;

@Service
@RequiredArgsConstructor
public class ProductoService {
    
    private final ProductoRepository repository;
    
    public List<DTOProducto> getAllProducto() {
        List<Producto> clientes = repository.findAll();
        return clientes.stream()
                .map((value) -> DTOProducto.valueOf(value))
                .toList();
    }
    
    public DTOProducto getProductoById(Long id) {
        Producto producto = repository.findById(id)
                                    .orElseThrow();
        return DTOProducto.valueOf(producto);
    }
    
    public DTOProducto createProducto(DTOProducto request) {
        Producto producto = Producto.builder()
                .nombre(request.nombre())
                .cantidad(request.cantidad())
                .precio(request.precio())
                .build();
        return DTOProducto.valueOf(
            repository.save(producto)
        );
    }
    
    public DTOProducto updateProducto(Long id, DTOProducto request) {
        Producto producto = repository.findById(id)
                                    .orElseThrow();
        producto.setNombre(request.nombre());
        producto.setCantidad(request.cantidad());
        producto.setPrecio(request.precio());
        return DTOProducto.valueOf(
                repository.save(producto)
        );
    }
    
    public void deleteProducto(Long id) {
        repository.deleteById(id);
    }
}
