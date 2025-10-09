package org.wm.api.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.wm.api.data.IClientePedido;
import org.wm.api.data.IReporte;
import org.wm.api.service.ClientePedidoService;


@RestController
@RequestMapping("/clientepedido")
@RequiredArgsConstructor
public class ClientePedidoController {
    
    private final ClientePedidoService service;
    
    @PostMapping
    public IClientePedido comprar(
            @RequestPart("pedido") IClientePedido request,
            @RequestPart("foto") MultipartFile img
    ) {
        return service.create(request, img);
    }
    
    @GetMapping
    public List<IReporte> reportes() {
        return service.getAllReporte();
    }
}
