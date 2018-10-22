package ut4.pd5;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

public class TGrafoDirigido implements IGrafoDirigido {

    private final Map<Comparable, TVertice> vertices; //lista de vertices del grafo.-

    public TGrafoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
        this.vertices = new HashMap<>();
        for (TVertice vertice : vertices) {
            insertarVertice(vertice.getEtiqueta());
        }
        for (TArista arista : aristas) {
            insertarArista(arista);
        }
    }

    /**
     * Metodo encargado de eliminar una arista dada por un origen y destino. En
     * caso de no existir la adyacencia, retorna falso. En caso de que las
     * etiquetas sean invalidas, retorna falso.
     *
     * @param nomVerticeOrigen
     * @param nomVerticeDestino
     * @return
     */
    @Override
    public boolean eliminarArista(Comparable nomVerticeOrigen, Comparable nomVerticeDestino) {
        if ((nomVerticeOrigen != null) && (nomVerticeDestino != null)) {
            TVertice vertOrigen = buscarVertice(nomVerticeOrigen);
            if (vertOrigen != null) {
                return vertOrigen.eliminarAdyacencia(nomVerticeDestino);
            }
        }
        return false;
    }

    /**
     * Metodo encargado de eliminar un vertice en el grafo. En caso de no
     * existir el v�rtice, retorna falso. En caso de que la etiqueta sea
     * inv�lida, retorna false.
     *
     * @param nombreVertice
     * @return
     */
    @Override
    public boolean eliminarVertice(Comparable nombreVertice) {
        if (nombreVertice != null) {
            getVertices().remove(nombreVertice);
            return getVertices().containsKey(nombreVertice);
        }
        return false;
    }

    /**
     * Metodo encargado de verificar la existencia de una arista. Las etiquetas
     * pasadas por par�metro deben ser v�lidas.
     *
     * @param etiquetaOrigen
     * @param etiquetaDestino
     * @return True si existe la adyacencia, false en caso contrario
     */
    @Override
    public boolean existeArista(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        TVertice vertOrigen = buscarVertice(etiquetaOrigen);
        TVertice vertDestino = buscarVertice(etiquetaDestino);
        if ((vertOrigen != null) && (vertDestino != null)) {
            return vertOrigen.buscarAdyacencia(vertDestino) != null;
        }
        return false;
    }

    /**
     * Metodo encargado de verificar la existencia de un vertice dentro del
     * grafo.-
     *
     * La etiqueta especificada como par�metro debe ser v�lida.
     *
     * @param unaEtiqueta Etiqueta del v�rtice a buscar.-
     * @return True si existe el vertice con la etiqueta indicada, false en caso
     * contrario
     */
    @Override
    public boolean existeVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta) != null;
    }

    /**
     * Metodo encargado de verificar buscar un vertice dentro del grafo.-
     *
     * La etiqueta especificada como parametro debe ser valida.
     *
     * @param unaEtiqueta Etiqueta del v�rtice a buscar.-
     * @return El vertice encontrado. En caso de no existir, retorna nulo.
     */
    private TVertice buscarVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta);
    }

    /**
     * Matodo encargado de insertar una arista en el grafo (con un cierto
     * costo), dado su vertice origen y destino.- Para que la arista sea valida,
     * se deben cumplir los siguientes casos: 1) Las etiquetas pasadas por
     * parametros son v�lidas.- 2) Los vertices (origen y destino) existen
     * dentro del grafo.- 3) No es posible ingresar una arista ya existente
     * (miso origen y mismo destino, aunque el costo sea diferente).- 4) El
     * costo debe ser mayor que 0.
     *
     * @param arista
     * @return True si se pudo insertar la adyacencia, false en caso contrario
     */
    @Override
    public boolean insertarArista(TArista arista) {
        boolean tempbool = false;
        if ((arista.getEtiquetaOrigen() != null) && (arista.getEtiquetaDestino() != null)) {
            TVertice vertOrigen = buscarVertice(arista.getEtiquetaOrigen());
            TVertice vertDestino = buscarVertice(arista.getEtiquetaDestino());
            tempbool = (vertOrigen != null) && (vertDestino != null);
            if (tempbool) {
                //getLasAristas().add(arista);
                return vertOrigen.insertarAdyacencia(arista.getCosto(), vertDestino);
            }

        }
        return false;
    }

    /**
     * Metodo encargado de insertar un vertice en el grafo.
     *
     * No pueden ingresarse v�rtices con la misma etiqueta. La etiqueta
     * especificada como par�metro debe ser v�lida.
     *
     * @param unaEtiqueta Etiqueta del v�rtice a ingresar.
     * @return True si se pudo insertar el vertice, false en caso contrario
     */
    public boolean insertarVertice(Comparable unaEtiqueta) {
        if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
            TVertice vert = new TVertice(unaEtiqueta);
            getVertices().put(unaEtiqueta, vert);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }

    @Override
    public boolean insertarVertice(TVertice vertice) {
        Comparable unaEtiqueta = vertice.getEtiqueta();
        if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
            getVertices().put(unaEtiqueta, vertice);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }

    public Object[] getEtiquetasOrdenado() {
        TreeMap<Comparable, TVertice> mapOrdenado = new TreeMap<>(this.getVertices());
        return mapOrdenado.keySet().toArray();
    }

    @Override
    public void desvisitarVertices() {
        for (TVertice vertice : this.vertices.values()) {
            vertice.setVisitado(false);
        }
    }

    /**
     * @return the vertices
     */
    @Override
    public Map<Comparable, TVertice> getVertices() {
        return vertices;
    }

    @Override
    public Comparable centroDelGrafo() {
        Double[][] matrizDeFloyd = floyd();
        Double[] vectorExcentricidades = new Double[matrizDeFloyd.length];

        for (int i = 0; i < vectorExcentricidades.length; i++) {
            vectorExcentricidades[i] = 0.0;
        }

        for (int i = 0; i < matrizDeFloyd.length; i++) {
            for (int j = 0; j < matrizDeFloyd.length; j++) {
                if (matrizDeFloyd[i][j] > vectorExcentricidades[j]) {
                    vectorExcentricidades[j] = matrizDeFloyd[i][j];
                }
            }
        }
        return (Comparable) vertices.keySet().toArray()[obtenerMinimoValor(vectorExcentricidades)];
    }

    private int obtenerMinimoValor(Double[] vectorExcentricidades) {
        int posicion = 0;
        Double minimo = vectorExcentricidades[posicion];
        for (int i = 1; i < vectorExcentricidades.length; i++) {
            if (vectorExcentricidades[i] < minimo) {
                minimo = vectorExcentricidades[i];
                posicion = i;
            }
        }
        return posicion;
    }

    @Override
    public Double[][] floyd() {
        Double[][] matrizFloyd = UtilGrafos.obtenerMatrizCostos(vertices);
        //matrizPredecesores = new Double[matrizFloyd.length][matrizFloyd.length];
        for (int i = 0; i < matrizFloyd.length; i++) {
            matrizFloyd[i][i] = 0.0;
        }
        for (int k = 0; k < matrizFloyd.length; k++) {
            for (int i = 0; i < matrizFloyd.length; i++) {
                for (int j = 0; j < matrizFloyd.length; j++) {
                    if ((matrizFloyd[i][k] + matrizFloyd[k][j]) < matrizFloyd[i][j]) {
                        matrizFloyd[i][j] = matrizFloyd[i][k] + matrizFloyd[k][j];
                        //matrizPredecesores[i][j] = k + 0d;
                    }
                }
            }
        }
        return matrizFloyd;
    }

    @Override
    public Comparable obtenerExcentricidad(Comparable etiquetaVertice) {
        Double[][] c = floyd();
        int columnaVertice = 0;
        int contador = 0;
        for (Comparable key : vertices.keySet()) {
            if (key.equals(etiquetaVertice)) {
                columnaVertice = contador;
                break;
            }
            contador += 1;
        }
        double excentricidad = 0;
        for (int i = 0; i < c.length; i++) {
            if (c[i][columnaVertice] > excentricidad) {
                excentricidad = c[i][columnaVertice];
            }
        }
        if (excentricidad == Double.MAX_VALUE) {
            excentricidad = -1;
        }
        return excentricidad;
    }

    @Override
    public Collection<TVertice> bea() {
        desvisitarVertices();
        Collection<TVertice> resultado = new LinkedList<>();
        for (TVertice vertice : vertices.values()) {
            if (!vertice.getVisitado()) {
                vertice.bea(resultado);
            }
        }
        desvisitarVertices();
        return resultado;
    }

    @Override
    public Collection<TVertice> bpf() {
        desvisitarVertices();
        Collection<TVertice> resultado = new LinkedList<>();
        for (TVertice vertice : vertices.values()) {
            if (!vertice.getVisitado()) {
                vertice.bpf(resultado);
            }
        }
        desvisitarVertices();
        return resultado;
    }

    @Override
    public Collection<TVertice> bpf(Comparable etiquetaOrigen) {
        desvisitarVertices();
        Collection<TVertice> resultado = new LinkedList<>();
        TVertice vertice = vertices.get(etiquetaOrigen);
        if (vertice != null) {
            if (!vertice.getVisitado()) {
                vertice.bpf(resultado);
            }
        }
        desvisitarVertices();
        return resultado;
    }

    @Override
    public Collection<TVertice> bpf(TVertice vertice) {
        desvisitarVertices();
        Collection<TVertice> resultado = new LinkedList<>();
        if (vertices.containsValue(vertice)) {
            if (!vertice.getVisitado()) {
                vertice.bpf(resultado);
            }
        }
        desvisitarVertices();
        return resultado;
    }

    @Override
    public boolean[][] warshall() {
        Double[][] costos = UtilGrafos.obtenerMatrizCostos(vertices);
        boolean[][] w = new boolean[vertices.size()][vertices.size()];
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = 0; j < vertices.size(); j++) {
                if (costos[i][j] == Double.MAX_VALUE || costos[i][j] <= 0) {
                    w[i][j] = false;
                } else {
                    w[i][j] = true;
                }
            }
        }
        for (int k = 0; k < w.length; k++) {
            for (int i = 0; i < w.length; i++) {
                for (int j = 0; j < w.length; j++) {
                    if (i == j) {
                        w[i][j] = false;
                    } else if (w[i][j] == false) {
                        w[i][j] = w[i][k] && w[k][j];
                    }
                }
            }
        }
        return w;
    }

    @Override
    public TCaminos todosLosCaminos(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        TCaminos todosLosCaminos = new TCaminos();
        TVertice v = buscarVertice(etiquetaOrigen);
        if (v != null) {
            TCamino caminoPrevio = new TCamino(v);
            v.todosLosCaminos(etiquetaDestino, caminoPrevio, todosLosCaminos);
            return todosLosCaminos;
        }
        return null;
    }

    @Override
    public boolean tieneCiclo(TCamino camino) {
        desvisitarVertices();
        camino.getOrigen().setVisitado(true);
        for (Comparable key : camino.getOtrosVertices()) {
            vertices.get(key).setVisitado(true);
        }
        TVertice vertice = vertices.get(camino.getOtrosVertices().getLast());
        if (vertice.tieneCiclo(camino)) {
            System.out.println(camino.imprimirEtiquetas());
            desvisitarVertices();
            return true;
        }
        desvisitarVertices();
        return false;
    }

    @Override
    public boolean tieneCiclo(Comparable etiquetaOrigen) {
        desvisitarVertices();
        TVertice vertice = vertices.get(etiquetaOrigen);
        TCamino camino = new TCamino(vertice);
        if (vertice.tieneCiclo(camino)) {
            System.out.println(camino.imprimirEtiquetas());
            desvisitarVertices();
            return true;
        }
        desvisitarVertices();
        return false;
    }

    @Override
    public boolean tieneCiclo() {
        desvisitarVertices();
        for (TVertice vertice : vertices.values()) {
            TCamino camino = new TCamino(vertice);
            if (!vertice.getVisitado()) {
                if (vertice.tieneCiclo(camino)) {
                    System.out.println(camino.imprimirEtiquetas());
                    desvisitarVertices();
                    return true;
                }
            }
        }
        desvisitarVertices();
        return false;
    }

    public boolean esConexo() {
        for (TVertice vertice : vertices.values()) {
            desvisitarVertices();
            Collection<TVertice> vxs = new HashSet<>();
            vertice.bpf(vxs);
            if (vertices.size() > vxs.size()) {
                return false;
            }
        }
        return true;
    }
    
    public Collection<TVertice> clasificacionTopologica(Comparable etiquetaOrigen) {
        desvisitarVertices();
        LinkedList<TVertice> lista = new LinkedList<>();
        if (!tieneCiclo()) {
            TVertice vertice = vertices.get(etiquetaOrigen);
            vertice.clasificacionTopologica(lista);
        }
        desvisitarVertices();
        return lista;
    }
    
    public TCamino caminoCritico(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        TCamino critico = null;
        if (!tieneCiclo()) {
            TCaminos caminos = todosLosCaminos(etiquetaOrigen, etiquetaDestino);
            double max = 0;
            
            for (TCamino camino : caminos.getCaminos()) {
                if (camino.getCostoTotal() > max) {
                    max = camino.getCostoTotal();
                    critico = camino;
                }
            }
        }
        return critico;
    }
    
    public Collection<Collection<TVertice>> componentesConexos() {
        Collection<Collection<TVertice>> componentes = new LinkedList<>();

        LinkedList<TVertice> bpfTotal = bpfPostorden();

        TGrafoDirigido grafoInverso = grafoOpuesto();
        Collection<TVertice> arbol = new LinkedList<>();
        for (TVertice vertice : bpfTotal) {
            TVertice vEnOpuesto = grafoInverso.getVertices().get(vertice.getEtiqueta());
            if (!vEnOpuesto.getVisitado()) {
                vEnOpuesto.setVisitado(true);
                vEnOpuesto.bpf(arbol);
                componentes.add(arbol);
                arbol = new LinkedList<>();
            }
        }
        return componentes;
    }
    
    private TGrafoDirigido grafoOpuesto() {
        LinkedList<TArista> aristas = new LinkedList<>();
        for (TVertice v : vertices.values()) {
            for (TAdyacencia adyacente : v.getAdyacentes()) {
                aristas.add(new TArista(adyacente.getEtiqueta(), v.getEtiqueta(), adyacente.getCosto()));
            }
        }
        return new TGrafoDirigido(vertices.values(), aristas);
    }
    
    private LinkedList<TVertice> bpfPostorden() {
        desvisitarVertices();
        LinkedList<TVertice> resultado = new LinkedList<>();
        for (TVertice vertice : vertices.values()) {
            if (!vertice.getVisitado()) {
                vertice.bpfPostorden(resultado);
            }
        }
        desvisitarVertices();
        return resultado;
    }
}
