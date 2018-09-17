package ut3.pd5.ej3;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeSet;

public class TNodoTrie implements INodoTrie {

    private HashMap<Comparable, TNodoTrie> hijos;
    private boolean esNumero;
    private TAbonado abonado;

    public TNodoTrie() {
        hijos = new HashMap<>();
        esNumero = false;
        abonado = null;
    }

    @Override
    public void buscarTelefonos(String primerosDigitos, TreeSet<TAbonado> abonados) {
        boolean recorro = true;
        TNodoTrie nodo = this;
        String s = "";
        for (int c = 0; c < primerosDigitos.length(); c++) {
            char caracter = primerosDigitos.charAt(c);      
            if (nodo.hijos.containsKey(caracter)) {
                nodo = nodo.hijos.get(caracter);
                s += primerosDigitos.charAt(c);
            } else {
                recorro = false;
                break;
            }
        }
        if (recorro){
            agregarTelefonos(abonados, nodo);
        }
    }
    
    private void agregarTelefonos(TreeSet<TAbonado> abonados, TNodoTrie nodo) {
        if (nodo != null) {
            if (nodo.esNumero) {
                abonados.add(nodo.abonado);              
            }
            for (Comparable caracter : nodo.hijos.keySet()) {
                agregarTelefonos(abonados, nodo.hijos.get(caracter));
            }
        }
    }

    @Override
    public void insertar(TAbonado unAbonado) {
        TNodoTrie nodo = this;
        String unTelefono = unAbonado.getTelefono();
        for (int c = 0; c < unTelefono.length(); c++) {
            char caracter = unTelefono.charAt(c);
            if (!nodo.hijos.containsKey(caracter)) {
                nodo.hijos.put(caracter, new TNodoTrie());
            }
            nodo = nodo.hijos.get(caracter);
        }
        nodo.esNumero = true;
        nodo.abonado = unAbonado;
    }
}
