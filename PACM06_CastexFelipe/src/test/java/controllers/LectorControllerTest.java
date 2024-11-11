package controllers;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import models.Lector;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LectorControllerTest {

private LectorController lectorController;
    
    @Before
    public void setUp() throws Exception {
        lectorController = new LectorController();
    }

    @After
    public void tearDown() throws Exception {
        lectorController = null;
    }

    @Test
    public void testA_CreateLector() {
        String resultado = lectorController.createLector("Santiago", "Marquez", "santimar97@gmail.com");
        System.out.println(resultado);
        assertEquals("Lector registrado", resultado);
    }
    
    @Test
    public void testB_ReadLector() {        
        String resultado = lectorController.readLector(LectorController.getLastId());
        System.out.println(resultado);
        assertNotNull(resultado); // Verifica que el resultado no sea nulo
        assertNotEquals("Error al obtener lector", resultado); // Verifica que no se haya producido un error
    }

    @Test
    public void testC_UpdateLector() {        
        String resultado = lectorController.updateLector(LectorController.getLastId(), "nombre", "Nicolas");
        System.out.println(resultado);
        assertEquals("Lector actualizado correctamente", resultado);
    }

    @Test
    public void testD_DeleteLector() {        
        String resultado = lectorController.deleteLector(LectorController.getLastId());
        System.out.println(resultado);
        assertEquals("Lector eliminado correctamente", resultado);
    }

    @Test
    public void testE_GetAllLectores() {
        List<Lector> lectores = lectorController.getAllLectores();       
        assertNotEquals("Error al obtener lista de lectores", lectores); // Verifica que no se haya producido un error
    }
   

}