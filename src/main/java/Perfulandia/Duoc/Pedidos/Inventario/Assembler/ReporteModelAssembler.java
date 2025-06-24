package Perfulandia.Duoc.Pedidos.Inventario.Assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import Perfulandia.Duoc.Pedidos.Inventario.Controller.ReporteControllerV2;
import Perfulandia.Duoc.Pedidos.Inventario.Model.Reporte;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class ReporteModelAssembler implements RepresentationModelAssembler<Reporte, EntityModel<Reporte>> {

    @Override
    public EntityModel<Reporte> toModel(Reporte reporte) {
        return EntityModel.of(reporte,
                linkTo(methodOn(ReporteControllerV2.class).getReporteById(reporte.getIdReporte())).withSelfRel(),
                linkTo(methodOn(ReporteControllerV2.class).getAllReportes()).withRel("reportes"),
                linkTo(methodOn(ReporteControllerV2.class).createReporte(reporte)).withRel("crear-reporte"),
                linkTo(methodOn(ReporteControllerV2.class).updateReporte(reporte.getIdReporte(), reporte)).withRel("actualizar-reporte"),
                linkTo(methodOn(ReporteControllerV2.class).deleteReporte(reporte.getIdReporte())).withRel("eliminar-reporte"));
    }
}
