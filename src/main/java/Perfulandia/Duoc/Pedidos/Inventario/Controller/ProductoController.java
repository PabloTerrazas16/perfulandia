package Perfulandia.Duoc.Pedidos.Inventario.Controller;

import Perfulandia.Duoc.Pedidos.Inventario.Model.Pedido;
import Perfulandia.Duoc.Pedidos.Inventario.Model.Producto;
import Perfulandia.Duoc.Pedidos.Inventario.Repository.ProductoRepository;
import Perfulandia.Duoc.Pedidos.Inventario.Service.ProductoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/Productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProductoService productoService;

    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        // Validamos que el producto tenga nombre y stock
        if (producto.getNombre() == null || producto.getStock() == null) {
            return ResponseEntity.badRequest().build(); // 400 Bad Request
        }
        Producto nuevoProducto = productoRepository.save(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto); // 201 Created
    }
    //obtener lista de los productos
   @GetMapping
    public List<Producto> ListarProductos() {
       return productoRepository.findAll();
   }

   //Buscar un producto por nombre
   @GetMapping("/buscar/{nombre}")
   public ResponseEntity<List<Producto>> buscarProducto(@PathVariable String nombre) {
       List<Producto> productos = productoRepository.findByNombre(nombre);
       if (productos.isEmpty()) {
           return ResponseEntity.noContent().build(); // 204 No Content si no hay resultados
       }
       return ResponseEntity.ok(productos);
   }



    //Actualizar  productos
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto productoActualizado) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe el producto con id " + id));

        producto.setNombre(productoActualizado.getNombre());
        producto.setStock(productoActualizado.getStock());
        producto.setPrecio(productoActualizado.getPrecio());

        Producto productoGuardado = productoRepository.save(producto);
        return ResponseEntity.ok(productoGuardado);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.ok("Producto eliminado correctamente");
    }

    /*FALTA EDITAR PRODUCTO*/







}
