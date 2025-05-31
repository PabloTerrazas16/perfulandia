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

    @Autowired
    private PedidoService pedidoService;

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

    public Pedido editarPedido(Long id, Pedido pedidoActualizado) {
        Pedido pedidoExistente = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + id));
        pedidoExistente.setCliente(pedidoActualizado.getCliente());
        pedidoExistente.setFecha(pedidoActualizado.getFecha());
        pedidoExistente.setProductos(pedidoActualizado.getProductos());
        return pedidoRepository.save(pedidoExistente);
    }







}
