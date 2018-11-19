/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ut6_ut7.clasificacion;

/**
 *
 * @author
 */
public class MainCascara {
    
    public static final int METODO_CLASIFICACION_INSERCION = 1;
    public static final int METODO_CLASIFICACION_SHELL = 2;
    public static final int METODO_CLASIFICACION_BURBUJA = 3;
    public static final int METODO_CLASIFICACION_QUICKSORT = 4;
    public static final int METODO_CLASIFICACION_HEAPSORT = 5;
    public static final int METODO_CLASIFICACION_SELECCION = 6;
    public static final int METODO_CLASIFICACION_BINSORT = 7;
    public static final int METODO_CLASIFICACION_RADIX = 8;

    public static long TIEMPO_RESOLUCION = 1000000000;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int[] tamaños = {300, 10000, 30000};
        String[] metodos = {"INSERCION_DIRECTA", "SHELLSORT", "BURBUJA", "QUICKSORT", "HEAPSORT", "SELECCION_DIRECTA", "BINSORT", "RADIXSORT"};

        for (int i = 0; i < tamaños.length; i++) {
            System.out.println("");
            System.out.println("------------ " + tamaños[i] + " elementos -----------------");
            System.out.println("");
            GeneradorDatosGenericos gdg = new GeneradorDatosGenericos(tamaños[i]);
            TClasificador clasif = new TClasificador();

            int[] vectorOriginalAleatorio = gdg.generarDatosAleatorios();
            int[] vectorOriginalDescendente = gdg.generarDatosDescendentes();
            int[] vectorOriginalAscendente = gdg.generarDatosAscendentes();

            for (int j = 1; j < (metodos.length+1); j++) {
                System.out.println("** " + metodos[j-1] + " **");

                //ALEATORIO
                int[] vectorAuxiliarAleatorio = vectorOriginalAleatorio.clone();
                Long tiempoMedioAlgoritmoBase = calculoDeTiempos(vectorAuxiliarAleatorio, clasif, j, false);
                Long tiempoMedioCascara = calculoDeTiempos(vectorAuxiliarAleatorio, clasif, j, true);
                Long tiempoMedioAlgoritmo = tiempoMedioAlgoritmoBase - tiempoMedioCascara;

                //DESCENDENTE
                int[] vectorAuxiliarDescendente = vectorOriginalDescendente.clone();
                Long tiempoMedioAlgoritmoBaseDescendente = calculoDeTiempos(vectorAuxiliarDescendente, clasif, j, false);
                Long tiempoMedioCascaraDescendente = calculoDeTiempos(vectorAuxiliarDescendente, clasif, j, true);
                Long tiempoMedioAlgoritmoDescendente = tiempoMedioAlgoritmoBaseDescendente - tiempoMedioCascaraDescendente;

                //ASCENDENTE
                int[] vectorAuxiliarAscendente = vectorOriginalAscendente.clone();
                Long tiempoMedioAlgoritmoBaseAscendente = calculoDeTiempos(vectorAuxiliarAscendente, clasif, j, false);
                Long tiempoMedioCascaraAscendente = calculoDeTiempos(vectorAuxiliarAscendente, clasif, j, true);
                Long tiempoMedioAlgoritmoAscendente = tiempoMedioAlgoritmoBaseAscendente - tiempoMedioCascaraAscendente;

                System.out.println("Tiempo medio aleatorio    " + (tiempoMedioAlgoritmo));
                System.out.println("Tiempo medio descendentes " + (tiempoMedioAlgoritmoDescendente));
                System.out.println("Tiempo medio ascendentes  " + (tiempoMedioAlgoritmoAscendente));
            }
        }
    }

    private static Long calculoDeTiempos(int[] vectorOriginal, TClasificador clasificador, int algoritmo, boolean cascara) {
        long t1 = System.nanoTime();
        long total = 0;
        int cantidadLlamadas = 0;

        while (total < TIEMPO_RESOLUCION) {
            cantidadLlamadas += 1;
            int[] datosCopia = copiarVector(vectorOriginal);
            clasificador.clasificar(datosCopia, algoritmo, cascara);
            long t2 = System.nanoTime();
            total = t2 - t1;
        }

        return total / cantidadLlamadas;
    }

    public static int[] copiarVector(int[] aOrigen) {
        int[] aDestino = new int[aOrigen.length];
        for (int x = 0; x < aOrigen.length; x++) {
            aDestino[x] = aOrigen[x];
        }
        return aDestino;
    }
}
