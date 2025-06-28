package Perfulandia.Duoc.Pedidos.Inventario.Repository;

import Perfulandia.Duoc.Pedidos.Inventario.Model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByCliente(String cliente);




}
