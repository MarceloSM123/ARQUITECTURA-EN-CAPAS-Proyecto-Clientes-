package com.krakedev.clientes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.krakedev.clientes.entidades.Cliente;
import com.krakedev.clientes.services.ServicioCliente;

public class ServicioClienteTest {
	 private ServicioCliente servicio;
	    private Cliente cliente1;
	    private Cliente cliente2;
	    
	    @BeforeEach
	    void setUp() {
	        servicio = new ServicioCliente();
	        // Constructor ahora requiere 4 parámetros: cédula, nombre, apellido, email
	        cliente1 = new Cliente("1234567890", "Juan", "Perez", "juan@example.com");
	        cliente2 = new Cliente("0987654321", "Maria", "Gomez", "maria@example.com");
	    }
	    
	    @Test
	    void testCrearClienteExitoso() {
	        Cliente resultado = servicio.crear(cliente1);
	        
	        assertNotNull(resultado);
	        assertEquals("1234567890", resultado.getCedula());
	        assertEquals("Juan", resultado.getNombre());
	        assertEquals("Perez", resultado.getApellido());
	        assertEquals("juan@example.com", resultado.getEmail());
	        assertEquals(1, servicio.listar().size());
	    }
	    
	    @Test
	    void testCrearClienteDuplicado() {
	        servicio.crear(cliente1);
	        Cliente resultado = servicio.crear(cliente1);
	        
	        assertNull(resultado);
	        assertEquals(1, servicio.listar().size());
	    }
	    
	    @Test
	    void testBuscarPorCedulaExistente() {
	        servicio.crear(cliente1);
	        Cliente encontrado = servicio.buscarPorCedula("1234567890");
	        
	        assertNotNull(encontrado);
	        assertEquals("1234567890", encontrado.getCedula());
	        assertEquals("Juan", encontrado.getNombre());
	        assertEquals("juan@example.com", encontrado.getEmail());
	    }
	    
	    @Test
	    void testBuscarPorCedulaNoExistente() {
	        Cliente encontrado = servicio.buscarPorCedula("9999999999");
	        
	        assertNull(encontrado);
	    }
	    
	    @Test
	    void testListarClientes() {
	        servicio.crear(cliente1);
	        servicio.crear(cliente2);
	        
	        assertEquals(2, servicio.listar().size());
	        assertTrue(servicio.listar().contains(cliente1));
	        assertTrue(servicio.listar().contains(cliente2));
	    }
	    
	    @Test
	    void testListarClientesVacio() {
	        assertEquals(0, servicio.listar().size());
	        assertTrue(servicio.listar().isEmpty());
	    }
	    
	    @Test
	    void testActualizarClienteExistente() {
	        servicio.crear(cliente1);
	        Cliente clienteActualizado = new Cliente("1234567890", "Carlos", "Rodriguez", "carlos@example.com");
	        
	        Cliente resultado = servicio.actualizar("1234567890", clienteActualizado);
	        
	        assertNotNull(resultado);
	        assertEquals("Carlos", resultado.getNombre());
	        assertEquals("Rodriguez", resultado.getApellido());
	        
	        
	        Cliente verificar = servicio.buscarPorCedula("1234567890");
	        assertEquals("Carlos", verificar.getNombre());
	        assertEquals("Rodriguez", verificar.getApellido());
	       
	    }
	    
	    @Test
	    void testActualizarClienteNoExistente() {
	        Cliente clienteActualizado = new Cliente("9999999999", "Carlos", "Rodriguez", "carlos@example.com");
	        Cliente resultado = servicio.actualizar("9999999999", clienteActualizado);
	        
	        assertNull(resultado);
	    }
	    
	    @Test
	    void testEliminarClienteExistente() {
	        servicio.crear(cliente1);
	        boolean resultado = servicio.eliminar("1234567890");
	        
	        assertTrue(resultado);
	        assertEquals(0, servicio.listar().size());
	        assertNull(servicio.buscarPorCedula("1234567890"));
	    }
	    
	    @Test
	    void testEliminarClienteNoExistente() {
	        boolean resultado = servicio.eliminar("9999999999");
	        
	        assertFalse(resultado);
	        assertEquals(0, servicio.listar().size());
	    }
	    
	    @Test
	    void testCrearMultiplesClientes() {
	        servicio.crear(cliente1);
	        servicio.crear(cliente2);
	        Cliente cliente3 = new Cliente("5555555555", "Pedro", "Lopez", "pedro@example.com");
	        servicio.crear(cliente3);
	        
	        assertEquals(3, servicio.listar().size());
	        assertNotNull(servicio.buscarPorCedula("1234567890"));
	        assertNotNull(servicio.buscarPorCedula("0987654321"));
	        assertNotNull(servicio.buscarPorCedula("5555555555"));
	    }
}
