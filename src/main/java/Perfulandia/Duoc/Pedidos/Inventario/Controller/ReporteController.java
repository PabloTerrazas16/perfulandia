package Perfulandia.Duoc.Pedidos.Inventario.Controller;

import Perfulandia.Duoc.Pedidos.Inventario.Model.Reporte;
import Perfulandia.Duoc.Pedidos.Inventario.Service.ReporteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping
    public ResponseEntity<List<Reporte>> listarReportes() {
        List<Reporte> reportes = reporteService.listarReportes();
        if (reportes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reportes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerReportePorId(@PathVariable Long id) {
        Reporte reporte = reporteService.obtenerReportePorId(id);
        if (reporte == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró reporte con ID " + id);
        }
        return ResponseEntity.ok(reporte);
    }

    @PostMapping
    public ResponseEntity<Reporte> crearReporte(@RequestBody Reporte reporte) {
        Reporte nuevoReporte = reporteService.guardarReporte(reporte);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoReporte);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarReporte(@PathVariable Long id, @RequestBody Reporte reporteActualizado) {
        try {
            Reporte reporte = reporteService.actualizarReporte(id, reporteActualizado.getNombreReporte(), reporteActualizado.getDescripcionReporte());
            return ResponseEntity.ok(reporte);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarReporte(@PathVariable Long id) {
        try {
            reporteService.eliminarReporte(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
