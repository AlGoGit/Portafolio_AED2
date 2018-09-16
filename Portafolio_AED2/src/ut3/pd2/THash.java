/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ut3.pd2;

public class THash implements IHash {

    private Comparable[] tabla;

    public THash(int cantidadClaves, double factorCarga) {
        tabla = new Comparable[(int)(cantidadClaves/factorCarga)];
    }

    public Comparable[] getTabla() {
        return tabla;
    }

    @Override
    public int buscar(int unaClave) {
        int comparaciones = 0;
        int claveHash = funcionHashing(unaClave);
        if (tabla[claveHash] == null) {
            return -1;
        } else {
            while (tabla[claveHash] != null) {
                comparaciones++;
                if (tabla[claveHash].equals(unaClave)) {
                    return comparaciones;
                }
                claveHash++;
                if (claveHash >= tabla.length) {
                    claveHash = 0;
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
        int claveHash = funcionHashing(unaClave);
        boolean salir = false;
        while (!salir) {
            comparaciones++;
            if (tabla[claveHash] == null) {
                tabla[claveHash] = unaClave;
                return comparaciones;
            }
            claveHash++;
            if (claveHash >= tabla.length) {
                claveHash = 0;
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
