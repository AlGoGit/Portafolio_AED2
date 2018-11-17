/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ut6_ut7.clasificacion;

/**
 *
 * @author usuario
 */
public class MedicionTiempo {
    /**
     * Constante de tipo long utilizada para indicar el tiempo de resolución  
     */
    public long  TIEMPO_RESOLUCION = 1000000000;
    /**
     * Objeto componente del tipo TClasificador
     */
    public TClasificador clasificador = new TClasificador();
    /**
     * Método que mide el tiempo de ejecución de un algoritmo
     * @param vectorOriginal        array de elementos enteros
     * @param metodoClasificacion   elemento entero que indicará el método de clasificación a ser ejecutado
     * @return                      tiempo de ejecución del algoritmo
     */
    public long medir(int[]vectorOriginal, int metodoClasificacion){
        long t1 = System.nanoTime();
        long total = 0l;
        int cantLlamadas = 0;
        while (total < TIEMPO_RESOLUCION) {  
            cantLlamadas += 1;
            int[] datosCopia = copiarVector(vectorOriginal);
            clasificador.clasificar(datosCopia, metodoClasificacion, false);
            long t2 = System.nanoTime();
            total = t2-t1;
            
        }
        long tiempoMedioAlgoritmoBase = total/cantLlamadas;        
        
        long t3 = System.nanoTime();
        long total2 = 0l;
        int cantLlamadas2 = 0;
        while (total2 < TIEMPO_RESOLUCION) {  
            cantLlamadas2 += 1;
            int[] datosCopia = copiarVector(vectorOriginal);
            clasificador.clasificar(datosCopia, metodoClasificacion, true);
            long t4 = System.nanoTime();
            total2 = t4-t3;
            
        }
        long tiempoMedioCascara = total2/cantLlamadas2;        
        
        //devuelve el Tiempo Medio del Algoritmo
        return tiempoMedioAlgoritmoBase-tiempoMedioCascara;
    }    
    
    public static int[] copiarVector(int[] aOrigen) {
        int[] aDestino = new int[aOrigen.length];
        for (int x = 0; x < aOrigen.length; x++) {
            aDestino[x] = aOrigen[x];
        }
        return aDestino;
    }
}
