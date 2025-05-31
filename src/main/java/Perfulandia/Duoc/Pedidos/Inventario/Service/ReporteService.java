package Perfulandia.Duoc.Pedidos.Inventario.Service;

import Perfulandia.Duoc.Pedidos.Inventario.Model.Reporte;
import Perfulandia.Duoc.Pedidos.Inventario.Repository.ReporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    public List<Reporte> listarReportes() {
        return reporteRepository.findAll();
    }

    public List<Reporte> listarReportesOrdenadosPorFecha() {
        return reporteRepository.findAllOrderByFechaReporteDesc();
    }

    public List<Reporte> listarReportesDesdeFecha(Date fecha) {
        return reporteRepository.findReportesAfterFecha(fecha);
    }

    public Reporte obtenerReportePorId(Long id) {
        return reporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reporte no encontrado con ID: " + id));

    }

    public Reporte guardarReporte(Reporte reporte) {
        if (reporte.getNombreReporte() == null || reporte.getNombreReporte().trim().isEmpty()
                || reporte.getFechaReporte() == null) {
            throw new RuntimeException("El nombre y la fecha del reporte son obligatorios.");
        }
        return reporteRepository.save(reporte);
    }

    public Reporte actualizarReporte(Long id, String nuevoNombre, String nuevaDescripcion) {
        Reporte reporteExistente = obtenerReportePorId(id);
        reporteExistente.setNombreReporte(nuevoNombre);
        reporteExistente.setDescripcionReporte(nuevaDescripcion);
        return reporteRepository.save(reporteExistente);
    }

    public void eliminarReporte(Long id) {
        if (!reporteRepository.existsById(id)) {
            throw new RuntimeException("Reporte no encontrado con ID: " + id);
        }
        reporteRepository.deleteById(id);
    }
}
