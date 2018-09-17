package ut3.pd5.ej3;
import java.util.Collection;
import java.util.LinkedList;
import java.util.TreeSet;

public class TArbolTrie implements IArbolTrie {

    private TNodoTrie raiz;

    @Override
    public TreeSet<TAbonado> buscarTelefonos(String pais, String area) {
        String primerosDigitos = pais + area;
        TreeSet<TAbonado> abonados = new TreeSet<>();
        if (raiz != null) {
            raiz.buscarTelefonos(primerosDigitos, abonados);
        }
        return abonados;
    }

    @Override
    public void insertar(TAbonado unAbonado) {
        if (raiz == null) {
            raiz = new TNodoTrie();
        }
        raiz.insertar(unAbonado);
    }
}
