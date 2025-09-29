package org.wm.api.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.wm.api.data.IProducto;
import org.wm.api.service.ProductoService;

@RestController
@RequestMapping("/producto")
@RequiredArgsConstructor
public class ProductoController {
    
    private final ProductoService service;
    @PostMapping()
    public IProducto createProducto(
            @RequestPart("producto")  IProducto request,
            @RequestPart("imagen") MultipartFile img) {
        return service.crate(request, img);
    }
    
    @GetMapping
    public List<IProducto> getAllProducto() {
        return service.getAll();
    }
    
    @GetMapping("/{id}")
    public IProducto getProductoById(@PathVariable Long id) {
        return service.getById(id);
    }
    
    @PutMapping("/{id}")
    public IProducto updateProducto(@PathVariable Long id, @RequestBody IProducto request) {
        return service.update(id, request);
    }
    
    @DeleteMapping("/{id}")
    public void deleteProducto(@PathVariable Long id) {
        service.delete(id);
    }
}
