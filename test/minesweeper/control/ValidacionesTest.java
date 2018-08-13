/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.control;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author law
 */
public class ValidacionesTest {
    
    public ValidacionesTest() {
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
     * Test of validateInitialEntry method, of class Validaciones.
     */
    @Test
    public void testValidateInitialEntry() {
        System.out.println("validateInitialEntry");
        String text = "8 15 10";
        Validaciones instance = new Validaciones();
        
        ArrayList<Integer> exp = new ArrayList<Integer>();
        exp.add(8);
        exp.add(15);
        exp.add(10);
        
        ArrayList<Integer> expResult = exp;
        ArrayList<Integer> result = instance.validateInitialEntry(text);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    
    /**
     * Test of validateInitialEntry method, of class Validaciones.
     * Test para cuando el numero de minas sea 0
     */
    @Test
    public void testValidateInitialEntry_2() {
        System.out.println("validateInitialEntry");
        String text = "8 15 0";
        Validaciones instance = new Validaciones();
        
        ArrayList<Integer> exp = new ArrayList<Integer>();
        
        ArrayList<Integer> expResult = exp;
        ArrayList<Integer> result = instance.validateInitialEntry(text);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    /**
     * Test of validateInitialEntry method, of class Validaciones.
     * Test para cuando el numero de minas sea mayor o igual al total de celdas
     */
    @Test
    public void testValidateInitialEntry_3() {
        System.out.println("validateInitialEntry");
        String text = "8 15 120";
        Validaciones instance = new Validaciones();
        
        ArrayList<Integer> exp = new ArrayList<Integer>();
        
        ArrayList<Integer> expResult = exp;
        ArrayList<Integer> result = instance.validateInitialEntry(text);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    /**
     * Test of validateInitialEntry method, of class Validaciones.
     * Test para cuando el tablero sea muy pequeño
     */
    @Test
    public void testValidateInitialEntry_4() {
        System.out.println("validateInitialEntry");
        String text = "1 2 1";
        Validaciones instance = new Validaciones();
        
        ArrayList<Integer> exp = new ArrayList<Integer>();
        
        ArrayList<Integer> expResult = exp;
        ArrayList<Integer> result = instance.validateInitialEntry(text);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of isCorrectNumber method, of class Validaciones.
     */
    @Test
    public void testIsCorrectNumber() {
        System.out.println("isCorrectNumber");
        String text = "8";
        Validaciones instance = new Validaciones();
        boolean expResult = true;
        boolean result = instance.isCorrectNumber(text);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of entryNotValid method, of class Validaciones.
     */
    @Test
    public void testEntryNotValid() {
        System.out.println("entryNotValid");
        int error_type = 0;
        Validaciones instance = new Validaciones();
        instance.entryNotValid(error_type);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    
    
}
