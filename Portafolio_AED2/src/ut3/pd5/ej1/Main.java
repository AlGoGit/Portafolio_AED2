package ut3.pd5.ej1;
import java.util.LinkedList;


public class Main {

    /**
     * @param args
     */
    public static void main(String[] args){
        TArbolTrie trie = new TArbolTrie();

        trie.indizarLibro("src/ut3/pd5/ej1/libro.txt");
        
        System.out.println(trie.buscar("surprised"));
        System.out.println(trie.buscar("abductor"));
        System.out.println(trie.buscar("than"));
        System.out.println(trie.buscar("abcacb"));
        System.out.println(trie.buscar("abunubn"));
    }
}