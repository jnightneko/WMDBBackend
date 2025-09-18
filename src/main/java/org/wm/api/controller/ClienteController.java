package org.wm.api.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.wm.api.dto.DTOCliente;
import org.wm.api.service.ClienteService;

@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class ClienteController {
    
    private final ClienteService clienteService;
    
    @GetMapping
    public List<DTOCliente> getAllCliente() {
        return clienteService.getAllCliente();
    }
    
    @GetMapping("/{id}")
    public DTOCliente getCliente(@PathVariable Long id) {
        return clienteService.getClienteById(id);
    }
    
    @PostMapping
    public DTOCliente createCliente(@RequestBody DTOCliente cliente) {
        return clienteService.createCliente(cliente);
    }
    
    @PutMapping("/{id}")
    public DTOCliente updateCliente(@PathVariable Long id, @RequestBody DTOCliente cliente) {
        return clienteService.updateCliente(id, cliente);
    }
    
    @DeleteMapping
    public void deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
    }
}
