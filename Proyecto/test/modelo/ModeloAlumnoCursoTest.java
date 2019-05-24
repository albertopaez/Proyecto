/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alberto
 */
public class ModeloAlumnoCursoTest {
    
    public ModeloAlumnoCursoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of EliminarAlumnoCurso method, of class ModeloAlumnoCurso.
     */
    @Test
    public void testEliminarAlumnoCurso() {
        System.out.println("EliminarAlumnoCurso");
        String idAlumno = "esteAlumnoNoExiste";
        String nombreCurso = "esteCursoAunMenos";
        ModeloAlumnoCurso instance = new ModeloAlumnoCurso();
        boolean expResult = true;
        boolean result = instance.EliminarAlumnoCurso(idAlumno, nombreCurso);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of nuevoAlumnoCurso method, of class ModeloAlumnoCurso.
     */
    @Test
    public void testNuevoAlumnoCurso() {
        System.out.println("nuevoAlumnoCurso");
        String idAlumno = "TestJUnit";
        String nombreCurso = "TestJUnit";
        ModeloAlumnoCurso instance = new ModeloAlumnoCurso();
        ModeloAlumnoCurso limpia = new ModeloAlumnoCurso();
        boolean expResult = true;
        boolean result = instance.nuevoAlumnoCurso(idAlumno, nombreCurso);
        assertEquals(expResult, result);
        if(result==true){
            limpia.EliminarAlumnoCurso(idAlumno, nombreCurso);
        }
        
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
