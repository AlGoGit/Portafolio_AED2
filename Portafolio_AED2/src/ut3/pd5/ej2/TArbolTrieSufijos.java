package ut3.pd5.ej2;

import java.util.LinkedList;

public class TArbolTrieSufijos {

    private TNodoTrieSufijos raiz;

    public void insertar(String palabra, int posicion) {
        if (raiz == null) {
            raiz = new TNodoTrieSufijos();
        }
        raiz.insertar(palabra, posicion);
    }

    public void imprimir() {
        if (raiz != null) {
            raiz.imprimir();
        }
    }

    public String buscar(String palabra) {
        if (raiz != null) {
            return raiz.buscar(palabra);
        } else {
            return "No existe el patron en la secuencia.";
        }
    }
}
