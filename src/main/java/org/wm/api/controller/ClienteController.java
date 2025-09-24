package org.wm.api.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.wm.api.data.ICliente;
import org.wm.api.service.ClienteService;

@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class ClienteController {
    
    private final ClienteService service;
    @PostMapping
    public ICliente createCliente(@RequestBody ICliente request) {
        return service.crate(request);
    }
    
    @GetMapping
    public List<ICliente> getAllCliente() {
        return service.getAll();
    }
    
    @GetMapping("/{id}")
    public ICliente getClienteById(@PathVariable Long id) {
        return service.getById(id);
    }
    
    @PutMapping("/{id}")
    public ICliente updateCliente(@PathVariable Long id, @RequestBody ICliente request) {
        return service.update(id, request);
    }
    
    @DeleteMapping("/{id}")
    public void deleteCliente(@PathVariable Long id) {
        service.delete(id);
    }
}
