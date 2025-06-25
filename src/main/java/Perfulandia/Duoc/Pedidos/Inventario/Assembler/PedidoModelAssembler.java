package Perfulandia.Duoc.Pedidos.Inventario.Assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import Perfulandia.Duoc.Pedidos.Inventario.Controller.PedidoControllerV2;
import Perfulandia.Duoc.Pedidos.Inventario.Model.Pedido;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class PedidoModelAssembler implements RepresentationModelAssembler<Pedido, EntityModel<Pedido>> {

    @Override
    public EntityModel<Pedido> toModel(Pedido pedido) {
        return EntityModel.of(pedido,
                linkTo(methodOn(PedidoControllerV2.class).getPedidoById(pedido.getId())).withSelfRel(),
                linkTo(methodOn(PedidoControllerV2.class).getAllPedidos()).withRel("pedidos"),
                linkTo(methodOn(PedidoControllerV2.class).createPedido(pedido)).withRel("crear-pedido"),
                linkTo(methodOn(PedidoControllerV2.class).updatePedido(pedido.getId(), pedido)).withRel("actualizar-pedido"),
                linkTo(methodOn(PedidoControllerV2.class).deletePedido(pedido.getId())).withRel("eliminar-pedido"));
    }
}
