package clases;

import java.util.List;

// TAD ÁRBOL BINARIO DE BÚSQUEDA
public class  ArbolBinarioDeBusqueda<T> implements InterfazABDB<T> {
    // Elementos imprescindibles de la estructura/arquitectura de este TAD...
    private NodoRaiz<T> raiz;
    private NodoRama<T> rama;
    private NodoHoja<T> hoja;
    // 1. "¿incluir grado como parámetro en el TAD o en los nodos?"
    // 2. "no hay metodo remove(), porque si no, ¿eso quitaría todas las aristas asociadas al vértice no? o hay otra manera de quitar el dato asociado al nodo"

    // Constructor del árbol binario
    public ArbolBinarioDeBusqueda(NodoRaiz<T> raiz, NodoRama<T> rama, NodoHoja<T> hoja) {
        this.raiz = raiz;
        this.rama = rama;
        this.hoja = hoja;
    }

    // Metodo para añadir elementos al arbol
    public void add(Elemento<T> elemento) {

    }

    /* Metodo para obtener el subárbol de la izquierda
    public ArbolBinarioDeBusqueda<T> getSubArbolIzquierda() {

    }
    */


    /* Metodo para obtener el subárbol de la derecha
    public ArbolBinarioDeBusqueda<T> getSubArbolDerecha() {

    }
     */

    // Metodo para comprobar si el árbol está vacio
    public boolean isArbolEmpty() {
        // Siempre falso
        boolean b = false;
        // Si los tres nodos están vacíos (debería de ser lo mismo que preguntar, "¿hay raíz?"), entonces, es verdad
        return b;
    }

    // METODOS QUE HAN SIDO IMPLEMENTADOS POR LA INTERFAZ
    @Override
    public int getGrado() {
        return 0;
    }

    @Override
    public int getAltura() {
        return 0;
    }

    @Override
    public List<T> getListaDatosNivel(int nivel) {
        return List.of();
    }

    @Override
    public boolean isArbolHomogeneo() {
        return false;
    }

    @Override
    public boolean isArbolCompleto() {
        return false;
    }

    @Override
    public boolean isArbolCasiCompleto() {
        return false;
    }

    @Override
    public List<NodoRama<T>> getCamino() {
        return List.of();
    }

    @Override
    public List<T> getListaPreorden() {
        return List.of();
    }

    @Override
    public List<T> getListaPostorden() {
        return List.of();
    }

    @Override
    public List<T> getListaOrdenCentral() {
        return List.of();
    }
}
