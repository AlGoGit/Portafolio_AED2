/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ut3.ta4;

import java.util.Arrays;

public class THash implements IHash {

    private Comparable[] tabla;

    public THash(int cantidadClaves, double factorCarga) {
        tabla = new Comparable[tamañoTabla(cantidadClaves, factorCarga)];
    }

    public Comparable[] getTabla() {
        return tabla;
    }

    private int tamañoTabla(int cantClaves, double factorCarga) {
        return siguientePrimo((int)(cantClaves/factorCarga));
    }

    private boolean esPrimo(int numero) {
        int divisor = numero - 1;
        while (divisor > 1 && numero % divisor != 0) {
            divisor--;
        }
        return (divisor == 1);
    }

    private int siguientePrimo(int numero) {
        numero++;
        while (!esPrimo(numero)) {
            numero++;
        }
        return numero;
    }

    @Override
    public int buscar(int unaClave) {
        int comparaciones = 0;
        int claveHash0 = funcionHashing(unaClave);
        int claveHash = claveHash0;
        int i = 1;
        if (tabla[claveHash] == null) {
            return -1;
        } else {
            while (tabla[claveHash] != null) {
                comparaciones++;
                if (tabla[claveHash].equals(unaClave)) {
                    return comparaciones;
                }
                claveHash = claveHash0 + (i*i);
                i++;
                while (claveHash >= tabla.length) {
                    claveHash = claveHash - tabla.length;
                }
                if (comparaciones >= tabla.length) {
                    return comparaciones * (-1);
                }
            }
            return (comparaciones + 1) * (-1);
        }
    }

    @Override
    public int insertar(int unaClave) {
        int comparaciones = 0;
        int claveHash0 = funcionHashing(unaClave);
        int claveHash = claveHash0;
        int i = 1;
        boolean salir = false;
        while (!salir) {
            comparaciones++;
            if (tabla[claveHash] == null) {
                tabla[claveHash] = unaClave;
                return comparaciones;
            }
            claveHash = claveHash0 + (i*i);
            i++;
            while (claveHash >= tabla.length) {
                claveHash = claveHash - tabla.length;
            }
            if (comparaciones >= tabla.length) {
                salir = true;
            }
        }
        return comparaciones * (-1);
    }

    @Override
    public int funcionHashing(int unaClave) {
        return unaClave % tabla.length;
    }

}
