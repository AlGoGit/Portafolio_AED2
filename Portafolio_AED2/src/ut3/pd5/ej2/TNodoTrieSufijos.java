package ut3.pd5.ej2;

import java.util.HashMap;
import java.util.LinkedList;

public class TNodoTrieSufijos {

    private HashMap<Comparable, TNodoTrieSufijos> hijos;
    private boolean esSufijo;
    private LinkedList posiciones;  //Indica la posicion de comienzo del sufijo en la secuencia

    public TNodoTrieSufijos() {
        hijos = new HashMap<>();
        esSufijo = false;
        posiciones = new LinkedList();
    }

    public void insertar(String unaPalabra, int posicion) {
        TNodoTrieSufijos nodo = this;
        for (int c = 0; c < unaPalabra.length(); c++) {
            char caracter = unaPalabra.charAt(c);
            if (!nodo.hijos.containsKey(caracter)) {
                nodo.hijos.put(caracter, new TNodoTrieSufijos());
            }
            nodo = nodo.hijos.get(caracter);
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
            for (Comparable caracter : nodo.hijos.keySet()) {
                imprimir(s + caracter, nodo.hijos.get(caracter));
            }
        }
    }

    public void imprimir() {
        imprimir("", this);
    }

    public String buscar(String s) {
        TNodoTrieSufijos nodo = this;
        for (int c = 0; c < s.length(); c++) {
            char caracter = s.charAt(c);
            if (!nodo.hijos.containsKey(caracter)) {
                return "No existe el patron en la secuencia.";
            }
            nodo = nodo.hijos.get(caracter);
        }
        LinkedList posicionesComienzo = new LinkedList();
        for (Object posicion : nodo.posiciones) {
            posicionesComienzo.add((int) posicion - (s.length() - 1));
        }
        return s + " " + posicionesComienzo.toString();
    }
}
