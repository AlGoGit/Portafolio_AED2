
import java.util.LinkedList;


public class Main {

    /**
     * @param args
     */
    public static void main(String[] args){
        TArbolTrie trie = new TArbolTrie();

        trie.indizarLibro("src/ut2/pd3/libropru.txt");
        
        trie.imprimir();   
    }
}