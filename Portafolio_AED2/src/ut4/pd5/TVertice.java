package ut4.pd5;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class TVertice implements IVertice {

    private Comparable etiqueta;
    private LinkedList<TAdyacencia> adyacentes;
    private boolean visitado;
    private Object datos;

    @Override
    public Comparable getEtiqueta() {
        return etiqueta;
    }

    @Override
    public String toString() {
        return etiqueta.toString();
    }

    @Override
    public LinkedList<TAdyacencia> getAdyacentes() {
        return adyacentes;
    }

    public TVertice(Comparable unaEtiqueta) {
        this.etiqueta = unaEtiqueta;
        adyacentes = new LinkedList();
        visitado = false;
    }

    @Override
    public void setVisitado(boolean valor) {
        this.visitado = valor;
    }

    @Override
    public boolean getVisitado() {
        return this.visitado;
    }

    @Override
    public TAdyacencia buscarAdyacencia(TVertice verticeDestino) {
        if (verticeDestino != null) {
            return buscarAdyacencia(verticeDestino.getEtiqueta());
        }
        return null;
    }

    @Override
    public Double obtenerCostoAdyacencia(TVertice verticeDestino) {
        TAdyacencia ady = buscarAdyacencia(verticeDestino);
        if (ady != null) {
            return ady.getCosto();
        }
        return Double.MAX_VALUE;
    }

    @Override
    public boolean insertarAdyacencia(Double costo, TVertice verticeDestino) {
        if (buscarAdyacencia(verticeDestino) == null) {
            TAdyacencia ady = new TAdyacencia(costo, verticeDestino);
            return adyacentes.add(ady);
        }
        return false;
    }

    @Override
    public boolean eliminarAdyacencia(Comparable nomVerticeDestino) {
        TAdyacencia ady = buscarAdyacencia(nomVerticeDestino);
        if (ady != null) {
            adyacentes.remove(ady);
            return true;
        }
        return false;
    }

    @Override
    public TVertice primerAdyacente() {
        if (this.adyacentes.getFirst() != null) {
            return this.adyacentes.getFirst().getDestino();
        }
        return null;
    }

    @Override
    public TAdyacencia buscarAdyacencia(Comparable etiquetaDestino) {
        for (TAdyacencia adyacencia : adyacentes) {
            if (adyacencia.getDestino().getEtiqueta().compareTo(etiquetaDestino) == 0) {
                return adyacencia;
            }
        }
        return null;
    }

    @Override
    public Object getDatos() {
        return datos;
    }

    @Override
    public void bpf(Collection<TVertice> visitados) {
        visitado = true;
        visitados.add(this);
        for (IAdyacencia adyacente : adyacentes) {
            if (!adyacente.getDestino().getVisitado()) {
                adyacente.getDestino().bpf(visitados);
            }
        }
    }
    
    public void bpfPostorden(LinkedList<TVertice> visitados) {
        visitado = true;
        for (IAdyacencia adyacente : adyacentes) {
            if (!adyacente.getDestino().getVisitado()) {
                adyacente.getDestino().bpfPostorden(visitados);
            }
        }
        visitados.addFirst(this);
    }

    @Override
    public TCaminos todosLosCaminos(Comparable etiqutaDestino, TCamino caminoPrevio, TCaminos todosLosCaminos) {
        visitado = true;
        for (TAdyacencia adyacencia : adyacentes) {
            TVertice destino = adyacencia.getDestino();
            if (!destino.getVisitado()) {
                if (destino.getEtiqueta().equals(etiqutaDestino)) {
                    TCamino copia = caminoPrevio.copiar();
                    copia.agregarAdyacencia(adyacencia);
                    todosLosCaminos.getCaminos().add(copia);
                } else {
                    caminoPrevio.agregarAdyacencia(adyacencia);
                    destino.todosLosCaminos(etiqutaDestino, caminoPrevio, todosLosCaminos);
                    caminoPrevio.eliminarAdyacencia(adyacencia);
                }
            }
        }
        this.setVisitado(false);
        return todosLosCaminos;
    }

    @Override
    public boolean tieneCiclo(TCamino camino) {
        boolean x = false;
        visitado = true;
        for (TAdyacencia adyacente : adyacentes) {
            TVertice destino = adyacente.getDestino();
            camino.agregarAdyacencia(adyacente);
            if (destino.visitado) {
                return true;
            } else {
                x = destino.tieneCiclo(camino);
            }
            camino.eliminarAdyacencia(adyacente);
        }
        visitado = false;
        return x;
    }

    @Override
    public void bea(Collection<TVertice> visitados) {
        visitado = true;
        visitados.add(this);
        Queue<TVertice> cola = new LinkedList<>();
        for (IAdyacencia adyacente : adyacentes) {
            if (!adyacente.getDestino().getVisitado()) {
                cola.add(adyacente.getDestino());
            }
        }
        TVertice siguiente = cola.poll();
        if (siguiente != null) {
            siguiente.bea(visitados);
        }
    }
    
    public void clasificacionTopologica(Collection<TVertice> lista) {
        visitado = true;
        for (IAdyacencia adyacente : adyacentes) {
            if (!adyacente.getDestino().getVisitado()) {
                adyacente.getDestino().clasificacionTopologica(lista);
            }
        }
        lista.add(this);
    }

}
