package ut3.pd1;
import java.util.LinkedList;

public class TArbolTrie implements IArbolTrie {

    private TNodoTrie raiz;

    @Override
    public void insertar(String palabra, int pagina) {
        if (raiz == null) {
            raiz = new TNodoTrie();
        }
        raiz.insertar(palabra, pagina);
    }

    @Override
    public void imprimir() {
        if (raiz != null) {
            raiz.imprimir();
        }
    }

    @Override
    public String buscar(String palabra) {
        if (raiz != null) {
            return raiz.buscar(palabra);
        } else {
            return "No existe en el indice.";
        }
    }

    @Override
    public LinkedList<String> predecir(String prefijo) {
        LinkedList<String> lista = new LinkedList<>();
        if (raiz != null) {
            raiz.predecir(prefijo, lista);
        }
        return lista;
    }
    
    public void indizarLibro(String archivo) { 
        String[] palabrasclave = ManejadorArchivosGenerico.leerArchivo(archivo); 
        int pagina = 1; 
        int linea = 1; 
        for (String p : palabrasclave) { 
            String p1 = p.replaceAll("[^a-zA-Z ]", ""); 
            String[] p2 = p1.split(" "); 
            for (String p3 : p2) { 
                insertar(p3.toLowerCase(), pagina);   
            } 
            if (linea >= 50) { 
                pagina++; 
                linea = 0; 
            } 
            linea++; 
        } 
    } 
}
