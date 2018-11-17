package parcial1.parcial;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Collection;
import java.util.HashSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author usuario
 */
public class TArbolTrieTest {
    TArbolTrie instance;
    TDispositivo d1 = new TDispositivo("123456", "a");
    TDispositivo d2 = new TDispositivo("123789", "b");
    TDispositivo d3 = new TDispositivo("123753", "c");
    TDispositivo d4 = new TDispositivo("128956", "d");
    TDispositivo d5 = new TDispositivo("127456", "e");
    
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
        instance.insertar(d1);
        instance.insertar(d2);
        instance.insertar(d3);
        instance.insertar(d4);
        instance.insertar(d5);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of buscarDispositivos method, of class TArbolTrie.
     */
    @Test
    public void testBuscarDispositivos() {
        System.out.println("buscarDispositivos");
        
        //
        
        Collection<TDispositivo> expResult1 = new HashSet<>();
        expResult1.add(d1);
        expResult1.add(d2);
        expResult1.add(d3);
        Collection<TDispositivo> result1 = instance.buscarDispositivos("123");
        assertEquals(expResult1, result1);
        
        //
        
        Collection<TDispositivo> expResult2 = new HashSet<>();
        expResult2.add(d4);
        Collection<TDispositivo> result2 = instance.buscarDispositivos("128");
        assertEquals(expResult2, result2);
        
        //
        
        Collection<TDispositivo> expResult3 = new HashSet<>();
        Collection<TDispositivo> result3 = instance.buscarDispositivos("456");
        assertEquals(expResult3, result3);
    }
    
}
