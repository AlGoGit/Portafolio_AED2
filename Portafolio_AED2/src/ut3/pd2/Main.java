/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ut3.pd2;

/**
 *
 * @author jechague
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String[] lineas = ManejadorArchivosGenerico.leerArchivo("src/ut3/pd2/claves_insertar.txt");
        //String[] lineas = ManejadorArchivosGenerico.leerArchivo("src/ut3/pd2/claves_insertar_prueba.txt");
        
        double factorCarga = 0.9;
        THash hash = new THash(lineas.length, factorCarga);

        double suma = 0;
        for (String linea : lineas) {
            suma += hash.insertar(Integer.parseInt(linea));
        }
        System.out.println("Comparaciones promedio insercion: " + suma / lineas.length);

        double suma2 = 0;
        double exitos = 0;
        double suma3 = 0;
        double noExitos = 0;
        String[] lineas2 = ManejadorArchivosGenerico.leerArchivo("src/ut3/pd2/claves_buscar.txt");
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

//        for (String linea : lineas) {
//            System.out.println(hash.insertar(Integer.parseInt(linea)));
//        }
//
//        String[] lineas2 = ManejadorArchivosGenerico.leerArchivo("src/ut3/pd2/claves_buscar_prueba.txt");
//        for (String linea2 : lineas2) {
//            System.out.println(hash.buscar(Integer.parseInt(linea2)));
//        }      
    }

}
