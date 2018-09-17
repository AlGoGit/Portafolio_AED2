package ut3.pd5.ej3;
import java.util.LinkedList;
import java.util.TreeSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ernesto
 */
public interface INodoTrie {

    void buscarTelefonos(String primerosDigitos, TreeSet<TAbonado> abonados);

    void insertar(TAbonado unAbonado);
    
}
