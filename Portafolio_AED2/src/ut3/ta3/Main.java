package ut3.ta3;
import java.util.*;

public class Main {

    private static final int REPETICIONES = 100;

    public static void main(String[] args) {
        TArbolTrie trie = new TArbolTrie();
        LinkedList linkedList = new LinkedList();
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        TreeMap treeMap = new TreeMap();
        String[] palabrasclave = ManejadorArchivosGenerico.leerArchivo("src/ut3/ta3/listado-general_desordenado.txt");
        String[] palabrasBuscar = ManejadorArchivosGenerico.leerArchivo("src/ut3/ta3/listado-general_palabrasBuscar.txt");
        for (String p : palabrasclave) {
            // insertar la palabra p en el trie
            trie.insertar(p, 0);
            // insertar la palabra p en el linkedList
            linkedList.add(p);
            // insertar la palabra p en el arrayList
            arrayList.add(p);
            // insertar la palabra p en el hashMap
            hashMap.put(p, p);
            // insertar la palabra p en el treeMap
            treeMap.put(p, p);
        }

        Medible[] medibles = new Medible[5];
        medibles[0] = new MedicionBuscarLinkedList(linkedList);
        medibles[1] = new MedicionBuscarArrayList(arrayList);
        medibles[2] = new MedicionBuscarTArbolTrie(trie);
        medibles[3] = new MedicionBuscarHashMap(hashMap);
        medibles[4] = new MedicionBuscarTreeMap(treeMap);
        Medicion mi;
        int i = 0;
        Object[] params = {REPETICIONES, palabrasBuscar};
        String[] lineas = new String[6];
        lineas[i++] = "algoritmo,tiempo,memoria";
        for (Medible m : medibles) {
            mi = m.medir(params);
            mi.print();
            lineas[i++] = mi.getTexto() + "," + mi.getTiempoEjecucion().toString() + "," + mi.getMemoria().toString();
        }

        ManejadorArchivosGenerico.escribirArchivo("./src/ut3/ta3/salida.csv", lineas);
    }
}
