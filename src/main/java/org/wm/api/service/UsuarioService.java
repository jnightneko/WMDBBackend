package org.wm.api.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.wm.api.data.IUsuario;
import org.wm.api.model.Rol;
import org.wm.api.model.Usuario;
import org.wm.api.repository.RolRepository;
import org.wm.api.repository.UsuarioRepository;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository repository;
    private final RolRepository rolRepository;
    
    private String encode(String simple) {
        char[] bcryptChars = BCrypt.with(BCrypt.Version.VERSION_2B).hashToChar(6, simple.toCharArray());
        return new String(bcryptChars);
    }
    
    public IUsuario crate(IUsuario request) {
        Optional<Rol> modelRol = rolRepository.findById(request.rol().id());
        if (modelRol.isEmpty()) {
            throw new IllegalArgumentException("Rol inexistente.");
        }
        
        Usuario model = Usuario.builder()
                .nombre(request.nombre())
                .contrasenia(encode(request.contrasenia()))
                .rol(modelRol.get())
                .build();
        return IUsuario.valueOf(
                repository.save(model)
        );
    }
    
    public List<IUsuario> getAll() {
        List<Usuario> models = repository.findAll();
        return models.stream()
                .map((value) -> IUsuario.valueOf(value))
                .toList();
    }
    
    public IUsuario getById(Long id) {
        Optional<Usuario> model = repository.findById(id);
        if (model.isEmpty()) {
            return null;
        }
        Usuario opModel = model.get();
        return IUsuario.valueOf(opModel);
    }
    
    public IUsuario update(Long id, IUsuario request) {
        Optional<Usuario> myModel = repository.findById(id);
        if (myModel.isEmpty()) {
            return null;
        }
        
        Usuario opModel = myModel.get();
        opModel.setNombre(request.nombre());
        
        if (request.contrasenia() != null) {
            opModel.setContrasenia(encode(request.contrasenia()));
        }
        if (request.rol() != null) {
            Optional<Rol> modelRol = rolRepository.findById(request.rol().id());
            if (modelRol.isEmpty()) {
                throw new IllegalArgumentException("Rol inexistente.");
            }
            opModel.setRol(modelRol.get());
        }
        
        return IUsuario.valueOf(
                repository.save(opModel)
        );
    }
    
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
