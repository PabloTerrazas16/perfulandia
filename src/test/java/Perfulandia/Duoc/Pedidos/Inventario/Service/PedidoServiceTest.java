package Perfulandia.Duoc.Pedidos.Inventario.Service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import Perfulandia.Duoc.Pedidos.Inventario.Model.Pedido;
import Perfulandia.Duoc.Pedidos.Inventario.Repository.PedidoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class PedidoServiceTest {

    @Autowired
    private PedidoService pedidoService;

    @MockBean
    private PedidoRepository pedidoRepository;

    @Test
    public void testListarPedidos() {
        Pedido pedido = new Pedido();
        pedido.setId(1L);
        pedido.setCliente("Juan Pérez");

        when(pedidoRepository.findAll()).thenReturn(List.of(pedido));

        List<Pedido> pedidos = pedidoService.listarPedidos();
        assertNotNull(pedidos);
        assertEquals(1, pedidos.size());
        assertEquals("Juan Pérez", pedidos.get(0).getCliente());
    }

    @Test
    public void testObtenerPedido() {
        Long id = 1L;
        Pedido pedido = new Pedido();
        pedido.setId(id);
        when(pedidoRepository.findById(id)).thenReturn(Optional.of(pedido));

        Pedido found = pedidoService.obtenerPedido(id);
        assertNotNull(found);
        assertEquals(id, found.getId());
    }

    @Test
    public void testCrearPedido() {
        Pedido pedido = new Pedido();
        pedido.setId(1L);
        when(pedidoRepository.save(pedido)).thenReturn(pedido);

        Pedido saved = pedidoService.crearPedido(pedido);
        assertNotNull(saved);
        assertEquals(1L, saved.getId());
    }

    public void eliminarPedido(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new RuntimeException("Pedido no encontrado con ID: " + id);
        }
        pedidoRepository.deleteById(id);
    }

}
