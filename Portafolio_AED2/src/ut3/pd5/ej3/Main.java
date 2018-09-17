package ut3.pd5.ej3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        TArbolTrie trie = new TArbolTrie();

        // CARGAR EN EL TRIE LOS TELÉFONOS Y NOMBRES A PARTIR DEL ARCHIVO ABONADOS.TXT
        String[] abonados = ManejadorArchivosGenerico.leerArchivo("src/ut3/pd5/ej3/abonados.txt");
        for (String abonado : abonados) {
            String[] datos = abonado.split(",");
            trie.insertar(new TAbonado(datos[1], datos[0]));
        }

        String codigoPais = "054"; // utilizar el indicado en el archivo "codigos.txt"
        String codigoArea = "90";// utilizar el indicado en el archivo "codigos.txt"
        TreeSet<TAbonado> ab = trie.buscarTelefonos(codigoPais, codigoArea);

        // crear el archivo "salida.txt", con los abonados (1 por linea) 
        // correspondientes al pais y area 
        // imprimir Nombre y teléfono, 
        // ordenados alfabeticamente por nombre
        
        String[] buscados = new String[ab.size()];
        int i = 0;
        for (TAbonado buscado : ab) {
            buscados[i] = buscado.toString();
            i++;
        }
        
        ManejadorArchivosGenerico.escribirArchivo("./src/ut3/pd5/ej3/salida.txt", buscados);
        
    }
}
