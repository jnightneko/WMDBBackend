package org.wm.api.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.wm.api.data.IUsuario;
import org.wm.api.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {
    
    private final UsuarioService service;
    @PostMapping
    public IUsuario createUsuario(@RequestBody IUsuario request) {
        return service.crate(request);
    }
    
    @GetMapping
    public List<IUsuario> getAllUsuario() {
        return service.getAll();
    }
    
    @GetMapping("/{id}")
    public IUsuario getUsuarioById(@PathVariable Long id) {
        return service.getById(id);
    }
    
    @PutMapping("/{id}")
    public IUsuario updateUsuario(@PathVariable Long id, @RequestBody IUsuario request) {
        return service.update(id, request);
    }
    
    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Long id) {
        service.delete(id);
    }
}
