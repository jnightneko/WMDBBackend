package org.wm.api.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.wm.api.data.IDetallePedido;
import org.wm.api.service.DetallePedidoService;

@RestController
@RequestMapping("/detallepedido")
@RequiredArgsConstructor
public class DetallePedidoController {
    
    private final DetallePedidoService service;
    @PostMapping
    public IDetallePedido createDetallePedido(@RequestBody IDetallePedido request) {
        return service.crate(request);
    }
    
    @GetMapping
    public List<IDetallePedido> getAllDetallePedido() {
        return service.getAll();
    }
    
    @GetMapping("/{id}")
    public IDetallePedido getDetallePedidoById(@PathVariable Long id) {
        return service.getById(id);
    }
    
    @PutMapping("/{id}")
    public IDetallePedido updateDetallePedido(@PathVariable Long id, @RequestBody IDetallePedido request) {
        return service.update(id, request);
    }
    
    @DeleteMapping("/{id}")
    public void deleteDetallePedido(@PathVariable Long id) {
        service.delete(id);
    }
}
