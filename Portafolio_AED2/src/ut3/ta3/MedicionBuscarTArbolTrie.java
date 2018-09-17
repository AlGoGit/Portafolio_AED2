package ut3.ta3;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author usuario
 */
public class MedicionBuscarTArbolTrie extends Medible {

    private TArbolTrie arbolTrie;

    public MedicionBuscarTArbolTrie(TArbolTrie arbolTrie) {
        this.arbolTrie = arbolTrie;
    }
    
    @Override
    public void ejecutar(Object... params) {
        int repeticion = (int) params[0];
        String[] palabras = (String[]) params[1];
        for(int i = 0; i < repeticion; i++){
            for(String palabra : palabras){
                arbolTrie.buscar(palabra);
            }
        }
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        return this.arbolTrie;
    }
    
}
