package org.wm.api.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.wm.api.data.ICliente;
import org.wm.api.model.Cliente;
import org.wm.api.model.Usuario;
import org.wm.api.repository.ClienteRepository;
import org.wm.api.repository.UsuarioRepository;

@Service
@RequiredArgsConstructor
public class ClienteService {
    
    private final ClienteRepository repository;
    private final UsuarioRepository usuarioRepository;
    
    public ICliente crate(ICliente request) {
        Optional<Usuario> opUsuario = usuarioRepository.findById(request.usuario().id());
        Usuario usuario = opUsuario.isEmpty() ? null : opUsuario.get();
        
        Cliente model = Cliente.builder()
                .nombre(request.nombre())
                .usuario(usuario)
                .build();
        return ICliente.valueOf(
                repository.save(model)
        );
    }
    
    public List<ICliente> getAll() {
        List<Cliente> models = repository.findAll();
        return models.stream()
                .map((value) -> ICliente.valueOf(value))
                .toList();
    }
    
    public ICliente getById(Long id) {
        Optional<Cliente> model = repository.findById(id);
        if (model.isEmpty()) {
            return null;
        }
        Cliente opModel = model.get();
        return ICliente.valueOf(opModel);
    }
    
    public ICliente update(Long id, ICliente request) {
        Optional<Cliente> myModel = repository.findById(id);
        if (myModel.isEmpty()) {
            return null;
        }
        
        Cliente opModel = myModel.get();
        opModel.setNombre(request.nombre());
        
        if (request.usuario() != null) {
            Optional<Usuario> opUsuario = usuarioRepository.findById(request.usuario().id());
            Usuario usuario = opUsuario.isEmpty() ? null : opUsuario.get();
            opModel.setUsuario(usuario);
        }
        return ICliente.valueOf(
                repository.save(opModel)
        );
    }
    
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
