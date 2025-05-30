package Perfulandia.Duoc.Pedidos.Inventario.Controller;

import Perfulandia.Duoc.Pedidos.Inventario.Model.Producto;
import Perfulandia.Duoc.Pedidos.Inventario.Repository.PedidoRepository;
import Perfulandia.Duoc.Pedidos.Inventario.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reportes")
public class ReporteController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("/totalUsuarios")
    public ResponseEntity<Long> contarUsuarios() {
        // Implementa la lógica para contar usuarios si tienes un repositorio de usuarios
        // Por ejemplo: return ResponseEntity.ok(usuarioRepository.count());
        return ResponseEntity.ok(0L); // Placeholder
    }

    @GetMapping("/totalPedidos")
    public ResponseEntity<Long> contarPedidos() {
        long totalPedidos = pedidoRepository.count();
        return ResponseEntity.ok(totalPedidos);
    }

    @GetMapping("/totalProductos")
    public ResponseEntity<Long> contarProductos() {
        long totalProductos = productoRepository.count();
        return ResponseEntity.ok(totalProductos);
    }

    @GetMapping("/ultimoProducto")
    public ResponseEntity<Producto> obtenerUltimoProducto() {
        List<Producto> productos = productoRepository.findAll();
        if (productos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Producto ultimoProducto = productos.get(productos.size() - 1); // Asumiendo que la lista está ordenada
        return ResponseEntity.ok(ultimoProducto);
    }
}
