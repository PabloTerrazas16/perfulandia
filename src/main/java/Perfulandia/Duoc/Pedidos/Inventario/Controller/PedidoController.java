package Perfulandia.Duoc.Pedidos.Inventario.Controller;

import Perfulandia.Duoc.Pedidos.Inventario.Model.Pedido;
import Perfulandia.Duoc.Pedidos.Inventario.Repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;


    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody Pedido pedido) {
        Pedido nuevoPedido = pedidoRepository.save(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPedido); // 201 Created
    }


    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtenerPedido(@PathVariable Long id) {
        return pedidoRepository.findById(id)
                .map(pedido -> ResponseEntity.ok(pedido)) // 200 OK
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)); // 404 Not Found
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPedido(@PathVariable Long id) {
        if (pedidoRepository.existsById(id)) {
            pedidoRepository.deleteById(id);
            return ResponseEntity.ok("Pedido eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Pedido no encontrado con ID: " + id);
        }
    }

    /*EDITAR PEDIDO FALTA*/




    /*FALTA LISTAR PEDIDOS*/





}
