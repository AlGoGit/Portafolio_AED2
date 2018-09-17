package ut3.ta3;
import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;

public class TNodoTrie implements Serializable {

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
        Collections.sort(nodo.getPaginas());
    }

    private void imprimir(String s, TNodoTrie nodo) {
        if (nodo != null) {
            if (nodo.esPalabra) {
                System.out.println(s + " " + nodo.getPaginas().toString());
            }
            for (int c = 0; c < CANT_CHR_ABECEDARIO; c++) {
                if (nodo.hijos[c] != null) {
                    imprimir(s + (char) (c + 'a'), nodo.hijos[c]);
                }
            }
        }
    }

    public void imprimir() {
        imprimir("", this);
    }
    
    public int buscar(String palabra) {
        palabra = palabra.toLowerCase(); // Paso la palabra a minuscula.
        int result = 0;
        int cantComp = 0;
        
        TNodoTrie nodo = this;
        for (int c = 0; c < palabra.length(); c++) {
            cantComp ++;
            int indice = palabra.charAt(c) - 'a';
            TNodoTrie nodoCaracter = nodo.hijos[indice];            
            if (nodoCaracter == null) { // Si no encuentra algun caracter.                
                result = 0;
            } else {
                if (c < palabra.length() - 1 ){ // Si no es el ultimo caracter, sigue buscando.
                    nodo = nodoCaracter;                    
                } else {
                    if ((c == palabra.length() - 1) && nodoCaracter.esPalabra) { // Si es el ultimo caracter y es palabra, esta contenida
                        result = cantComp;
                    } else {
                        result = 0; // En caso contrario no esta contenida.
                    }                    
                }                
            }
        }        
        return result;
    }
}
