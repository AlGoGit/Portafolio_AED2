/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ut2.pd1;

/**
 *
 * @author Francisco
 * @param <T>
 */
public class TNodoArbolGenerico<T> {

    private final T dato;
    private final Comparable clave;
    private TNodoArbolGenerico<T> primerHijo;
    private TNodoArbolGenerico<T> siguienteHermano;

    public TNodoArbolGenerico(T dato, Comparable clave) {
        this.dato = dato;
        this.clave = clave;
        this.primerHijo = null;
        this.siguienteHermano = null;
    }

    public T getDato() {
        return dato;
    }

    public Comparable getClave() {
        return clave;
    }

    public TNodoArbolGenerico<T> getPrimerHijo() {
        return primerHijo;
    }

    public TNodoArbolGenerico<T> getSiguienteHermano() {
        return siguienteHermano;
    }

    public void setPrimerHijo(TNodoArbolGenerico<T> primerHijo) {
        this.primerHijo = primerHijo;
    }

    public void setSiguienteHermano(TNodoArbolGenerico<T> siguienteHermano) {
        this.siguienteHermano = siguienteHermano;
    }

    public TNodoArbolGenerico<T> buscar(Comparable unaEtiqueta) {
        if (clave.equals(unaEtiqueta)) {
            return this;
        } else {
            TNodoArbolGenerico unHijo = primerHijo;
            while (unHijo != null) {
                TNodoArbolGenerico resultado = unHijo.buscar(unaEtiqueta);
                if (resultado != null) {
                    return resultado;
                } else {
                    unHijo = unHijo.getSiguienteHermano();
                }
            }
            return null;
        }
    }

    public String imprimir() {
        return this.clave.toString();
    }

    public boolean insertar(Comparable unaEtiqueta, Comparable etiquetaPadre) {
        if (clave.equals(etiquetaPadre)) {
            TNodoArbolGenerico<T> nodo = new TNodoArbolGenerico(unaEtiqueta, unaEtiqueta);
            nodo.setSiguienteHermano(primerHijo);
            primerHijo = nodo;
            return true;
        } else {
            TNodoArbolGenerico unHijo = primerHijo;
            while (unHijo != null) {
                boolean resultado = unHijo.insertar(unaEtiqueta, etiquetaPadre);
                if (resultado) {
                    return resultado;
                } else {
                    unHijo = unHijo.getSiguienteHermano();
                }
            }
            return false;
        }
    }

    public String listarIndentado(int nivel) {
        String str = repetir("\t", nivel) + imprimir() + "\n";
        TNodoArbolGenerico unHijo = primerHijo;
        while (unHijo != null) {
            str += unHijo.listarIndentado(nivel+1);
            unHijo = unHijo.getSiguienteHermano();
        }
        return str;
    }

    private String repetir(String s, int n) {
        String str = "";
        for (int i = 0; i < n; i++) {
            str += s;
        }
        return str;
    }
}
