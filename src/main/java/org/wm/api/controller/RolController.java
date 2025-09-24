package org.wm.api.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.wm.api.data.IRol;
import org.wm.api.service.RolService;

@RestController
@RequestMapping("/rol")
@RequiredArgsConstructor
public class RolController {
    
    private final RolService service;
    @PostMapping
    public IRol createRol(@RequestBody IRol request) {
        return service.crate(request);
    }
    
    @GetMapping
    public List<IRol> getAllRol() {
        return service.getAll();
    }
    
    @GetMapping("/{id}")
    public IRol getRolById(@PathVariable Long id) {
        return service.getById(id);
    }
    
    @PutMapping("/{id}")
    public IRol updateRol(@PathVariable Long id, @RequestBody IRol request) {
        return service.update(id, request);
    }
    
    @DeleteMapping("/{id}")
    public void deleteRol(@PathVariable Long id) {
        service.delete(id);
    }
}
