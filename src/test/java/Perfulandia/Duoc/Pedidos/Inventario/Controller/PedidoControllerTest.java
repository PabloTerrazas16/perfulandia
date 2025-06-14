package Perfulandia.Duoc.Pedidos.Inventario.Controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import Perfulandia.Duoc.Pedidos.Inventario.Model.Pedido;
import Perfulandia.Duoc.Pedidos.Inventario.Service.PedidoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

@WebMvcTest(PedidoController.class)
public class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoService pedidoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Pedido pedido;

    @BeforeEach
    void setUp() {
        pedido = new Pedido();
        pedido.setId(1L);
        pedido.setCliente("Juan Pérez");
        pedido.setFecha(LocalDate.of(2025, 1, 8));
    }

    @Test
    public void testGetAllPedidos() throws Exception {
        when(pedidoService.listarPedidos()).thenReturn(List.of(pedido));

        mockMvc.perform(get("/api/pedido"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].cliente").value("Juan Pérez"));
    }

    @Test
    public void testGetPedidoById() throws Exception {
        when(pedidoService.obtenerPedido(1L)).thenReturn(pedido);

        mockMvc.perform(get("/api/pedido/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.cliente").value("Juan Pérez"));
    }

    @Test
    public void testCreatePedido() throws Exception {
        when(pedidoService.crearPedido(any(Pedido.class))).thenReturn(pedido);

        mockMvc.perform(post("/api/pedido")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pedido)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.cliente").value("Juan Pérez"));
    }

    @Test
    public void testUpdatePedido() throws Exception {
        when(pedidoService.editarPedido(anyLong(), any(Pedido.class))).thenReturn(pedido);

        mockMvc.perform(put("/api/pedido/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pedido)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.cliente").value("Juan Pérez"));
    }

    @Test
    public void testDeletePedido() throws Exception {
        doNothing().when(pedidoService).eliminarPedido(1L);

        mockMvc.perform(delete("/api/pedido/1"))
                .andExpect(status().isOk());

        verify(pedidoService, times(1)).eliminarPedido(1L);
    }
}
