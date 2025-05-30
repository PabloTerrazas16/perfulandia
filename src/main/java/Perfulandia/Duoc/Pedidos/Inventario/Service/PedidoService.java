package Perfulandia.Duoc.Pedidos.Inventario.Service;

import Perfulandia.Duoc.Pedidos.Inventario.Model.Pedido;
import Perfulandia.Duoc.Pedidos.Inventario.Repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }
    public long contarPedidos() {
        return pedidoRepository.count();
    }

    public Pedido crearPedido(Pedido pedido) {

        return pedidoRepository.save(pedido);
    }

    public Pedido obtenerPedido(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
    }

    public void eliminarPedido(Long id) {
        if (pedidoRepository.existsById(id)) {
            pedidoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Pedido no encontrado con ID: " + id);
        }
    }







}
