/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ut2.pd7;

import java.util.TreeSet;
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
public class TArbolTrieTest {
    TArbolTrie instance;
    TAbonado ab1 = new TAbonado("a", "123456789");
    TAbonado ab2 = new TAbonado("b", "123451234");
    TAbonado ab3 = new TAbonado("c", "123454567");
    TAbonado ab4 = new TAbonado("d", "789456123");
    
    public TArbolTrieTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new TArbolTrie();
        instance.insertar(ab1);
        instance.insertar(ab2);
        instance.insertar(ab3);
        instance.insertar(ab4);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of buscarTelefonos method, of class TArbolTrie.
     */
    @Test
    public void testBuscarTelefonos() {
        System.out.println("buscarTelefonos");

        TreeSet<TAbonado> expResult1 = new TreeSet<>();
        expResult1.add(ab1);
        expResult1.add(ab2);
        expResult1.add(ab3);
        TreeSet<TAbonado> result1 = instance.buscarTelefonos("123", "45");
        assertEquals(expResult1, result1);
        
        TreeSet<TAbonado> expResult2 = new TreeSet<>();
        TreeSet<TAbonado> result2 = instance.buscarTelefonos("125", "45");
        assertEquals(expResult2, result2);
    }
}
