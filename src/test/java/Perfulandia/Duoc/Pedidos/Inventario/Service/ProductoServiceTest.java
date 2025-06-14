package Perfulandia.Duoc.Pedidos.Inventario.Service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import Perfulandia.Duoc.Pedidos.Inventario.Model.Producto;
import Perfulandia.Duoc.Pedidos.Inventario.Repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test") // Activa el perfil de prueba
public class ProductoServiceTest {

    @Autowired
    private ProductoService productoService;

    @MockBean
    private ProductoRepository productoRepository;

    @Test
    public void testListarProductos() {
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Producto A");

        when(productoRepository.findAll()).thenReturn(List.of(producto));

        List<Producto> productos = productoService.listarProductos();
        assertNotNull(productos);
        assertEquals(1, productos.size());
        assertEquals("Producto A", productos.get(0).getNombre());
    }

    @Test
    public void testBuscarProductoPorNombre() {
        String nombre = "Producto A";
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre(nombre);
        when(productoRepository.findByNombre(nombre)).thenReturn(List.of(producto));

        List<Producto> found = productoService.buscarProductoPorNombre(nombre);
        assertNotNull(found);
        assertEquals(1, found.size());
        assertEquals(nombre, found.get(0).getNombre());
    }

    @Test
    public void testCrearProducto() {
        Producto producto = new Producto();
        producto.setId(1L);
        when(productoRepository.save(producto)).thenReturn(producto);

        Producto saved = productoService.crearProducto(producto);
        assertNotNull(saved);
        assertEquals(1L, saved.getId());
    }

    @Test
    public void testEliminarProducto() {
        Long id = 1L;
        Producto producto = new Producto();
        producto.setId(id);
        producto.setNombre("Producto A");

        // Simula que el producto existe en el repositorio
        when(productoRepository.findById(id)).thenReturn(Optional.of(producto));
        when(productoRepository.existsById(id)).thenReturn(true); // Simula que el producto existe

        doNothing().when(productoRepository).deleteById(id);

        // Llama al método eliminarProducto
        productoService.eliminarProducto(id);

        // Verifica que se haya llamado a deleteById
        verify(productoRepository, times(1)).deleteById(id);
    }

}
   