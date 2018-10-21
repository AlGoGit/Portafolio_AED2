package ut5.pd1;

import java.util.Collection;

import java.util.LinkedList;

public class TAristas extends LinkedList<TArista> {

    private final static String SEPARADOR_ELEMENTOS_IMPRESOS = "-";

    /**
     * Busca dentro de la lista de aristas una arista que conecte a etOrigen con
     * etDestino
     *
     * @param etOrigen
     * @param etDestino
     * @return
     */
    public TArista buscar(Comparable etOrigen, Comparable etDestino) {
        for (TArista laa : this) {
            if ((laa.getEtiquetaOrigen().equals(etOrigen)) && laa.getEtiquetaDestino().equals(etDestino)) {
                return laa;
            }
        }
        return null;
    }

    /**
     * Busca la arista de menor costo que conecte a cualquier nodo de VerticesU
     * con cualquier otro de VerticesV y cuyo costo sea el minimo
     *
     * @param VerticesU - Lista de vertices U
     * @param VerticesV - Lista de vertices V
     * @return
     */
    public TArista buscarMin(Collection<TVertice> VerticesU, Collection<TVertice> VerticesV) {
        TArista tempArista = null;
        TArista tAMin = null;
        Double costoMin = Double.POSITIVE_INFINITY;
        for (TVertice u : VerticesU) {
            for (TVertice v : VerticesV) {
                tempArista = this.buscar(u.getEtiqueta(), v.getEtiqueta());
                if (tempArista != null && tempArista.getCosto() < costoMin) {
                    tAMin = tempArista;
                    costoMin = tempArista.getCosto();
                }
            }
        }
        return tAMin;
    }

    public String imprimirEtiquetas() {
        if (this.isEmpty()) {
            return null;
        }
        StringBuilder salida = new StringBuilder();
        for (TArista arista : this) {
            salida.append(arista.getEtiquetaOrigen());
            salida.append(SEPARADOR_ELEMENTOS_IMPRESOS);
            salida.append(arista.getEtiquetaDestino());
            salida.append(SEPARADOR_ELEMENTOS_IMPRESOS);
            salida.append(arista.getCosto());
            salida.append("\n");
        }
        return salida.toString();
    }

    void insertarAmbosSentidos(Collection<TArista> aristas) {
        for (TArista ta : aristas) {
            this.add(ta);
            this.add(ta.aristaInversa());
        }
    }
}
