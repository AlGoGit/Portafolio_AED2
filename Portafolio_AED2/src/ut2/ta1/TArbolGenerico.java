/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ut2.ta1;

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
}
