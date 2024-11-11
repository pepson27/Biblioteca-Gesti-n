package controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import models.Libro;
import models.Prestamo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PrestamoControllerTest {

private PrestamoController prestamoController;
    
    @Before
    public void setUp() throws Exception {
    	prestamoController = new PrestamoController();
    }

    @After
    public void tearDown() throws Exception {
    	prestamoController = null;
    }

    @Test
    public void testA_CreatePrestamo() {
        String resultado = prestamoController.createPrestamo(5, 2, "2024-04-20", "2030-07-20");
        System.out.println(resultado);
        assertEquals("Prestamo registrado", resultado);
    }
    
    @Test
    public void testB_ReadPrestamo() {        
        String resultado = prestamoController.readPrestamo(PrestamoController.getLastId());
        System.out.println(resultado);
        assertNotNull(resultado); // Verifica que el resultado no sea nulo
        assertNotEquals("Error al obtener prestamo", resultado); // Verifica que no se haya producido un error
    }

    @Test
    public void testC_UpdatePrestamo() {        
        String resultado = prestamoController.updatePrestamo(PrestamoController.getLastId(), "fechaDevolucion", "2035-07-27");
        System.out.println(resultado);
        assertEquals("Prestamo actualizado correctamente", resultado);
    }

    @Test
    public void testD_DeletePrestamo() {        
        String resultado = prestamoController.deletePrestamo(PrestamoController.getLastId());
        System.out.println(resultado);
        assertEquals("Prestamo eliminado correctamente", resultado);
    }

    @Test
    public void testE_GetAllPrestamos() {
        List<Prestamo> prestamos = prestamoController.getAllPrestamos();        
        assertNotEquals("Error al obtener lista de prestamos", prestamos);
    }
    
    
    public static SessionFactory mockSessionFactory() {
        SessionFactory sessionFactory = mock(SessionFactory.class);
        Session session = mock(Session.class);
        @SuppressWarnings({ "unused", "unchecked" })
		Query<Prestamo> query = mock(Query.class);
        List<Prestamo> prestamos = new ArrayList<>();
        List<Libro> libros = new ArrayList<>();
        Prestamo prestamo = new Prestamo();
        prestamos.add(prestamo);
        Libro libro = new Libro();
        libros.add(libro);

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.get(Libro.class, 1)).thenReturn(libro);
        
        @SuppressWarnings("unchecked")
		NativeQuery<BigInteger> nativeQuery = mock(NativeQuery.class);
        when(session.createNativeQuery("SELECT MAX(id) FROM Prestamo", BigInteger.class)).thenReturn(nativeQuery);

        return sessionFactory;
    }

    @Test
    public void testGetLibrosPrestadosALector() {
    	
    	PrestamoController prestamoController = new PrestamoController();
        prestamoController.init(mockSessionFactory());
        
		List<Libro> libros = prestamoController.getLibrosPrestadosALector(1);
        
        assertNotEquals("Error al obtener lista de libros prestados al lector", libros);
    }

    @Test
    public void testGetHistorialPrestamosPorLector() {
    	PrestamoController prestamoController = new PrestamoController();
        prestamoController.init(mockSessionFactory());
        
		List<Prestamo> historialPrestamos = prestamoController.getHistorialPrestamosPorLector(1);
        
        assertNotEquals("Error al obtener historial de prestamos por lector", historialPrestamos);
    }


}









