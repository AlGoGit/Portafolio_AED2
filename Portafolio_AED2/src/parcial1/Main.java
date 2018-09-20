package parcial1;
import java.util.Collection;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        TArbolTrie trie = new TArbolTrie();

        // CARGAR EN EL TRIE LOS DISPOSITIVOS PARTIR DEL ARCHIVO DISPOSITIVOS.TXT
        
        String[] lineas = ManejadorArchivosGenerico.leerArchivo("src/dispositivos.txt");
        for (String linea : lineas) {
            String[] partes = linea.split(",");
            TDispositivo dis = new TDispositivo(partes[0], partes[1]);
            trie.insertar(dis);
        }
        
        String subRed1 = "141."; // utilizar el indicado en el archivo "subredes.txt"
        String subRed2 = "005.116.";

        Collection<TDispositivo> dispositivos1 = trie.buscarDispositivos(subRed1);
        Collection<TDispositivo> dispositivos2 = trie.buscarDispositivos(subRed2);

        // crear el archivo "salida.txt", con los dispositivos(1 por linea) 
        // correspondientes a la subred indicada
        // imprimir Nombre y dirección de  IP, 
        
        String[] aEscribir = new String[dispositivos1.size() + dispositivos2.size()];
        int i = 0;
        for (TDispositivo disp : dispositivos1) {
            aEscribir[i] = disp.toString();
            i++;
        }
        for (TDispositivo disp : dispositivos2) {
            aEscribir[i] = disp.toString();
            i++;
        }
        
        ManejadorArchivosGenerico.escribirArchivo("./src/salida.txt", aEscribir);
        
    }
}
