/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ut2.pd1;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Francisco
 */
public class TArbolGenericoTest {
    TArbolGenerico instance;
    
    public TArbolGenericoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new TArbolGenerico();
        
        instance.insertar("a", "");
        instance.insertar("b", "a");
        instance.insertar("c", "a");
        instance.insertar("d", "a");
        instance.insertar("e", "c");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of buscar method, of class TArbolGenerico.
     */
    @Test
    public void testBuscar() {
        System.out.println("buscar");

        TNodoArbolGenerico result = instance.buscar("a");
        assertEquals("a", result.getClave());
        
        TNodoArbolGenerico result2 = instance.buscar("d");
        assertEquals("d", result2.getClave());
        
        TNodoArbolGenerico result3 = instance.buscar("f");
        assertEquals(null, result3);
    }

    /**
     * Test of insertar method, of class TArbolGenerico.
     */
    @Test
    public void testInsertar() {
        System.out.println("insertar");
 
        assertEquals("a\n\td\n\tc\n\t\te\n\tb\n", instance.listarIndentado());
    }   
}
