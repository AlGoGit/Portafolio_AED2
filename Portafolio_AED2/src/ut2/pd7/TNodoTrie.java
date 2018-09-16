package ut2.pd7;

import java.util.LinkedList;
import java.util.TreeSet;

public class TNodoTrie implements INodoTrie {

    private static final int CANT_DIGITOS = 10;
    private TNodoTrie[] hijos;
    private boolean esNumero;
    private TAbonado abonado;

    public TNodoTrie() {
        hijos = new TNodoTrie[CANT_DIGITOS];
        esNumero = false;
        abonado = null;
    }

    @Override
    public void buscarTelefonos(String primerosDigitos, TreeSet<TAbonado> abonados) {
        boolean recorro = true;
        TNodoTrie nodo = this;
        String s = "";
        for (int c = 0; c < primerosDigitos.length(); c++) {
            int indice = primerosDigitos.charAt(c) - '0';      
            if (nodo.hijos[indice] != null) {
                nodo = nodo.hijos[indice];
                s += primerosDigitos.charAt(c);
            } else {
                recorro = false;
                break;
            }
        }
        if (recorro){
            agregarTelefonos(s, abonados, nodo);
        }
    }
    
    private void agregarTelefonos(String s, TreeSet<TAbonado> abonados, TNodoTrie nodo) {
        if (nodo != null) {
            if (nodo.esNumero) {
                abonados.add(nodo.abonado);              
            }
            for (int c = 0; c < CANT_DIGITOS; c++) {
                if (nodo.hijos[c] != null) {
                    agregarTelefonos(s+(char)(c + '0'), abonados, nodo.hijos[c]);
                }
            }
        }
    }

    @Override
    public void insertar(TAbonado unAbonado) {
        TNodoTrie nodo = this;
        String unTelefono = unAbonado.getTelefono();
        for (int c = 0; c < unTelefono.length(); c++) {
            int indice = unTelefono.charAt(c) - '0';
            if (nodo.hijos[indice] == null) {
                nodo.hijos[indice] = new TNodoTrie();
            }
            nodo = nodo.hijos[indice];
        }
        nodo.esNumero = true;
        nodo.abonado = unAbonado;
    }
}
