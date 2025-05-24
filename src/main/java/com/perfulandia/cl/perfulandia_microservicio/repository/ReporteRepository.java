package com.perfulandia.cl.perfulandia_microservicio.repository;

import com.perfulandia.cl.perfulandia_microservicio.model.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Long> {

    /**
     * Obtiene todos los reportes ordenados por fecha de generación.
     */
    @Query("SELECT r FROM Reporte r ORDER BY r.fechaReporte DESC")
    List<Reporte> obtenerReportesOrdenadosPorFecha();

    /**
     * Obtiene reportes generados después de una fecha específica.
     */
    @Query("SELECT r FROM Reporte r WHERE r.fechaReporte > :fecha")
    List<Reporte> obtenerReportesDesdeFecha(java.util.Date fecha);
}