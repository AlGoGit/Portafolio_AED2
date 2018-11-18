package ut6_ut7.clasificacion;

import java.util.Arrays;
import java.util.Random;

public class TClasificador {

    public static final int METODO_CLASIFICACION_INSERCION = 1;
    public static final int METODO_CLASIFICACION_SHELL = 2;
    public static final int METODO_CLASIFICACION_BURBUJA = 3;
    public static final int METODO_CLASIFICACION_QUICKSORT = 4;
    public static final int METODO_CLASIFICACION_HEAPSORT = 5;
    public static final int METODO_CLASIFICACION_SELECCION = 6;
    public static final int METODO_CLASIFICACION_BINSORT = 7;
    public static final int METODO_CLASIFICACION_RADIX = 8;

    /**
     * Punto de entrada al clasificador
     *
     * @param metodoClasificacion
     * @param orden
     * @param tamanioVector
     * @return Un vector del tam. solicitado, ordenado por el algoritmo
     * solicitado
     */
    public int[] clasificar(int[] datosParaClasificar, int metodoClasificacion, boolean cascara) {
        if (!cascara) {
            switch (metodoClasificacion) {
                case METODO_CLASIFICACION_INSERCION:
                    return ordenarPorInsercion(datosParaClasificar);
                case METODO_CLASIFICACION_SHELL:
                    return ordenarPorShell(datosParaClasificar);
                case METODO_CLASIFICACION_BURBUJA:
                    return ordenarPorBurbuja(datosParaClasificar);
                case METODO_CLASIFICACION_QUICKSORT:
                    return ordenarPorQuickSort(datosParaClasificar);
                case METODO_CLASIFICACION_HEAPSORT:
                    return ordenarPorHeapSort(datosParaClasificar);
                case METODO_CLASIFICACION_SELECCION:
                    return ordenarPorSeleccion(datosParaClasificar);
                default:
                    System.err.println("Este codigo no deberia haberse ejecutado");
                    break;
            }
        } else {
            usarCascara(datosParaClasificar);
        }
        return datosParaClasificar;
    }

    protected int[] usarCascara(int[] datosParaClasificar) {
        if (datosParaClasificar != null) {
            return datosParaClasificar;
        }
        return null;
    }

    private boolean estaOrdenado(int[] vector) {
        for (int i = 0; i < vector.length - 1; i++) {
            if (vector[i] > vector[i + 1]) {
                return false;
            }
        }
        return true;
    }

    private void intercambiar(int[] vector, int pos1, int pos2) {
        int temp = vector[pos2];
        vector[pos2] = vector[pos1];
        vector[pos1] = temp;
    }

    /**
     * @param datosParaClasificar
     * @return
     */
    private int[] ordenarPorShell(int[] datosParaClasificar) {
        int j, inc;
        int[] incrementos = new int[]{3223, 358, 51, 10, 3, 1};

        for (int posIncrementoActual = 0; posIncrementoActual < incrementos.length; posIncrementoActual++) {
            inc = incrementos[posIncrementoActual];
            if (inc < (datosParaClasificar.length / 2)) {
                for (int i = inc; i < datosParaClasificar.length; i++) {
                    j = i - inc;
                    while (j >= 0) {
                        if (datosParaClasificar[j] > datosParaClasificar[j + inc]) {
                            intercambiar(datosParaClasificar, j, j + inc);
                        }
                        j--;
                    }
                }
            }
        }
        return datosParaClasificar;
    }

    /**
     * @param datosParaClasificar
     * @return
     */
    protected int[] ordenarPorInsercion(int[] datosParaClasificar) {
        if (datosParaClasificar != null) {
            for (int i = 1; i < datosParaClasificar.length; i++) {
                int j = i - 1;
                while ((j >= 0) && (datosParaClasificar[j + 1] < datosParaClasificar[j])) {
                    intercambiar(datosParaClasificar, j, j + 1);
                    j--;
                }
            }
            return datosParaClasificar;
        }
        return null;
    }

    private int[] ordenarPorBurbuja(int[] datosParaClasificar) {
        int n = datosParaClasificar.length - 1;
        for (int i = 0; i <= n; i++) {
            for (int j = n; j >= (i + 1); j--) {
                if (datosParaClasificar[j] < datosParaClasificar[j - 1]) {
                    intercambiar(datosParaClasificar, j - 1, j);
                }
            }
        }
        return datosParaClasificar;
    }

    protected int[] ordenarPorQuickSort(int[] datosParaClasificar) {
        quicksort(datosParaClasificar, 0, datosParaClasificar.length - 1, datosParaClasificar.length, 0);
        return datosParaClasificar;
    }

    private int[] quicksort(int[] entrada, int i, int j, int n, int llamadas) {
//        if (llamadas < 10 * (Math.log(n) / Math.log(2))) {
        int izquierda = i;
        int derecha = j;
        int posicionPivote = encuentraPivote(izquierda, derecha);
        if (posicionPivote >= 0) {
            int pivote = entrada[posicionPivote];
            while (izquierda < derecha) {
                while ((entrada[izquierda] < pivote) && (izquierda < j)) {
                    izquierda++;
                }
                while ((entrada[derecha] > pivote) && (derecha > i)) {
                    derecha--;
                }
                if (izquierda <= derecha) {
                    intercambiar(entrada, derecha, izquierda);
                    izquierda++;
                    derecha--;
                }
            }
            if (i < derecha) {
                quicksort(entrada, i, derecha, n, llamadas + 1);
            }
            if (izquierda < j) {
                quicksort(entrada, izquierda, j, n, llamadas + 1);
            }
        }
//        }
        return entrada;
    }

    protected int[] ordenarPorHeapSort(int[] datosParaClasificar) {
        for (int i = (datosParaClasificar.length - 1) / 2; i >= 0; i--) { //Armo el heap inicial de n-1 div 2 hasta 0
            armaHeap(datosParaClasificar, i, datosParaClasificar.length - 1);
        }
        for (int i = datosParaClasificar.length - 1; i >= 1; i--) { // mayor o igual
            intercambiar(datosParaClasificar, 0, i);
            armaHeap(datosParaClasificar, 0, i - 1);
        }
        return datosParaClasificar;
    }

    private void armaHeap(int[] datosParaClasificar, int primero, int ultimo) {
        if (primero < ultimo) {
            int r = primero;
            while (r <= ultimo / 2) {
                if (ultimo == 2 * r) { //r tiene un hijo solo
                    if (datosParaClasificar[r] < datosParaClasificar[r * 2]) {
                        intercambiar(datosParaClasificar, r, 2 * r);
                    }
                    r = ultimo; // afuera del else
                } else { //r tiene 2 hijos
                    int posicionIntercambio = 0;
                    if (datosParaClasificar[2 * r] < datosParaClasificar[2 * r + 1]) {
                        posicionIntercambio = 2 * r + 1;
                    } else {
                        posicionIntercambio = 2 * r;
                    }
                    if (datosParaClasificar[r] < datosParaClasificar[posicionIntercambio]) { // mayor
                        intercambiar(datosParaClasificar, r, posicionIntercambio);
                        r = posicionIntercambio;
                    } else {
                        r = ultimo;
                    }
                }
            }
        }
    }

    protected int[] ordenarPorSeleccion(int[] datosParaClasificar) {
        for (int i = 0; i < datosParaClasificar.length - 1; i++) {
            int indiceMenor = i;
            int claveMenor = datosParaClasificar[i];
            for (int j = i + 1; j < datosParaClasificar.length; j++) {
                if (datosParaClasificar[j] < claveMenor) {
                    indiceMenor = j;
                    claveMenor = datosParaClasificar[j];
                }
            }
            intercambiar(datosParaClasificar, i, indiceMenor);
        }
        return datosParaClasificar;
    }

    protected int[] ordenarPorCuenta(int[] datosParaClasificar, int maximo) {
        int[] cuenta = new int[maximo + 1];
        for (int i = 0; i < datosParaClasificar.length; i++) {
            cuenta[datosParaClasificar[i]]++;
        }
        for (int i = 1; i < maximo + 1; i++) {
            cuenta[i] += cuenta[i - 1];
        }
        int[] salida = new int[datosParaClasificar.length];
        for (int i = datosParaClasificar.length - 1; i >= 0; i--) {
            int j = cuenta[datosParaClasificar[i]] - 1;
            salida[j] = datosParaClasificar[i];
            cuenta[datosParaClasificar[i]]--;
        }
        return salida;
    }

    private int encuentraPivote(int izq, int der) {
        if (izq == der) {
            return -1;
        }
        return ((izq + der) / 2);
    }

    private int encuentraPivote1(int izq, int der) {
        Random rand = new Random();
        int randomNum = rand.nextInt((der - izq)) + izq;
        return randomNum;
    }

    public static void main(String args[]) {

        TClasificador clasif = new TClasificador();
        MedicionTiempo medidaTiempo = new MedicionTiempo();
        GeneradorDatosGenericos gdg1 = new GeneradorDatosGenericos(300);
        GeneradorDatosGenericos gdg2 = new GeneradorDatosGenericos(10000);
        GeneradorDatosGenericos gdg3 = new GeneradorDatosGenericos(30000);
        GeneradorDatosGenericos gdg4 = new GeneradorDatosGenericos(20);

//        System.out.println("");
//        System.out.println("**INSERCION DIRECTA**");
//        System.out.println("Inserción 300 aleatorio:   " + (medidaTiempo.medir(gdg1.generarDatosAleatorios(), METODO_CLASIFICACION_INSERCION)));
//        System.out.println("Inserción 300 ascendente:  " + (medidaTiempo.medir(gdg1.generarDatosAscendentes(), METODO_CLASIFICACION_INSERCION)));
//        System.out.println("Inserción 300 descendente: " + (medidaTiempo.medir(gdg1.generarDatosDescendentes(), METODO_CLASIFICACION_INSERCION)));
//
//        System.out.println("");
//        System.out.println("Inserción 10.000 aleatorio:   " + (medidaTiempo.medir(gdg2.generarDatosAleatorios(), METODO_CLASIFICACION_INSERCION)));
//        System.out.println("Inserción 10.000 ascendente:  " + (medidaTiempo.medir(gdg2.generarDatosAscendentes(), METODO_CLASIFICACION_INSERCION)));
//        System.out.println("Inserción 10.000 descendente: " + (medidaTiempo.medir(gdg2.generarDatosDescendentes(), METODO_CLASIFICACION_INSERCION)));
//
//        System.out.println("");
//        System.out.println("Inserción 30.000 aleatorio:   " + (medidaTiempo.medir(gdg3.generarDatosAleatorios(), METODO_CLASIFICACION_INSERCION)));
//        System.out.println("Inserción 30.000 ascendente:  " + (medidaTiempo.medir(gdg3.generarDatosAscendentes(), METODO_CLASIFICACION_INSERCION)));
//        System.out.println("Inserción 30.000 descendente: " + (medidaTiempo.medir(gdg3.generarDatosDescendentes(), METODO_CLASIFICACION_INSERCION)));
//
//        System.out.println("");
//        System.out.println("**BUBBLESORT**");
//        System.out.println("Inserción 300 aleatorio:   " + (medidaTiempo.medir(gdg1.generarDatosAleatorios(), METODO_CLASIFICACION_BURBUJA)));
//        System.out.println("Inserción 300 ascendente:  " + (medidaTiempo.medir(gdg1.generarDatosAscendentes(), METODO_CLASIFICACION_BURBUJA)));
//        System.out.println("Inserción 300 descendente: " + (medidaTiempo.medir(gdg1.generarDatosDescendentes(), METODO_CLASIFICACION_BURBUJA)));
//
//        System.out.println("");
//        System.out.println("Inserción 10.000 aleatorio:   " + (medidaTiempo.medir(gdg2.generarDatosAleatorios(), METODO_CLASIFICACION_BURBUJA)));
//        System.out.println("Inserción 10.000 ascendente:  " + (medidaTiempo.medir(gdg2.generarDatosAscendentes(), METODO_CLASIFICACION_BURBUJA)));
//        System.out.println("Inserción 10.000 descendente: " + (medidaTiempo.medir(gdg2.generarDatosDescendentes(), METODO_CLASIFICACION_BURBUJA)));
//
//        System.out.println("");
//        System.out.println("Inserción 30.000 aleatorio:   " + (medidaTiempo.medir(gdg3.generarDatosAleatorios(), METODO_CLASIFICACION_BURBUJA)));
//        System.out.println("Inserción 30.000 ascendente:  " + (medidaTiempo.medir(gdg3.generarDatosAscendentes(), METODO_CLASIFICACION_BURBUJA)));
//        System.out.println("Inserción 30.000 descendente: " + (medidaTiempo.medir(gdg3.generarDatosDescendentes(), METODO_CLASIFICACION_BURBUJA)));
//
//        System.out.println("");
//        System.out.println("**QUICKSORT**");
//        System.out.println("Inserción 300 aleatorio:   " + (medidaTiempo.medir(gdg1.generarDatosAleatorios(), METODO_CLASIFICACION_QUICKSORT)));
//        System.out.println("Inserción 300 ascendente:  " + (medidaTiempo.medir(gdg1.generarDatosAscendentes(), METODO_CLASIFICACION_QUICKSORT)));
//        System.out.println("Inserción 300 descendente: " + (medidaTiempo.medir(gdg1.generarDatosDescendentes(), METODO_CLASIFICACION_QUICKSORT)));
//
//        System.out.println("");
//        System.out.println("Inserción 10.000 aleatorio:   " + (medidaTiempo.medir(gdg2.generarDatosAleatorios(), METODO_CLASIFICACION_QUICKSORT)));
//        System.out.println("Inserción 10.000 ascendente:  " + (medidaTiempo.medir(gdg2.generarDatosAscendentes(), METODO_CLASIFICACION_QUICKSORT)));
//        System.out.println("Inserción 10.000 descendente: " + (medidaTiempo.medir(gdg2.generarDatosDescendentes(), METODO_CLASIFICACION_QUICKSORT)));
//        
//        System.out.println("");
//        System.out.println("Inserción 30.000 aleatorio:   " + (medidaTiempo.medir(gdg3.generarDatosAleatorios(), METODO_CLASIFICACION_QUICKSORT)));
//        System.out.println("Inserción 30.000 ascendente:  " + (medidaTiempo.medir(gdg3.generarDatosAscendentes(), METODO_CLASIFICACION_QUICKSORT)));
//        System.out.println("Inserción 30.000 descendente: " + (medidaTiempo.medir(gdg3.generarDatosDescendentes(), METODO_CLASIFICACION_QUICKSORT)));
//
//        System.out.println("");
//        System.out.println("**SHELLSORT**");
//        System.out.println("Inserción 300 aleatorio:   " + (medidaTiempo.medir(gdg1.generarDatosAleatorios(), METODO_CLASIFICACION_SHELL)));
//        System.out.println("Inserción 300 ascendente:  " + (medidaTiempo.medir(gdg1.generarDatosAscendentes(), METODO_CLASIFICACION_SHELL)));
//        System.out.println("Inserción 300 descendente: " + (medidaTiempo.medir(gdg1.generarDatosDescendentes(), METODO_CLASIFICACION_SHELL)));
//
//        System.out.println("");
//        System.out.println("Inserción 10.000 aleatorio:   " + (medidaTiempo.medir(gdg2.generarDatosAleatorios(), METODO_CLASIFICACION_SHELL)));
//        System.out.println("Inserción 10.000 ascendente:  " + (medidaTiempo.medir(gdg2.generarDatosAscendentes(), METODO_CLASIFICACION_SHELL)));
//        System.out.println("Inserción 10.000 descendente: " + (medidaTiempo.medir(gdg2.generarDatosDescendentes(), METODO_CLASIFICACION_SHELL)));
//
//        System.out.println("");
//        System.out.println("Inserción 30.000 aleatorio:   " + (medidaTiempo.medir(gdg3.generarDatosAleatorios(), METODO_CLASIFICACION_SHELL)));
//        System.out.println("Inserción 30.000 ascendente:  " + (medidaTiempo.medir(gdg3.generarDatosAscendentes(), METODO_CLASIFICACION_SHELL)));
//        System.out.println("Inserción 30.000 descendente: " + (medidaTiempo.medir(gdg3.generarDatosDescendentes(), METODO_CLASIFICACION_SHELL)));
//        
//        System.out.println("");
//        System.out.println("**HEAPSORT**");
//        System.out.println("Inserción 300 aleatorio:   " + (medidaTiempo.medir(gdg1.generarDatosAleatorios(), METODO_CLASIFICACION_HEAPSORT)));
//        System.out.println("Inserción 300 ascendente:  " + (medidaTiempo.medir(gdg1.generarDatosAscendentes(), METODO_CLASIFICACION_HEAPSORT)));
//        System.out.println("Inserción 300 descendente: " + (medidaTiempo.medir(gdg1.generarDatosDescendentes(), METODO_CLASIFICACION_HEAPSORT)));
//
//        System.out.println("");
//        System.out.println("Inserción 10.000 aleatorio:   " + (medidaTiempo.medir(gdg2.generarDatosAleatorios(), METODO_CLASIFICACION_HEAPSORT)));
//        System.out.println("Inserción 10.000 ascendente:  " + (medidaTiempo.medir(gdg2.generarDatosAscendentes(), METODO_CLASIFICACION_HEAPSORT)));
//        System.out.println("Inserción 10.000 descendente: " + (medidaTiempo.medir(gdg2.generarDatosDescendentes(), METODO_CLASIFICACION_HEAPSORT)));
//
//        System.out.println("");
//        System.out.println("Inserción 30.000 aleatorio:   " + (medidaTiempo.medir(gdg3.generarDatosAleatorios(), METODO_CLASIFICACION_HEAPSORT)));
//        System.out.println("Inserción 30.000 ascendente:  " + (medidaTiempo.medir(gdg3.generarDatosAscendentes(), METODO_CLASIFICACION_HEAPSORT)));
//        System.out.println("Inserción 30.000 descendente: " + (medidaTiempo.medir(gdg3.generarDatosDescendentes(), METODO_CLASIFICACION_HEAPSORT)));
//        
//        System.out.println("");
//        System.out.println("**SELECCION**");
//        System.out.println("Inserción 300 aleatorio:   " + (medidaTiempo.medir(gdg1.generarDatosAleatorios(), METODO_CLASIFICACION_SELECCION)));
//        System.out.println("Inserción 300 ascendente:  " + (medidaTiempo.medir(gdg1.generarDatosAscendentes(), METODO_CLASIFICACION_SELECCION)));
//        System.out.println("Inserción 300 descendente: " + (medidaTiempo.medir(gdg1.generarDatosDescendentes(), METODO_CLASIFICACION_SELECCION)));
//
//        System.out.println("");
//        System.out.println("Inserción 10.000 aleatorio:   " + (medidaTiempo.medir(gdg2.generarDatosAleatorios(), METODO_CLASIFICACION_SELECCION)));
//        System.out.println("Inserción 10.000 ascendente:  " + (medidaTiempo.medir(gdg2.generarDatosAscendentes(), METODO_CLASIFICACION_SELECCION)));
//        System.out.println("Inserción 10.000 descendente: " + (medidaTiempo.medir(gdg2.generarDatosDescendentes(), METODO_CLASIFICACION_SELECCION)));
//        
//        System.out.println("");
//        System.out.println("Inserción 30.000 aleatorio:   " + (medidaTiempo.medir(gdg3.generarDatosAleatorios(), METODO_CLASIFICACION_SELECCION)));
//        System.out.println("Inserción 30.000 ascendente:  " + (medidaTiempo.medir(gdg3.generarDatosAscendentes(), METODO_CLASIFICACION_SELECCION)));
//        System.out.println("Inserción 30.000 descendente: " + (medidaTiempo.medir(gdg3.generarDatosDescendentes(), METODO_CLASIFICACION_SELECCION)));
        
        System.out.println(Arrays.toString(clasif.clasificar(gdg4.generarDatosAleatorios(), METODO_CLASIFICACION_INSERCION, false)));
        System.out.println(Arrays.toString(clasif.clasificar(gdg4.generarDatosAleatorios(), METODO_CLASIFICACION_SHELL, false)));
        System.out.println(Arrays.toString(clasif.clasificar(gdg4.generarDatosAleatorios(), METODO_CLASIFICACION_BURBUJA, false)));
        System.out.println(Arrays.toString(clasif.clasificar(gdg4.generarDatosAleatorios(), METODO_CLASIFICACION_QUICKSORT, false)));
        System.out.println(Arrays.toString(clasif.clasificar(gdg4.generarDatosAleatorios(), METODO_CLASIFICACION_HEAPSORT, false)));
        System.out.println(Arrays.toString(clasif.clasificar(gdg4.generarDatosAleatorios(), METODO_CLASIFICACION_SELECCION, false)));
        System.out.println(Arrays.toString(clasif.ordenarPorCuenta(new int[]{1, 3, 2, 1, 4, 5, 4, 4}, 5)));
//        System.out.println(Arrays.toString(clasif.clasificar(gdg4.generarDatosAleatorios(), METODO_CLASIFICACION_BINSORT, false)));
//        System.out.println(Arrays.toString(clasif.clasificar(gdg4.generarDatosAleatorios(), METODO_CLASIFICACION_RADIX, false)));
    }
}
