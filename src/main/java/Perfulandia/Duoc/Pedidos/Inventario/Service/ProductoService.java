package Perfulandia.Duoc.Pedidos.Inventario.Service;

import Perfulandia.Duoc.Pedidos.Inventario.Model.Pedido;
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
    public long contarProductos() {
        return productoRepository.count();
    }

    public Producto actualizarStock(Long id, Integer cantidad) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        producto.setStock(producto.getStock() + cantidad);
        return productoRepository.save(producto);
    }
    public void eliminarProducto(Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Producto no encontrado con ID: " + id);
        }
    }


    public List<Producto> buscarProductoPorNombre(String nombre) {

        return productoRepository.findByNombre(nombre);
    }



}
