package parcial1.parcial;



import java.util.Collection;
import java.util.HashMap;

public class TNodoTrie implements INodoTrie {

    private HashMap<Comparable, TNodoTrie> hijos;
    private boolean esDireccion;
    private TDispositivo dispositivo;

    public TNodoTrie() {
        hijos = new HashMap();
        esDireccion = false;
        dispositivo = null;
    }
    
    @Override
    public void insertar(TDispositivo unDispositivo) {
        String ip = unDispositivo.getDirIP();
        TNodoTrie nodo = this;
        for (int c = 0; c < ip.length(); c++) {
            char caracter = ip.charAt(c);
            if (!nodo.hijos.containsKey(caracter)) {
                nodo.hijos.put(caracter, new TNodoTrie());
            }
            nodo = nodo.hijos.get(caracter);
        }
        nodo.esDireccion = true;
        nodo.dispositivo = unDispositivo;
    }

    public void buscarDispositivos(String mascara, Collection<TDispositivo> dispositivos) {
        boolean recorro = true;
        TNodoTrie nodo = this;
        for (int c = 0; c < mascara.length(); c++) {
            char caracter = mascara.charAt(c);
            if (nodo.hijos.containsKey(caracter)) {
                nodo = nodo.hijos.get(caracter);
            } else {
                recorro = false;
                break;
            }
        }
        if (recorro) {
            conseguirDispositivos(dispositivos, nodo);
        }
    }
    
    private void conseguirDispositivos(Collection<TDispositivo> dispositivos, TNodoTrie nodo) {
        if (nodo != null) {
            if (nodo.esDireccion) {
                dispositivos.add(nodo.dispositivo);
            }
            for (Comparable caracter : nodo.hijos.keySet()) {
                conseguirDispositivos(dispositivos, nodo.hijos.get(caracter));
            }
        }
    }
}
