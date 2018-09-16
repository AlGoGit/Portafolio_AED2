package ut2.pd6;

import java.util.LinkedList;

public class TNodoTrieSufijos {

    private static final int CANT_CHR_ABECEDARIO = 26;
    private TNodoTrieSufijos[] hijos;
    private boolean esSufijo;
    private LinkedList posiciones;  //Indica la posicion de comienzo del sufijo en la secuencia

    public TNodoTrieSufijos() {
        hijos = new TNodoTrieSufijos[CANT_CHR_ABECEDARIO];
        esSufijo = false;
        posiciones = new LinkedList();
    }

    public void insertar(String unaPalabra, int posicion) {
        TNodoTrieSufijos nodo = this;
        for (int c = 0; c < unaPalabra.length(); c++) {
            int indice = unaPalabra.charAt(c) - 'a';
            if (nodo.hijos[indice] == null) {
                nodo.hijos[indice] = new TNodoTrieSufijos();
            }
            nodo = nodo.hijos[indice];
            nodo.posiciones.add(posicion);
            posicion++;
        }
        nodo.esSufijo = true;
    }

    private void imprimir(String s, TNodoTrieSufijos nodo) {
        if (nodo != null) {
            if (nodo.esSufijo) {
                System.out.println(s + " " + nodo.posiciones.toString());              
            }
            for (int c = 0; c < CANT_CHR_ABECEDARIO; c++) {
                if (nodo.hijos[c] != null) {
                    imprimir(s+(char)(c + 'a'), nodo.hijos[c]);
                }
            }
        }
    }

    public void imprimir() {
        imprimir("", this);
    }
    
    public String buscar(String s) {
        TNodoTrieSufijos nodo = this;
        for (int c = 0; c < s.length(); c++) {
            int indice = s.charAt(c) - 'a';
            if (nodo.hijos[indice] == null) {
                return "No existe el patron en la secuencia.";
            }
            nodo = nodo.hijos[indice];
        }
        LinkedList posicionesComienzo = new LinkedList();
        for (Object posicion : nodo.posiciones) {
            posicionesComienzo.add((int)posicion - (s.length()-1));
        }
        return s + " " + posicionesComienzo.toString();
    }  
}
