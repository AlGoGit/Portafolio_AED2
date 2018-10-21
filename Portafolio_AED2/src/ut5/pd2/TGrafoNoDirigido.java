package ut5.pd2;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;

public class TGrafoNoDirigido extends TGrafoDirigido implements IGrafoNoDirigido {

    protected TAristas lasAristas = new TAristas();

    /**
     *
     * @param vertices
     * @param aristas
     */
    public TGrafoNoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
        super(vertices, aristas);
        lasAristas.insertarAmbosSentidos(aristas);

    }

    @Override
    public boolean insertarArista(TArista arista) {
        boolean tempbool = false;
        TArista arInv = new TArista(arista.getEtiquetaDestino(), arista.getEtiquetaOrigen(), arista.getCosto());
        tempbool = (super.insertarArista(arista) && super.insertarArista(arInv));
        return tempbool;
    }

    public TAristas getLasAristas() {
        return lasAristas;
    }

    @Override
    public TGrafoNoDirigido Prim() {
        LinkedList<TArista> aristasAAM = new LinkedList<>();
        LinkedList<TVertice> verticesU = new LinkedList<>();
        LinkedList<TVertice> verticesV = new LinkedList<>();
        TArista tempArista;
        for (TVertice aux : vertices.values()) {
            verticesV.add(aux);
        }
        while (!verticesV.isEmpty()) {
            verticesU.add(verticesV.removeFirst());
            tempArista = lasAristas.buscarMin(verticesU, verticesV);
            if (tempArista != null) {
                aristasAAM.add(tempArista);
            }
        }
        return new TGrafoNoDirigido(verticesU, aristasAAM);
    }
    
    @Override
    public TGrafoNoDirigido Kruskal() {
        TAristas aristasAAM = new TAristas();
        TAristas aristasSinElegir = new TAristas();
        HashSet<TVertice> verticesElegidos = new HashSet<>();
        HashSet<TVertice> verticesSinElegir = new HashSet<>();
        
        for (TVertice aux : vertices.values()) {
            verticesSinElegir.add(aux);
        }
        for (TArista aux : lasAristas) {
            aristasSinElegir.add(aux);
        }
        
        while (!verticesSinElegir.isEmpty()) {
            TArista tempArista = aristasSinElegir.buscarMin();
            if (tempArista != null) {
                TVertice tempOrigen = vertices.get(tempArista.getEtiquetaOrigen());
                TVertice tempDestino = vertices.get(tempArista.getEtiquetaDestino());
                
                if (!(verticesElegidos.contains(tempOrigen) && verticesElegidos.contains(tempDestino))) {
                    verticesElegidos.add(tempOrigen);
                    verticesElegidos.add(tempDestino);
                    verticesSinElegir.remove(tempOrigen);
                    verticesSinElegir.remove(tempDestino);
                    aristasAAM.add(tempArista);
                }
                aristasSinElegir.remove(tempArista);
            }
        }
        return new TGrafoNoDirigido(verticesElegidos, aristasAAM);
    }
}

