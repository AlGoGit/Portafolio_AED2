package ut4.pd5;

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
        TGrafoDirigido gd = (TGrafoDirigido) UtilGrafos.cargarGrafo("./src/ut4/pd5/aeropuertos2.txt","./src/ut4/pd5/conexiones2.txt", false, TGrafoDirigido.class);
        
//        UtilGrafos.imprimirMatrizMejorado(gd.floyd(), gd.getVertices(), "Floyd");
//        
//        for (Comparable key : gd.getVertices().keySet()) {
//            System.out.println(gd.obtenerExcentricidad(key));
//        }
//        System.out.println(gd.centroDelGrafo());
//        
//        UtilGrafos.imprimirMatrizMejorado(UtilGrafos.obtenerMatrizCostos(gd.getVertices()), gd.getVertices(), "");
//        
//        boolean[][] mwarshall = gd.warshall();
//        Comparable[][] mwarshall2 = new Comparable[mwarshall.length][mwarshall.length];
//        for (int i = 0; i < mwarshall.length; i++) {
//            for (int j = 0; j < mwarshall.length; j++) {
//                if (mwarshall[i][j]) {
//                    mwarshall2[i][j] = 1d;
//                } else {
//                    mwarshall2[i][j] = 0d;
//                }
//            }
//        }
//        
//        UtilGrafos.imprimirMatrizMejorado(mwarshall2, gd.getVertices(), "Warshall");
//        
//        System.out.println(gd.bpf("Montevideo").toString());
//        
//        TCaminos caminos = gd.todosLosCaminos("Montevideo", "Rio_de_Janeiro");
//        caminos.imprimirCaminosConsola();

        System.out.println(gd.esConexo());
        System.out.println(gd.clasificacionTopologica("Asuncion").toString());
    }
}
