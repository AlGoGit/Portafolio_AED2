package parcial1;


import java.util.Collection;
import java.util.HashSet;

public class TArbolTrie implements IArbolTrie {

    private TNodoTrie raiz;

    @Override
    public void insertar(TDispositivo unDispositivo) {
        if (raiz == null) {
            raiz = new TNodoTrie();
        }
        raiz.insertar(unDispositivo);
    }

    @Override
    public Collection<TDispositivo> buscarDispositivos(String mascaraSubRed) {
        Collection<TDispositivo> dispositivos = new HashSet<>();
        if (raiz != null) {
            raiz.buscarDispositivos(mascaraSubRed, dispositivos);
        }
        return dispositivos;
    }
}
