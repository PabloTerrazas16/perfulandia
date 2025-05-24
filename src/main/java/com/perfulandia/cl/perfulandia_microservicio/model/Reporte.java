package com.perfulandia.cl.perfulandia_microservicio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name="Reporte")
@Data
@AllArgsConstructor
@NoArgsConstructor



public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, length = 13, nullable = false)
    private Long idReporte;

    @Column(nullable = false)
    private String nombreReporte;

    @Column(nullable = false)
    private String tipoReporte;

    @Column(nullable = true)
    private String descripcionReporte;

    @Column(nullable = false)
    private Date fechaReporte;

}
