package ut5.pd1;


import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
