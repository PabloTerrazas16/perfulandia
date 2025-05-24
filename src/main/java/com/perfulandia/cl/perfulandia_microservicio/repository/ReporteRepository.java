package com.perfulandia.cl.perfulandia_microservicio.repository;

import com.perfulandia.cl.perfulandia_microservicio.model.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Long> {


}