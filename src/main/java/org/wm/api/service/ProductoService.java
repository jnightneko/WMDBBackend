package org.wm.api.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.wm.api.data.IProducto;
import org.wm.api.model.Producto;
import org.wm.api.repository.ProductoRepository;

@Service
@RequiredArgsConstructor
public class ProductoService {
    
    private final ProductoRepository repository;
    
    private String uploadImg(MultipartFile img) {
        String fileName  = System.currentTimeMillis() + "_" + img.getOriginalFilename();
        String uploadDir = System.getProperty("user.dir") + "/app/uploads/";
        Path uploadPath  = Paths.get(uploadDir);

        try {
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path filePath = uploadPath.resolve(fileName);
            Files.copy(img.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Failed to store image: " + e);
        }
        return fileName;
    }
    
    public IProducto crate(IProducto request, MultipartFile img) {
        String fileName = uploadImg(img);
        Producto model = Producto.builder()
                .nombre(request.nombre())
                .cantidad(request.cantidad())
                .precio(request.precio())
                .imagen("/uploads/" + fileName)
                .build();
        return IProducto.valueOf(
                repository.save(model)
        );
    }
    
    public List<IProducto> getAll() {
        List<Producto> models = repository.findAll();
        return models.stream()
                .map((value) -> IProducto.valueOf(value))
                .toList();
    }
    
    public IProducto getById(Long id) {
        Optional<Producto> model = repository.findById(id);
        if (model.isEmpty()) {
            return null;
        }
        Producto opModel = model.get();
        return IProducto.valueOf(opModel);
    }
    
    public IProducto update(Long id, IProducto request, MultipartFile img) {
        Optional<Producto> myModel = repository.findById(id);
        if (myModel.isEmpty()) {
            return null;
        }
        
        Producto opModel = myModel.get();
        opModel.setNombre(request.nombre());
        opModel.setCantidad(request.cantidad());
        opModel.setPrecio(request.precio());

        if (img != null) {
            String fileName  = opModel.getImagen();
            String uploadDir = System.getProperty("user.dir") + "/app/" + fileName;
            
            File file = new File(uploadDir);
            if (file.exists()) {
                try {
                    Files.copy(img.getInputStream(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    throw new RuntimeException("Failed to store image: " + e);
                }
            } else {
                fileName = "/uploads/" + uploadImg(img);
            }
            
            opModel.setImagen(fileName);
        }

        return IProducto.valueOf(
                repository.save(opModel)
        );
    }
    
    public void delete(Long id) {
        Optional<Producto> model = repository.findById(id);
        if (model.isEmpty()) {
            return;
        }
        
        Producto opModel = model.get();
        if (opModel.getImagen() != null) {
            String fileName  = opModel.getImagen();
            String uploadDir = System.getProperty("user.dir") + "/app/" + fileName;
            
            File file = new File(uploadDir);
            if (file.exists()) {
                file.delete();
            }
        }
        repository.deleteById(id);
    }
}
