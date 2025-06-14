package Perfulandia.Duoc.Pedidos.Inventario;

import Perfulandia.Duoc.Pedidos.Inventario.Model.Pedido;
import Perfulandia.Duoc.Pedidos.Inventario.Model.Producto;
import Perfulandia.Duoc.Pedidos.Inventario.Model.Reporte;
import Perfulandia.Duoc.Pedidos.Inventario.Repository.PedidoRepository;
import Perfulandia.Duoc.Pedidos.Inventario.Repository.ProductoRepository;
import Perfulandia.Duoc.Pedidos.Inventario.Repository.ReporteRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Random;


@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ReporteRepository reporteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();

        // Generar reportes
        for (int i = 0; i < 5; i++) {
            Reporte reporte = new Reporte();
            reporte.setNombreReporte(faker.commerce().productName());
            reporte.setDescripcionReporte(faker.lorem().sentence());
            reporte.setFechaReporte(faker.date().past(30, java.util.concurrent.TimeUnit.DAYS));
            reporteRepository.save(reporte);
        }

        // Generar productos
        for (int i = 0; i < 10; i++) {
            Producto producto = new Producto();
            producto.setNombre(faker.commerce().productName());
            producto.setStock(faker.number().numberBetween(0, 100));
            producto.setPrecio(faker.number().numberBetween(1000, 50000));
            productoRepository.save(producto);
        }

        List<Producto> productos = productoRepository.findAll();
        if (productos.isEmpty()) {
            // Maneja el caso en que no hay productos
            System.out.println("No hay productos disponibles para crear pedidos.");
            return; // O lanza una excepción, o maneja el flujo como desees
        }

// Generar pedidos
        for (int i = 0; i < 20; i++) {
            Pedido pedido = new Pedido();
            pedido.setCliente(faker.name().fullName());
            pedido.setFecha(faker.date().past(10, java.util.concurrent.TimeUnit.DAYS).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());

            // Asegúrate de que la lista de productos no esté vacía
            int startIndex = random.nextInt(productos.size());
            int endIndex = random.nextInt(productos.size() - startIndex) + startIndex + 1; // Esto ahora es seguro
            pedido.setProductos(productos.subList(startIndex, endIndex)); // Selecciona productos aleatorios
            pedidoRepository.save(pedido);
        }


    }
}
