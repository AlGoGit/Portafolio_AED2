package ut3.pd4;
import java.util.HashMap;
import java.util.LinkedList;


public class Main {

    /**
     * @param args
     */
    public static void main(String[] args){
        
        HashMap<String,Integer> indice = new HashMap<>();
        
        String[] palabrasclave = ManejadorArchivosGenerico.leerArchivo("src/ut3/pd4/libro.txt"); 
        for (String p : palabrasclave) { 
            String p1 = p.replaceAll("[^a-zA-Z ]", ""); 
            String[] p2 = p1.split(" "); 
            for (String p3 : p2) { 
                if (indice.containsKey(p3)) {
                    int frec = indice.get(p3);
                    indice.put(p3.toLowerCase(), frec+1);
                } else {
                    indice.put(p3.toLowerCase(), 1); 
                }  
            } 
        } 
        System.out.println(indice.toString());
    }
}