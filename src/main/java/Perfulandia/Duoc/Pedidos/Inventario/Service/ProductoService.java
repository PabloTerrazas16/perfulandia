package Perfulandia.Duoc.Pedidos.Inventario.Service;

import Perfulandia.Duoc.Pedidos.Inventario.Model.Producto;
import Perfulandia.Duoc.Pedidos.Inventario.Repository.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    public List<Producto> buscarProductoPorNombre(String nombre) {
        return productoRepository.findByNombre(nombre);
    }

    public Producto crearProducto(Producto producto) {
        // Validaciones específicas pueden ir aquí si no se manejan en controlador
        return productoRepository.save(producto);
    }

    public Producto actualizarProducto(Long id, Producto productoActualizado) {
        Producto productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));

        productoExistente.setNombre(productoActualizado.getNombre());
        productoExistente.setStock(productoActualizado.getStock());
        productoExistente.setPrecio(productoActualizado.getPrecio());

        return productoRepository.save(productoExistente);
    }

    public void eliminarProducto(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado con id: " + id);
        }
        productoRepository.deleteById(id);
    }

}

