package es.uah.matcomp.ed.el2.Arbol.AVL.Ejercicio;

import es.uah.matcomp.ed.el2.Arbol.AVL.AVL;
import es.uah.matcomp.ed.el2.Arbol.Arbol.Nodo_Arbol_Binario;

/**
 * Subclase de {@link AVL} especializada en el tipo {@code Integer}.
 * Añade la operación {@link #getSuma()} para calcular la suma de todos los elementos
 * insertados en el árbol.
 */
public class ArbolBinarioDeBusquedaEnteros extends AVL<Integer> {

    // -------- CONSTRUCTORES --------

    /** Crea un árbol vacío. */
    public ArbolBinarioDeBusquedaEnteros() {
        super();
    }

    /**
     * Crea un árbol con un único nodo raíz.
     *
     * @param valor Valor entero que se almacena en la raíz.
     */
    public ArbolBinarioDeBusquedaEnteros(Integer valor) {
        super(valor);
    }

    // -------- SUMA --------

    /**
     * Calcula la suma de todos los elementos contenidos en el árbol.
     * <p>
     * Recorre el árbol en in-order y acumula los valores. Devuelve 0
     * si el árbol está vacío.
     *
     * @return La suma de todos los enteros almacenados en el árbol.
     */
    public int getSuma() {
        return getSumaRecursivo(root);
    }

    /**
     * Método recursivo auxiliar que suma todos los valores del subárbol
     * cuya raíz es {@code actual}.
     * <p>
     * Suma el dato del nodo actual más la suma de su subárbol izquierdo
     * y la suma de su subárbol derecho. Un nodo nulo aporta 0.
     *
     * @param actual El nodo raíz del subárbol que se está sumando.
     * @return La suma de todos los enteros del subárbol.
     */
    private int getSumaRecursivo(Nodo_Arbol_Binario<Integer> actual) {
        if (actual == null) return 0;
        return actual.getDato()
                + getSumaRecursivo(actual.getNodoIzq())
                + getSumaRecursivo(actual.getNodoDrch());
    }
}