package Perfulandia.Duoc.Pedidos.Inventario.Controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import Perfulandia.Duoc.Pedidos.Inventario.Model.Reporte;
import Perfulandia.Duoc.Pedidos.Inventario.Service.ReporteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;

@WebMvcTest(ReporteController.class)
public class ReporteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReporteService reporteService;

    @Autowired
    private ObjectMapper objectMapper;

    private Reporte reporte;

    @BeforeEach
    void setUp() {
        reporte = new Reporte();
        reporte.setIdReporte(1L);
        reporte.setNombreReporte("Reporte de Ventas");
        reporte.setDescripcionReporte("Descripción del reporte de ventas");
        reporte.setFechaReporte(new Date());
    }

    @Test
    public void testGetAllReportes() throws Exception {
        when(reporteService.listarReportes()).thenReturn(List.of(reporte));

        mockMvc.perform(get("/api/reportes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idReporte").value(1))
                .andExpect(jsonPath("$[0].nombreReporte").value("Reporte de Ventas"));
    }

    @Test
    public void testGetReporteById() throws Exception {
        when(reporteService.obtenerReportePorId(1L)).thenReturn(reporte);

        mockMvc.perform(get("/api/reportes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idReporte").value(1))
                .andExpect(jsonPath("$.nombreReporte").value("Reporte de Ventas"));
    }

    @Test
    public void testCreateReporte() throws Exception {
        when(reporteService.guardarReporte(any(Reporte.class))).thenReturn(reporte);

        mockMvc.perform(post("/api/reportes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reporte)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idReporte").value(1))
                .andExpect(jsonPath("$.nombreReporte").value("Reporte de Ventas"));
    }

    @Test
    public void testUpdateReporte() throws Exception {
        when(reporteService.actualizarReporte(anyLong(), anyString(), anyString())).thenReturn(reporte);

        mockMvc.perform(put("/api/reportes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reporte)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idReporte").value(1))
                .andExpect(jsonPath("$.nombreReporte").value("Reporte de Ventas"));
    }

    @Test
    public void testDeleteReporte() throws Exception {
        doNothing().when(reporteService).eliminarReporte(1L);

        mockMvc.perform(delete("/api/reportes/1"))
                .andExpect(status().isNoContent());

        verify(reporteService, times(1)).eliminarReporte(1L);
    }
}
