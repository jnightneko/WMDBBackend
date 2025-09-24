package org.wm.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wm.api.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
}
