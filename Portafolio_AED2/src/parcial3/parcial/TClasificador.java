package parcial3.parcial;


import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author usuario
 */
public class TClasificador {
    
    protected ArrayList<TProduct> ordenarPorQuickSort(ArrayList<TProduct> datosParaClasificar) {
        if (datosParaClasificar.size() > 0) {
            quicksort(datosParaClasificar, 0, datosParaClasificar.size() - 1);
        }
        return datosParaClasificar;
    }

    private ArrayList<TProduct> quicksort(ArrayList<TProduct> entrada, int i, int j) {
        int izquierda = i;
        int derecha = j;
        int posicionPivote = encuentraPivote(izquierda, derecha);
        if (posicionPivote >= 0) {
            TProduct pivote = entrada.get(posicionPivote);
            while (izquierda < derecha) {
                while ((entrada.get(izquierda).compareTo(pivote) > 0) && (izquierda < j)) {
                    izquierda++;
                }
                while ((entrada.get(derecha).compareTo(pivote) < 0) && (derecha > i)) {
                    derecha--;
                }
                if (izquierda <= derecha) {
                    intercambiar(entrada, derecha, izquierda);
                    izquierda++;
                    derecha--;
                }
            }
            if (i < derecha) {
                quicksort(entrada, i, derecha);
            }
            if (izquierda < j) {
                quicksort(entrada, izquierda, j);
            }
        }
        return entrada;
    }
    
    private int encuentraPivote(int izq, int der) {
        if (izq == der) {
            return -1;
        }
        return ((izq + der) / 2);
    }
    
    private void intercambiar(ArrayList<TProduct> entrada, int pos1, int pos2) {
        TProduct temp = entrada.get(pos2);
        entrada.set(pos2, entrada.get(pos1));
        entrada.set(pos1, temp);
    }
}
