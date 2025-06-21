package Perfulandia.Duoc.Pedidos.Inventario.Controller;

import Perfulandia.Duoc.Pedidos.Inventario.Model.Pedido;
import Perfulandia.Duoc.Pedidos.Inventario.Service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/pedido")
@Tag(name = "Pedido", description = "Operaciones relacionadas con los pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    @Operation(summary = "Obtener todos los pedidos", description = "Obtiene una lista de todos los pedidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })
    public List<Pedido> getAllPedidos() {
        return pedidoService.listarPedidos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener todos los pedidos por ID", description = "Obtiene un solo pedido por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })
    public ResponseEntity<Pedido> getPedidoById(@PathVariable Long id) {
        Pedido pedido = pedidoService.obtenerPedido(id);
        return ResponseEntity.ok(pedido);
    }


    @PostMapping
    @Operation(summary = "Ingresar pedidos", description = "Ingresa un pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Operación exitosa",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Pedido.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
    })
    public ResponseEntity<Pedido> createPedido(@RequestBody Pedido pedido) {
        try {
            Pedido createdPedido = pedidoService.crearPedido(pedido); // Intenta crear el pedido
            return ResponseEntity.status(201).body(createdPedido); // Retorna 201 si la creación fue exitosa
        } catch (IllegalArgumentException e) { // Captura la excepción si hay un problema con la solicitud
            return ResponseEntity.badRequest().build(); // Retorna 400 si la solicitud es incorrecta
        }
    }


    @PutMapping("/{id}")
    @Operation(summary = "Actualizar pedidos por ID", description = "Actualiza un solo pedido por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Pedido.class))),
            @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })
    public ResponseEntity<Pedido> updatePedido(@PathVariable Long id, @RequestBody Pedido pedido) {
        Pedido updatedPedido = pedidoService.editarPedido(id, pedido); // Intenta actualizar el pedido
        return ResponseEntity.ok(updatedPedido); // Retorna 200 si la actualización fue exitosa
    }



    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar pedidos por ID", description = "Elimina un solo pedido por ID")
    @ApiResponse(responseCode = "200", description = "Operación exitosa",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Pedido.class)))
    @ApiResponse(responseCode = "404", description = "Pedido no encontrado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = String.class))) // Mensaje de error simple
    public ResponseEntity<String> deletePedido(@PathVariable Long id) {
        try {
            pedidoService.eliminarPedido(id); // Intenta eliminar el pedido
            return ResponseEntity.ok().build(); // Retorna 200 si la eliminación fue exitosa
        } catch (NoSuchElementException e) { // Captura la excepción si el pedido no existe
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"message\": \"Pedido no encontrado\"}"); // Mensaje de error en formato JSON
        }
    }

}
