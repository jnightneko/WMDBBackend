package org.wm.api.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.wm.api.dto.DTOProducto;
import org.wm.api.service.ProductoService;

@RestController
@RequestMapping("/producto")
@RequiredArgsConstructor
public class ProductoController {
    
    private final ProductoService productoService;
    
    @GetMapping
    public List<DTOProducto> getAllProducto() {
        return productoService.getAllProducto();
    }
    
    @GetMapping("/{id}")
    public DTOProducto getProducto(@PathVariable Long id) {
        return productoService.getProductoById(id);
    }
    
    @PostMapping
    public DTOProducto createProducto(@RequestBody DTOProducto producto) {
        return productoService.createProducto(producto);
    }
    
    @PutMapping("/{id}")
    public DTOProducto updateProducto(@PathVariable Long id, @RequestBody DTOProducto producto) {
        return productoService.updateProducto(id, producto);
    }
    
    @DeleteMapping
    public void deleteCliente(@PathVariable Long id) {
        productoService.deleteProducto(id);
    }
}
