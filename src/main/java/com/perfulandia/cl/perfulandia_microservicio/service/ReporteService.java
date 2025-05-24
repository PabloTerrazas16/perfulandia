package com.perfulandia.cl.perfulandia_microservicio.service;

import com.perfulandia.cl.perfulandia_microservicio.model.Reporte;
import com.perfulandia.cl.perfulandia_microservicio.repository.ReporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    /*
      Obtiene todos los reportes
     */
    public List<Reporte> listarReportes() {
        return reporteRepository.findAll();
    }

    /*
      Obtiene un reporte por ID
     */
    public Reporte obtenerReportePorId(Long id) {
        Optional<Reporte> reporte = reporteRepository.findById(id);
        return reporte.orElse(null);
    }

    /**
     * Guarda un nuevo reporte en la base de datos
     */
    public Reporte guardarReporte(Reporte reporte) {
        return reporteRepository.save(reporte);
    }

    /*
      Elimina un reporte por ID
     */

    public void eliminarReporte(Long id) {
        reporteRepository.deleteById(id);
    }
}
