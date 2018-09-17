package ut3.ta3;
import java.io.Serializable;



public class TArbolTrie implements Serializable {

    private TNodoTrie raiz;

    public void insertar(String palabra, int pagina) {
        if (raiz == null) {
            raiz = new TNodoTrie();
        }
        raiz.insertar(palabra, pagina);
    }

    public void imprimir() {
        if (raiz != null) {
            raiz.imprimir();
        }
    }
    
    public int buscar (String palabra) {
        if (raiz == null) {
            return 0;
        } else {
            return raiz.buscar(palabra);
        }
    }
}
