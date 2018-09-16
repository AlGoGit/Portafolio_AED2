package ut3.pd1;

import java.util.HashMap;
import java.util.LinkedList;

public class TNodoTrie implements INodoTrie {

    private HashMap<Comparable, TNodoTrie> hijos;
    private boolean esPalabra;
    private LinkedList paginas;

    public TNodoTrie() {
        hijos = new HashMap();
        esPalabra = false;
        paginas = new LinkedList();
    }

    public LinkedList getPaginas() {
        return paginas;
    }

    @Override
    public void insertar(String unaPalabra, int pagina) {
        TNodoTrie nodo = this;
        for (int c = 0; c < unaPalabra.length(); c++) {
            char caracter = unaPalabra.charAt(c);
            if (!nodo.hijos.containsKey(caracter)) {
                nodo.hijos.put(caracter, new TNodoTrie());
            }
            nodo = nodo.hijos.get(caracter);
        }
        nodo.esPalabra = true;
        nodo.getPaginas().add(pagina);
    }

    private void imprimir(String s, TNodoTrie nodo) {
        if (nodo != null) {
            if (nodo.esPalabra) {
                System.out.println(s + " " + nodo.getPaginas().toString());
            }
            for (Comparable caracter : hijos.keySet()) {
                imprimir(s + caracter, nodo.hijos.get(caracter));
            }
        }
    }

    @Override
    public void imprimir() {
        imprimir("", this);
    }

    public String buscar(String s) {
        TNodoTrie nodo = this;
        for (int c = 0; c < s.length(); c++) {
            char caracter = s.charAt(c);
            if (!nodo.hijos.containsKey(caracter)) {
                return "No existe en el indice.";
            }
            nodo = nodo.hijos.get(caracter);
        }
        return s + " " + nodo.getPaginas().toString();
    }

    private void predecirRecorrido(String s, LinkedList<String> palabras, TNodoTrie nodo) {
        if (nodo != null) {
            if (nodo.esPalabra) {
                palabras.add(s);
            }
            for (Comparable caracter : hijos.keySet()) {
                predecirRecorrido((s + caracter), palabras, nodo.hijos.get(caracter));
            }
        }
    }

    @Override
    public void predecir(String prefijo, LinkedList<String> palabras) {
        boolean recorro = true;
        TNodoTrie nodo = this;
        String s = "";
        for (int c = 0; c < prefijo.length(); c++) {
            char caracter = prefijo.charAt(c);
            if (nodo.hijos.containsKey(caracter)) {
                nodo = nodo.hijos.get(caracter);
                s += caracter;
            } else {
                recorro = false;
                break;
            }
        }
        if (recorro) {
            predecirRecorrido(s, palabras, nodo);
        }

    }
}
