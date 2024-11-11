package controllers;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import models.Libro;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LibroControllerTest {

private LibroController libroController;
    
    @Before
    public void setUp() throws Exception {
        libroController = new LibroController();
    }

    @After
    public void tearDown() throws Exception {
        libroController = null;
    }

    @Test
    public void testA_CreateLibro() {
        String resultado = libroController.createLibro("El señor de los anillos", "J.R.R. Tolkien", 1954);
        System.out.println(resultado);
        assertEquals("Libro registrado", resultado);
    }
    
    @Test
    public void testB_ReadLibro() {
        
        String resultado = libroController.readLibro(LibroController.getLastId());
        System.out.println(resultado);
        assertNotNull(resultado); // Verifica que el resultado no sea nulo   
        assertNotEquals("Error al obtener libro", resultado); // Verifica que no se haya producido un error
    }

    @Test
    public void testC_UpdateLibro() {       
        String resultado = libroController.updateLibro(LibroController.getLastId(), "titulo", "El señor de los anillos - La Comunidad del Anillo");
        System.out.println(resultado);
        assertEquals("Libro actualizado correctamente", resultado);
    }

    @Test
    public void testD_DeleteLibro() {        
        String resultado = libroController.deleteLibro(LibroController.getLastId());
        System.out.println(resultado);
        assertEquals("Libro eliminado correctamente", resultado);
    }

    @Test
    public void testE_GetAllLibros() {
        List<Libro> libros = libroController.getAllLibros();        
        assertNotEquals("Error al obtener lista de libros", libros);
    }

    @Test
    public void testF_GetAllLibrosDisponibles() {
        List<Libro> librosDisponibles = libroController.getAllLibrosDisponibles();       
        assertNotEquals("Error al obtener lista de libros disponibles para prestamo", librosDisponibles);
    }

}
