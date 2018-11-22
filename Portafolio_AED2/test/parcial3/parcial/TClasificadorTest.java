package parcial3.parcial;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
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
public class TClasificadorTest {

    TProduct producto1;
    TProduct producto2;
    TProduct producto3;
    TProduct producto4;
    TProduct producto5;
    TProduct producto6;

    public TClasificadorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        try {
            producto1 = new TProduct("1, , ,a, , , , , , , , , , , ");
            producto2 = new TProduct("2, , ,b, , , , , , , , , , , ");
            producto3 = new TProduct("3, , ,c, , , , , , , , , , , ");
            producto4 = new TProduct("4, , ,d, , , , , , , , , , , ");
            producto5 = new TProduct("5, , ,e, , , , , , , , , , , ");
            producto6 = new TProduct("6, , ,f, , , , , , , , , , , ");
        } catch (Exception e) {}
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of ordenarPorQuickSort method, of class TClasificador.
     */
    @Test
    public void testOrdenarPorQuickSortVacio() {
        TClasificador clasificador = new TClasificador();
        
        ArrayList<TProduct> prueba = new ArrayList();
        
        ArrayList<TProduct> pruebaOrdenada = clasificador.ordenarPorQuickSort(prueba);
        
        ArrayList<TProduct> expResult = new ArrayList();

        assertEquals(expResult, pruebaOrdenada);
    }
    
    @Test
    public void testOrdenarPorQuickSortAscendente() {
        TClasificador clasificador = new TClasificador();
        
        ArrayList<TProduct> prueba = new ArrayList();
        prueba.add(producto1);
        prueba.add(producto2);
        prueba.add(producto3);
        prueba.add(producto4);
        prueba.add(producto5);
        prueba.add(producto6);
        
        ArrayList<TProduct> pruebaOrdenada = clasificador.ordenarPorQuickSort(prueba);
        
        ArrayList<TProduct> expResult = new ArrayList();
        expResult.add(producto6);
        expResult.add(producto5);
        expResult.add(producto4);
        expResult.add(producto3);
        expResult.add(producto2);
        expResult.add(producto1);
        
        assertEquals(expResult, pruebaOrdenada);
    }
    
    @Test
    public void testOrdenarPorQuickSortDescendente() {
        TClasificador clasificador = new TClasificador();
        
        ArrayList<TProduct> prueba = new ArrayList();
        prueba.add(producto6);
        prueba.add(producto5);
        prueba.add(producto4);
        prueba.add(producto3);
        prueba.add(producto2);
        prueba.add(producto1);
        
        ArrayList<TProduct> pruebaOrdenada = clasificador.ordenarPorQuickSort(prueba);
        
        ArrayList<TProduct> expResult = new ArrayList();
        expResult.add(producto6);
        expResult.add(producto5);
        expResult.add(producto4);
        expResult.add(producto3);
        expResult.add(producto2);
        expResult.add(producto1);
        
        assertEquals(expResult, pruebaOrdenada);
    }
    
    @Test
    public void testOrdenarPorQuickSortDesordenado() {
        TClasificador clasificador = new TClasificador();
        
        ArrayList<TProduct> prueba = new ArrayList();
        prueba.add(producto4);
        prueba.add(producto2);
        prueba.add(producto5);
        prueba.add(producto1);
        prueba.add(producto6);
        prueba.add(producto3);
        
        ArrayList<TProduct> pruebaOrdenada = clasificador.ordenarPorQuickSort(prueba);
        
        ArrayList<TProduct> expResult = new ArrayList();
        expResult.add(producto6);
        expResult.add(producto5);
        expResult.add(producto4);
        expResult.add(producto3);
        expResult.add(producto2);
        expResult.add(producto1);
        
        assertEquals(expResult, pruebaOrdenada);
    }

}
