
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
    public int buscar(String palabra) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            String p1 = p.replaceAll("[,:;.\"()]?!", "");
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
