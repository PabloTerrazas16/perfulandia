package Perfulandia.Duoc.Pedidos.Inventario.Assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import Perfulandia.Duoc.Pedidos.Inventario.Controller.ProductoController;
import Perfulandia.Duoc.Pedidos.Inventario.Model.Producto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class ProductoModelAssembler implements RepresentationModelAssembler<Producto, EntityModel<Producto>> {



    @Override
    public EntityModel<Producto> toModel(Producto producto) {
        return EntityModel.of(producto,
                linkTo(methodOn(ProductoController.class).listarProductos()).withRel("productos"),
                linkTo(methodOn(ProductoController.class).buscarPorNombre(producto.getNombre())).withRel("buscar-por-nombre"),
                linkTo(methodOn(ProductoController.class).crearProducto(producto)).withRel("crear-producto"),
                linkTo(methodOn(ProductoController.class).actualizarProducto(producto.getId(), producto)).withRel("actualizar-producto"),
                linkTo(methodOn(ProductoController.class).eliminarProducto(producto.getId())).withRel("eliminar-producto"));
    }
}
