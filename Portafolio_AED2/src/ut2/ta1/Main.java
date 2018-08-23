/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ut2.ta1;

/**
 *
 * @author Francisco
 */
public class Main {
    
    public static void main(String[] args) {
        TArbolGenerico arbol = new TArbolGenerico();
        TNodoArbolGenerico a = new TNodoArbolGenerico(null, "a");
        TNodoArbolGenerico b = new TNodoArbolGenerico(null, "b");
        TNodoArbolGenerico c = new TNodoArbolGenerico(null, "c");
        TNodoArbolGenerico d = new TNodoArbolGenerico(null, "d");
        TNodoArbolGenerico e = new TNodoArbolGenerico(null, "e");
        
        c.setPrimerHijo(e);
        c.setSiguienteHermano(d);
        b.setSiguienteHermano(c);
        a.setPrimerHijo(b);
        arbol.setRaiz(a);
        
        System.out.println(arbol.buscar("a").imprimir());
        System.out.println(arbol.buscar("d").imprimir());
        System.out.println(arbol.buscar("e").imprimir());
        System.out.println(arbol.buscar("f"));
    }         
}
