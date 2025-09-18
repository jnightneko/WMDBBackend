package org.wm.api.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.wm.api.dto.DTOPedido;
import org.wm.api.service.PedidoService;

@RestController
@RequestMapping("/pedido")
@RequiredArgsConstructor
public class PedidoController {
    
    private final PedidoService pedidoService;
    
    @GetMapping
    public List<DTOPedido> getAllPedido() {
        return pedidoService.getAllPedido();
    }
    
    @GetMapping("/{id}")
    public DTOPedido getPedido(@PathVariable Long id) {
        return pedidoService.getPedidoById(id);
    }
    
    @PostMapping
    public DTOPedido createPedido(@RequestBody DTOPedido cliente) {
        return pedidoService.createPedido(cliente);
    }
    
    @PutMapping("/{id}")
    public DTOPedido updatePedido(@PathVariable Long id, @RequestBody DTOPedido cliente) {
        return pedidoService.updatePedido(id, cliente);
    }
    
    @DeleteMapping
    public void deletePedido(@PathVariable Long id) {
        pedidoService.deletePedido(id);
    }
}
