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
public class TArbolGenerico<T> {
    private TNodoArbolGenerico<T> raiz;

    public TArbolGenerico() {
    }

    public TNodoArbolGenerico<T> getRaiz() {
        return raiz;
    }

    public void setRaiz(TNodoArbolGenerico<T> raiz) {
        this.raiz = raiz;
    }
    
    public TNodoArbolGenerico<T> buscar(Comparable unaEtiqueta) {
        if (raiz != null) {
            return raiz.buscar(unaEtiqueta);
        } else {
            return null;
        }
    }
    
    public boolean insertar(Comparable unaEtiqueta, Comparable etiquetaPadre) {
        if (raiz != null) {
            return raiz.insertar(unaEtiqueta, etiquetaPadre);
        } else {
            if (etiquetaPadre.equals("")) {
                raiz = new TNodoArbolGenerico(unaEtiqueta, unaEtiqueta);
                return true;
            } else {
                return false;
            }
        }
    }
    
    public String listarIndentado() {
        if (raiz != null) {
            return raiz.listarIndentado(0);
        } else {
            return "";
        }
    }
}
