package Perfulandia.Duoc.Pedidos.Inventario.Controller;

import Perfulandia.Duoc.Pedidos.Inventario.Assembler.ReporteModelAssembler;
import Perfulandia.Duoc.Pedidos.Inventario.Model.Reporte;
import Perfulandia.Duoc.Pedidos.Inventario.Service.ReporteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/reportes")
@Tag(name="ReportesV2", description = "Controlador que devuelve solicitudes Hateoas")
public class ReporteControllerV2 {

    @Autowired
    private ReporteService reporteService;

    @Autowired
    private ReporteModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Reporte>> getAllReportes() {
        List<EntityModel<Reporte>> reportes = reporteService.listarReportes().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(reportes,
                linkTo(methodOn(ReporteControllerV2.class).getAllReportes()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Reporte> getReporteById(@PathVariable Long id) {
        Reporte reporte = reporteService.obtenerReportePorId(id);
        return assembler.toModel(reporte);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Reporte>> createReporte(@RequestBody Reporte reporte) {
        Reporte newReporte = reporteService.guardarReporte(reporte);
        return ResponseEntity
                .created(linkTo(methodOn(ReporteControllerV2.class).getReporteById(newReporte.getIdReporte())).toUri())
                .body(assembler.toModel(newReporte));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Reporte>> updateReporte(@PathVariable Long id, @RequestBody Reporte reporte) {
        reporte.setIdReporte(id);
        Reporte updatedReporte = reporteService.guardarReporte(reporte);
        return ResponseEntity.ok(assembler.toModel(updatedReporte));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteReporte(@PathVariable Long id) {
        reporteService.eliminarReporte(id);
        return ResponseEntity.noContent().build();
    }
}
