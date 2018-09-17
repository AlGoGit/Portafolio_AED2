/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ut3.ta4;

/**
 *
 * @author jechague
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int cantidadElementos = 9;
        double factorCarga = 0.99;
        THash hash = new THash(cantidadElementos, factorCarga); 
        
        
        double suma2 = 0;
        double exitos = 0;
        double suma3 = 0;
        double noExitos = 0;
        String[] lineas2 = ManejadorArchivosGenerico.leerArchivo("src/ut3/ta4/claves_insertar.txt");
        for (String linea2 : lineas2) {
            int i = hash.insertar(Integer.parseInt(linea2));
            if (i >= 0) {
                suma2 += i;
                exitos++;
            } else {
                suma3 += i;
                noExitos++;
            }
        }
        System.out.println("Comparaciones promedio inserciones exitosas: " + suma2 / exitos);
        System.out.println("Comparaciones promedio inserciones no exitosas: " + suma3 / noExitos);
        System.out.println("Inserciones exitosas/total elementos para insertar: " + exitos / lineas2.length);

        suma2 = 0;
        exitos = 0;
        suma3 = 0;
        noExitos = 0;
        lineas2 = ManejadorArchivosGenerico.leerArchivo("src/ut3/ta4/claves_buscar.txt");
        for (String linea2 : lineas2) {
            int i = hash.buscar(Integer.parseInt(linea2));
            if (i >= 0) {
                suma2 += i;
                exitos++;
            } else {
                suma3 += i;
                noExitos++;
            }
        }
        System.out.println("Comparaciones promedio busquedas exitosas: " + suma2 / exitos);
        System.out.println("Comparaciones promedio busquedas no exitosas: " + suma3 / noExitos);
    }
    
}
