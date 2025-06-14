package Perfulandia.Duoc.Pedidos.Inventario.Repository;

import Perfulandia.Duoc.Pedidos.Inventario.Model.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Long> {

    // Obtiene todos los reportes ordenados por fechaReporte descendente
    @Query("SELECT r FROM Reporte r ORDER BY r.fechaReporte DESC")
    List<Reporte> findAllOrderByFechaReporteDesc();

    // Obtiene reportes cuya fechaReporte sea posterior a la fecha dada
    @Query("SELECT r FROM Reporte r WHERE r.fechaReporte > :fecha")
    List<Reporte> findReportesAfterFecha(Date fecha);

}
