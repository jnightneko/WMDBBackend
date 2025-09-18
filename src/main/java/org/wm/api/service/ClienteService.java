package org.wm.api.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.wm.api.dto.DTOCliente;
import org.wm.api.model.Cliente;
import org.wm.api.repo.ClienteRepository;

@Service
@RequiredArgsConstructor
public class ClienteService {
    
    private final ClienteRepository repository;
    
    public List<DTOCliente> getAllCliente() {
        List<Cliente> clientes = repository.findAll();
        return clientes.stream()
                .map((value) -> DTOCliente.valueOf(value))
                .toList();
    }
    
    public DTOCliente getClienteById(Long id) {
        Cliente cliente = repository.findById(id)
                                    .orElseThrow();
        return DTOCliente.valueOf(cliente);
    }
    
    public DTOCliente createCliente(DTOCliente request) {
        Cliente cliente = Cliente.builder()
                .nombre(request.nombre())
                .build();
        return DTOCliente.valueOf(
            repository.save(cliente)
        );
    }
    
    public DTOCliente updateCliente(Long id, DTOCliente request) {
        Cliente cliente = repository.findById(id)
                                    .orElseThrow();
        cliente.setNombre(request.nombre());
       return DTOCliente.valueOf(
            repository.save(cliente)
       );
    }
    
    public void deleteCliente(Long id) {
        repository.deleteById(id);
    }
}
