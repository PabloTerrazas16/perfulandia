package Perfulandia.Duoc.Pedidos.Inventario.Model;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
@Entity
@Table(name = "reporte")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reporte", unique = true, nullable = false)
    private Long idReporte;
    @Column(name = "nombre_reporte", nullable = false)
    private String nombreReporte;
    @Column(name = "descripcion_reporte")
    private String descripcionReporte;
    @Column(name = "fecha_reporte", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaReporte;
}
