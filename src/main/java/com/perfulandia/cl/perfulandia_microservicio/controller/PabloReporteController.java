package com.perfulandia.cl.perfulandia_microservicio.controller;


import com.perfulandia.cl.perfulandia_microservicio.model.Reporte;
import com.perfulandia.cl.perfulandia_microservicio.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reportes")
public class PabloReporteController {

    @Autowired
    private ReporteService reporteService;

    /*
     Obtiene todos los reportes existentes.
     */
    @GetMapping
    public ResponseEntity<List<Reporte>> obtenerTodosLosReportes() {
        List<Reporte> reportes = reporteService.listarReportes();
        if (reportes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}

        return ResponseEntity.ok(reportes);
    }

    /*
      Obtiene un reporte por ID
     */

    @GetMapping("/{id}")
    public ResponseEntity<Reporte> obtenerReportePorId(@PathVariable Long id) {
        Reporte reporte = reporteService.obtenerReportePorId(id);
        return reporte != null ? ResponseEntity.ok(reporte) : ResponseEntity.notFound().build();
    }

    /*
     Registra un nuevo reporte
     */

    @PostMapping
    public ResponseEntity<Reporte> crearReporte(@RequestBody Reporte reporte) {
        Reporte nuevoReporte = reporteService.guardarReporte(reporte);
        return ResponseEntity.ok(nuevoReporte);
    }

    /*
     Elimina un reporte por ID
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReporte(@PathVariable Long id) {
        reporteService.eliminarReporte(id);
        return ResponseEntity.noContent().build();
    }
}