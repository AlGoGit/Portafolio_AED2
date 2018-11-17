package parcial2.parcial;


import java.util.Collection;
import java.util.LinkedList;

public class Programa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String[] lineasActores = ManejadorArchivosGenerico.leerArchivo("src/actores.txt", false);
        Collection<TVertice> actores = new LinkedList<>();
        for (String actor : lineasActores) {
            actores.add(new TVertice(actor));
        }

        String[] lineasConexiones = ManejadorArchivosGenerico.leerArchivo("src/en_pelicula.txt", false);
        Collection<TArista> conexiones = new LinkedList<>();
        for (String conexion : lineasConexiones) {
            String[] partes = conexion.split(",");
            if (partes.length == 3) {
                conexiones.add(new TArista(partes[0], partes[1], Double.parseDouble(partes[2])));
            }
        }

        TGrafoNoDirigido gkb = new TGrafoNoDirigido(actores, conexiones);

        String actorZZ1 = "Kevin_Bacon"; // se indicará en el pizarrón
        Collection<TVertice> contactos1 = gkb.listarContactos(actorZZ1, 1);
        String[] lineas1 = new String[contactos1.size()];
        int i = 0;
        for (TVertice actor : contactos1) {
            lineas1[i] = actor.getEtiqueta().toString();
            i++;
        }
        ManejadorArchivosGenerico.escribirArchivo("src/salida1.txt", lineas1);

        String actorZZ2 = "David_Cross"; // se indicará en el pizarrón
        Collection<TVertice> contactos2 = gkb.listarContactos(actorZZ2, 5);
        String[] lineas2 = new String[contactos2.size()];
        i = 0;
        for (TVertice actor : contactos2) {
            lineas2[i] = actor.getEtiqueta().toString();
            i++;
        }
        ManejadorArchivosGenerico.escribirArchivo("src/salida2.txt", lineas2);
    }
}
