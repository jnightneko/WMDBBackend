package org.wm.api.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.wm.api.data.IUsuario;
import org.wm.api.model.Usuario;
import org.wm.api.repository.UsuarioRepository;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final UsuarioRepository repository;
    
    public IUsuario login(IUsuario request) {
        List<Usuario> models = repository.findAllByNombre(request.nombre());
        if (models.isEmpty()) {
            return null;
        }
        
        for (final Usuario user : models) {
            String password = request.contrasenia();            
            BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), user.getContrasenia());
            if (result.verified) {
                return IUsuario.valueOf(user);
            }
        }
        return null;
    }
}
