package org.wm.api.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.wm.api.data.IRol;
import org.wm.api.model.Rol;
import org.wm.api.repository.RolRepository;

@Service
@RequiredArgsConstructor
public class RolService {
    
    private final RolRepository repository;
    public IRol crate(IRol request) {
        Optional<Rol> findModel = repository.findByNombre(request.nombre());
        if (!findModel.isEmpty()) {
            return null;
        }
        
        Rol model = Rol.builder()
                .nombre(request.nombre())
                .build();
        return IRol.valueOf(
                repository.save(model)
        );
    }
    
    public List<IRol> getAll() {
        List<Rol> models = repository.findAll();
        return models.stream()
                .map((value) -> IRol.valueOf(value))
                .toList();
    }
    
    public IRol getById(Long id) {
        Optional<Rol> model = repository.findById(id);
        if (model.isEmpty()) {
            return null;
        }
        Rol opModel = model.get();
        return IRol.valueOf(opModel);
    }
    
    public IRol update(Long id, IRol request) {
        Optional<Rol> myModel = repository.findById(id);
        if (myModel.isEmpty()) {
            return null;
        }
        
        Rol opModel = myModel.get();
        opModel.setNombre(request.nombre());
        
        return IRol.valueOf(
                repository.save(opModel)
        );
    }
    
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
