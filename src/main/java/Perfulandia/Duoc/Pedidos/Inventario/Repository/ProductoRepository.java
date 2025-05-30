package Perfulandia.Duoc.Pedidos.Inventario.Repository;

import Perfulandia.Duoc.Pedidos.Inventario.Model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByNombre(String nombre);
}
