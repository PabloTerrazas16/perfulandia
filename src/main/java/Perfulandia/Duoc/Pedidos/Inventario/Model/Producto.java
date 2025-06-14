package Perfulandia.Duoc.Pedidos.Inventario.Model;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer stock;
    private Integer precio;
}


