package Perfulandia.Duoc.Pedidos.Inventario.Service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import Perfulandia.Duoc.Pedidos.Inventario.Model.Reporte;
import Perfulandia.Duoc.Pedidos.Inventario.Repository.ReporteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ReporteServiceTest {

    @Autowired
    private ReporteService reporteService;

    @MockBean
    private ReporteRepository reporteRepository;

    @Test
    public void testListarReportes() {
        Reporte reporte = new Reporte();
        reporte.setIdReporte(1L);
        reporte.setNombreReporte("Reporte de Ventas");

        when(reporteRepository.findAll()).thenReturn(List.of(reporte));

        List<Reporte> reportes = reporteService.listarReportes();
        assertNotNull(reportes);
        assertEquals(1, reportes.size());
        assertEquals("Reporte de Ventas", reportes.get(0).getNombreReporte());
    }

    @Test
    public void testObtenerReportePorId() {
        Long id = 1L;
        Reporte reporte = new Reporte();
        reporte.setIdReporte(id);
        when(reporteRepository.findById(id)).thenReturn(Optional.of(reporte));

        Reporte found = reporteService.obtenerReportePorId(id);
        assertNotNull(found);
        assertEquals(id, found.getIdReporte());
    }

    @Test
    public void testGuardarReporte() {
        // Crea un nuevo objeto Reporte y establece los campos obligatorios
        Reporte reporte = new Reporte();
        reporte.setIdReporte(1L);
        reporte.setNombreReporte("Reporte de Ventas"); // Establece el nombre
        reporte.setFechaReporte(new Date()); // Establece la fecha

        // Configura el comportamiento del mock: cuando se llame a save(), devuelve el objeto Reporte proporcionado.
        when(reporteRepository.save(reporte)).thenReturn(reporte);

        // Llama al metodo guardarReporte() del servicio.
        Reporte saved = reporteService.guardarReporte(reporte);

        // Verifica que el Reporte guardado no sea nulo y que su nombre coincida con el nombre esperado.
        assertNotNull(saved);
        assertEquals("Reporte de Ventas", saved.getNombreReporte());
    }


    @Test
    public void testEliminarReporte() {
        Long id = 1L;
        Reporte reporte = new Reporte();
        reporte.setIdReporte(id);
        reporte.setNombreReporte("Reporte de Ventas");
        reporte.setFechaReporte(new Date()); // Asegúrate de establecer la fecha

        // Configura el mock para que devuelva true cuando se llame a existsById
        when(reporteRepository.existsById(id)).thenReturn(true);

        // Configura el mock para que devuelva el reporte cuando se llame a findById
        when(reporteRepository.findById(id)).thenReturn(Optional.of(reporte));

        // Define el comportamiento del mock: cuando se llame a deleteById(), no hace nada.
        doNothing().when(reporteRepository).deleteById(id);

        // Llama al metodo eliminarReporte() del servicio.
        reporteService.eliminarReporte(id);

        // Verifica que el metodo deleteById() del repositorio se haya llamado exactamente una vez.
        verify(reporteRepository, times(1)).deleteById(id);
    }




}
