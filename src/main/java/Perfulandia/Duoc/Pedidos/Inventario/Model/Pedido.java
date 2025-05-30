package Perfulandia.Duoc.Pedidos.Inventario.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "pedido")
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cliente;
    private LocalDate fecha;

    @ManyToMany // Cambia a @OneToMany si es necesario
    @JoinTable(
            name = "pedido_producto", // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "pedido_id"), // Columna en la tabla de pedidos
            inverseJoinColumns = @JoinColumn(name = "producto_id") // Columna en la tabla de productos
    )
    private List<Producto> productos;


}
