package ut4.pd2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Francisco
 */
public class PruebaGrafo {
    public static void main(String[] args) {
        TGrafoDirigido gd = (TGrafoDirigido) UtilGrafos.cargarGrafo("./src/ut4/pd2/vertices.txt","./src/ut4/pd2/aristas.txt", false, TGrafoDirigido.class);
        
        UtilGrafos.imprimirMatrizMejorado(gd.floyd(), gd.getVertices(), "Floyd");
        
        for (Comparable key : gd.getVertices().keySet()) {
            System.out.println(gd.obtenerExcentricidad(key));
        }
        System.out.println(gd.centroDelGrafo());
    }
}
