/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ut2.pd6;

/**
 *
 * @author Francisco
 */
public class Main {
    
    public static void main(String[] args) {
        TArbolTrieSufijos trie = new TArbolTrieSufijos();
        String[] secuencia = ManejadorArchivosGenerico.leerArchivo("src/ut2/pd6/secuencia.txt");
        for (String s : secuencia) {
            String s1;
            for (int i = 0; i < s.length(); i++) {
                s1 = s.substring(i);
                trie.insertar(s1, i);
            }
        }
        System.out.println(trie.buscar("ccta"));
        System.out.println(trie.buscar("ggtca"));
        System.out.println(trie.buscar("aacct"));
        System.out.println(trie.buscar("taat"));
        System.out.println(trie.buscar("ag"));
    }
}
