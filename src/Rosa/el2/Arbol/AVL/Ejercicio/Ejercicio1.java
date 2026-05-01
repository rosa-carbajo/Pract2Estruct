package es.uah.matcomp.ed.el2.Arbol.AVL.Ejercicio;

import Listas.Lista_simple.Lista_Simple;

/**
 * Programa de prueba 1: inserta los números del 0 al 128 EN ORDEN y
 * verifica las propiedades del árbol resultante.
 */
public class Ejercicio1 {

    public static void main(String[] args) {

        ArbolBinarioDeBusquedaEnteros arbol = new ArbolBinarioDeBusquedaEnteros();

        // --- 1. Inserción en orden -------------------------
        System.out.println("=== INSERCIÓN EN ORDEN (0 → 128) ===");
        for (int i = 0; i <= 128; i++) {
            arbol.anadir(i);
        }
        System.out.println("Elementos insertados: 0 a 128\n");

        // -- 2. Suma total --------------------------
        int suma = arbol.getSuma();
        System.out.println("=== SUMA ===");
        System.out.println("getSuma()  → " + suma + "\n");

        // -- 3. Verificación por los 3 recorridos --------------------------
        System.out.println("=== VERIFICACIÓN POR RECORRIDOS ===");

        Lista_Simple<Integer> inOrden   = arbol.getListaInOrden();
        Lista_Simple<Integer> preOrden  = arbol.getListaPreOrden();
        Lista_Simple<Integer> postOrden = arbol.getListaPostOrden();

        int sumaIn   = sumarLista(inOrden);
        int sumaPre  = sumarLista(preOrden);
        int sumaPost = sumarLista(postOrden);

        System.out.println("Suma In-Order    → " + sumaIn);
        System.out.println("Suma Pre-Order   → " + sumaPre);
        System.out.println("Suma Post-Order  → " + sumaPost);
        System.out.println("¿Todas iguales?  → " + (sumaIn == sumaPre && sumaPre == sumaPost) + "\n");

        //--- 4. Verificación por subárboles ---------------------------
        System.out.println("=== VERIFICACIÓN POR SUBÁRBOLES ===");

        ArbolBinarioDeBusquedaEnteros izq  = (ArbolBinarioDeBusquedaEnteros) arbol.getSubArbolIzquierda();
        ArbolBinarioDeBusquedaEnteros drch = (ArbolBinarioDeBusquedaEnteros) arbol.getSubArbolDerecha();

        // La raíz es el 0 (primer insertado), su valor es 0
        int valorRaiz   = 0;
        int sumaIzq     = izq.getSuma();
        int sumaDrch    = drch.getSuma();
        int sumaTotal   = valorRaiz + sumaIzq + sumaDrch;

        System.out.println("Valor raíz       → " + valorRaiz);
        System.out.println("Suma subárbol Izq→ " + sumaIzq);
        System.out.println("Suma subárbol Dch→ " + sumaDrch);
        System.out.println("Suma total       → " + sumaTotal);
        System.out.println("¿Igual a getSuma?→ " + (sumaTotal == suma));
        System.out.println("  La suma de los subárboles más la raíz siempre da la total");
        System.out.println("  porque la suma total es simplemente la suma de la raíz más todos los valores a su izquierda " +
                "y todos los valores a su derecha.");

        // -- 5. Altura -------------------------
        System.out.println("=== ALTURA ===");
        System.out.println("Altura del árbol → " + arbol.getAltura());
        System.out.println(" Insertando en orden, cada número es mayor que el anterior,");
        System.out.println("  por lo que el árbol degenera en una lista enlazada hacia la derecha.");
        System.out.println("  Altura esperada: 129 (un nodo por nivel)\n");

        // --- 6. Camino al valor 110 -------------------------
        System.out.println("=== CAMINO AL VALOR 110 ===");
        Lista_Simple<Integer> camino = arbol.getCamino(110);
        System.out.print("Camino: ");
        camino.visualizar();
        System.out.println("Longitud del camino → " + camino.getTamanio());
        System.out.println(" En un árbol degenerado, el camino tiene tantos saltos");
        System.out.println("  como el valor buscado (110 saltos desde la raíz 0).");
    }

    // -- Utilidades --------------------------------------------------

    /** Suma todos los elementos de una {@code Lista_Simple<Integer>}. */
    private static int sumarLista(Lista_Simple<Integer> lista) {
        int suma = 0;
        for (int i = 0; i < lista.getTamanio(); i++) {
            suma += lista.get(i).getData();
        }
        return suma;
    }

}