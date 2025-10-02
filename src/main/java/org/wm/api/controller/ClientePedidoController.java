package org.wm.api.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.wm.api.data.IClientePedido;
import org.wm.api.data.IReporte;
import org.wm.api.service.ClientePedidoService;


@RestController
@RequestMapping("/clientepedido")
@RequiredArgsConstructor
public class ClientePedidoController {
    
    private final ClientePedidoService service;
    
    @PostMapping
    public IClientePedido comprar(@RequestBody IClientePedido request) {
        return service.create(request);
    }
    
    @GetMapping
    public List<IReporte> reportes() {
        return service.getAllReporte();
    }
}
