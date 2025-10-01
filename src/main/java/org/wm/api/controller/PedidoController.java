package org.wm.api.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.wm.api.data.IPedido;
import org.wm.api.service.PedidoService;

@RestController
@RequestMapping("/pedido")
@RequiredArgsConstructor
public class PedidoController {
    
    private final PedidoService service;
    @PostMapping
    public IPedido createPedido(@RequestBody IPedido request) {
        return service.crate(request);
    }
    
    @GetMapping
    public List<IPedido> getAllPedido() {
        return service.getAll();
    }
    
    @GetMapping("/cliente/{id}")
    public List<IPedido> getAllPedidoCliente(@PathVariable Long id) {
        return service.getAllByIdCliente(id);
    }
    
    @GetMapping("/{id}")
    public IPedido getPedidoById(@PathVariable Long id) {
        return service.getById(id);
    }
    
    @PutMapping("/{id}")
    public IPedido updatePedido(@PathVariable Long id, @RequestBody IPedido request) {
        return service.update(id, request);
    }
    
    @DeleteMapping("/{id}")
    public void deletePedido(@PathVariable Long id) {
        service.delete(id);
    }
}
