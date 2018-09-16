

import java.util.LinkedList;

public class TNodoTrie implements INodoTrie {

    private static final int CANT_CHR_ABECEDARIO = 26;
    private TNodoTrie[] hijos;
    private boolean esPalabra;
    private LinkedList paginas;

    public TNodoTrie() {
        hijos = new TNodoTrie[CANT_CHR_ABECEDARIO];
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
            int indice = unaPalabra.charAt(c) - 'a';
            if (nodo.hijos[indice] == null) {
                nodo.hijos[indice] = new TNodoTrie();
            }
            nodo = nodo.hijos[indice];
        }
        nodo.esPalabra = true;
        nodo.getPaginas().add(pagina);
    }

    private void imprimir(String s, TNodoTrie nodo) {
        if (nodo != null) {
            if (nodo.esPalabra) {
                System.out.println(s + " " + nodo.getPaginas().toString());              
            }
            for (int c = 0; c < CANT_CHR_ABECEDARIO; c++) {
                if (nodo.hijos[c] != null) {
                    imprimir(s+(char)(c + 'a'), nodo.hijos[c]);
                }
            }
        }
    }

    @Override
    public void imprimir() {
        imprimir("", this);
    }
    
//    private TNodoTrie buscar(String s) {
//        TNodoTrie nodo = this;
//        for (int c = 0; c < s.length(); c++) {
//            int indice = s.charAt(c) - 'a';
//            if (nodo.hijos[indice] == null) {
//                return null;
//            }
//            nodo = nodo.hijos[indice];
//        }
//        return nodo;
//    }
    
    public String buscar(String s) {
        TNodoTrie nodo = this;
        int comparaciones = 0;
        for (int c = 0; c < s.length(); c++) {
            int indice = s.charAt(c) - 'a';
            if (nodo.hijos[indice] == null) {
                return "No existe en el indice.";
            }
            comparaciones++;
            nodo = nodo.hijos[indice];
        }
        return s + " " + nodo.getPaginas().toString() + " (" + comparaciones + " comparaciones)";
    }  
    
    private void predecirRecorrido(String s, LinkedList<String> palabras, TNodoTrie nodo) {
        if (nodo != null) {
            if (nodo.esPalabra) {
                palabras.add(s);              
            }
            for (int c = 0; c < CANT_CHR_ABECEDARIO; c++) {
                if (nodo.hijos[c] != null) {
                    predecirRecorrido(s+(char)(c + 'a'), palabras, nodo.hijos[c]);
                }
            }
        }
    }

    @Override
    public void predecir(String prefijo, LinkedList<String> palabras) {
        boolean recorro = true;
        TNodoTrie nodo = this;
        String s = "";
        for (int c = 0; c < prefijo.length(); c++) {
            int indice = prefijo.charAt(c) - 'a';      
            if (nodo.hijos[indice] != null) {
                nodo = nodo.hijos[indice];
                s += prefijo.charAt(c);
            } else {
                recorro = false;
                break;
            }
        }
        if (recorro){
            predecirRecorrido(s, palabras, nodo);
        }
        
    } 
}
