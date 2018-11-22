package parcial3.parcial;


import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Programa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Instanciar productos del ecommerce leyendo del archivo.
        String[] lineas = ManejadorArchivosGenerico.leerArchivo("src/flipkart-ecommerce-sample.csv", true);
        int i = 0;
        ArrayList<TProduct> productos = new ArrayList<>();
        for(String linea : lineas) {
            try {
                TProduct producto = new TProduct(linea);
                if (producto.isValid()) {
                    i += 1;
                    // Agregar el producto a una colección del tipo apropiado.
                    productos.add(producto);             
                }
            } catch (Exception ex) {
                Logger.getLogger(Programa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Se cargaron " + i + " productos");
        
        TClasificador clasificador = new TClasificador();
        ArrayList<TProduct> productosOrdenados = clasificador.ordenarPorQuickSort(productos);
        
        String[] lineasAEscribir = new String[productosOrdenados.size()];
        int j = 0;
        for (TProduct product : productosOrdenados) {
            lineasAEscribir[j] = product.toString();
            j++;
        }
        ManejadorArchivosGenerico.escribirArchivo("src/productos_ordenados.txt", lineasAEscribir);
    }
}
