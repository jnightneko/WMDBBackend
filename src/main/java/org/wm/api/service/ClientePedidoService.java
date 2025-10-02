package org.wm.api.service;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.wm.api.data.ICliente;
import org.wm.api.data.IClientePProducto;
import org.wm.api.data.IClientePedido;
import org.wm.api.data.IDetallePedido;
import org.wm.api.data.IReporte;
import org.wm.api.data.IUsuario;
import org.wm.api.model.Cliente;
import org.wm.api.model.DetallePedido;
import org.wm.api.model.Pedido;
import org.wm.api.model.Producto;
import org.wm.api.model.Usuario;
import org.wm.api.repository.ClienteRepository;
import org.wm.api.repository.DetallePedidoRepository;
import org.wm.api.repository.PedidoRepository;
import org.wm.api.repository.ProductoRepository;
import org.wm.api.repository.UsuarioRepository;

@Service
@RequiredArgsConstructor
public class ClientePedidoService {
        
    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;
    private final DetallePedidoRepository detallePedidoRepository;
    
    public List<IReporte> getAllReporte() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<IReporte> reportes = new ArrayList<>();
        
        for (Usuario user : usuarios) {
            Cliente cliente = clienteRepository.findByUsuario(user)
                    .orElse(null);
            
            if (cliente == null)
                continue;
            
            List<IDetallePedido> detallePedidosModel = new ArrayList<>();
            int productos = 0;
            
            List<Pedido> pedidos = pedidoRepository.findAllByCliente(cliente);
            for (Pedido tran : pedidos) {
                List<DetallePedido> detalle = detallePedidoRepository.findAllByPedido(tran);
                detallePedidosModel.addAll(detalle.stream()
                        .map((value) -> IDetallePedido.valueOf(value))
                        .toList());
                productos += detalle.size();
            }
            
            reportes.add(new IReporte(
                    ICliente.valueOf(cliente),
                    detallePedidosModel,
                    pedidos.size(),
                    productos));
        }
        
        return reportes;
    }
    
    @Transactional
    public IClientePedido create(IClientePedido request) {
        IUsuario usuario = request.usuario();
        
        Optional<Cliente> opCliente = clienteRepository.findByUsuario(
                usuarioRepository.findById(usuario
                        .id())
                        .orElseThrow()
        );
        if (opCliente == null || opCliente.isEmpty()) {
            throw new IllegalArgumentException("Usuario|Cliente inexistente");
        }
        
        Pedido pedido = Pedido.builder()
                .cliente(opCliente.get())
                .total(0.0f)
                .ubicacion(request.ubicacion())
                .build();
        pedido = pedidoRepository.save(pedido);
        
        List<IClientePProducto> productos = request.productos();
        float total = 0.0f;
        
        for (IClientePProducto element : productos) {
            Optional<Producto> opProducto = productoRepository.findById(element.id());
            if (opProducto.isEmpty()) {
                throw new IllegalArgumentException("Producto inexistente");
            }
            
            Producto mProducto = opProducto.get();
            int cantidadActual = mProducto.getCantidad();
            float precio = mProducto.getPrecio();
            
            int diffCanidad = cantidadActual - element.cantidad();
            if (diffCanidad < 0) {
                throw new IllegalArgumentException("Cantidad insuficiente de productos: " + mProducto.getNombre());
            }
            
            mProducto.setCantidad(diffCanidad);
            productoRepository.save(mProducto);
            
            total += (precio * element.cantidad());
            
            DetallePedido detallePedido = DetallePedido.builder()
                    .cantidad(element.cantidad())
                    .pedido(pedido)
                    .producto(mProducto)
                    .createdAt(new Date())
                    .updatedAt(new Date())
                    .build();
            detallePedidoRepository.save(detallePedido);
        }
        
        pedido.setTotal(total);
        pedidoRepository.save(pedido);
        
        return request;
    }
}
