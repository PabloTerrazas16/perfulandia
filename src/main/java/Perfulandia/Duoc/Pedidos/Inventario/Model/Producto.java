package Perfulandia.Duoc.Pedidos.Inventario.Model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "producto")
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer stock;
    private int precio;





}


