package es.uah.matcomp.ed.el2.Arbol.AVL.Ejercicio;

import Listas.Lista_simple.Lista_Simple;
import java.util.Random; // Se permite para generar el azar, pero no para estructuras

/**
 * Programa de prueba 2: inserta los números del 0 al 128 ALEATORIAMENTE y
 * verifica las propiedades del árbol resultante.
 */
public class Ejercicio2 {

    public static void main(String[] args) {
        ArbolBinarioDeBusquedaEnteros arbol = new ArbolBinarioDeBusquedaEnteros();
        Random random = new Random();

        // --- 1. Inserción Aleatoria (0 a 128 sin repetir) ---
        System.out.println("=== INSERCIÓN ALEATORIA (0 → 128) ===");

        int[] numeros = new int[129];
        for (int i = 0; i <= 128; i++) numeros[i] = i;

        // Mezcla del array (Fisher-Yates shuffle) manualmente
        for (int i = numeros.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = numeros[i];
            numeros[i] = numeros[j];
            numeros[j] = temp;
        }

        for (int n : numeros) {
            arbol.anadir(n);
        }
        System.out.println("129 elementos insertados aleatoriamente.\n");

        // -- 2. Suma total --
        int suma = arbol.getSuma();
        System.out.println("=== SUMA ===");
        System.out.println("getSuma()  → " + suma + "\n");

        // -- 3. Verificación por los 3 recorridos --
        System.out.println("=== VERIFICACIÓN POR RECORRIDOS ===");
        int sumaIn   = sumarLista(arbol.getListaInOrden());
        int sumaPre  = sumarLista(arbol.getListaPreOrden());
        int sumaPost = sumarLista(arbol.getListaPostOrden());

        System.out.println("Suma In-Order    → " + sumaIn);
        System.out.println("Suma Pre-Order   → " + sumaPre);
        System.out.println("Suma Post-Order  → " + sumaPost);
        System.out.println("¿Iguales?        → " + (sumaIn == sumaPre && sumaPre == sumaPost) + "\n");

        // --- 4. Verificación por subárboles ---
        System.out.println("=== VERIFICACIÓN POR SUBÁRBOLES ===");
        ArbolBinarioDeBusquedaEnteros izq  = (ArbolBinarioDeBusquedaEnteros) arbol.getSubArbolIzquierda();
        ArbolBinarioDeBusquedaEnteros drch = (ArbolBinarioDeBusquedaEnteros) arbol.getSubArbolDerecha();

        // Obtenemos el valor de la raíz (usando el camino al primer elemento)
        int valorRaiz = arbol.getListaPreOrden().get(0).getData();
        int sumaIzq   = (izq != null) ? izq.getSuma() : 0;
        int sumaDrch  = (drch != null) ? drch.getSuma() : 0;
        int sumaTotalSub = valorRaiz + sumaIzq + sumaDrch;

        System.out.println("Valor raíz       → " + valorRaiz);
        System.out.println("Suma total subs  → " + sumaTotalSub);
        System.out.println("¿Igual a total?  → " + (sumaTotalSub == suma));
        System.out.println("¿Por qué? → Porque un árbol es una estructura recursiva; todo el contenido del " +
                "árbol es la unión disjunta de la raíz y sus dos hijos." +
                "(Es decir, La suma total es simplemente la suma de la raíz más todos los valores a su izquierda " +
                "y todos los valores a su derecha." +
                " No puede haber una intersección entre el conjunto izquierdo y el derecho.)");

        // -- 5. Altura --
        System.out.println("\n=== ALTURA ===");
        int altura = arbol.getAltura();
        System.out.println("Altura del árbol → " + altura);
        System.out.println("¿Por qué? → Al insertar aleatoriamente, el árbol tiende a estar más balanceado que en orden secuencial. " +
                "La altura será mucho menor que 129 " +
                "ya que as inserciones aleatorias distribuyen los nodos a izquierda y derecha de forma más equitativa.");

        // --- 6. Camino al valor 110 ---
        System.out.println("\n=== CAMINO AL VALOR 110 ===");
        Lista_Simple<Integer> camino = arbol.getCamino(110);
        System.out.print("Camino: ");
        camino.visualizar();
        System.out.println("Longitud del camino → " + camino.getTamanio());
        System.out.println("La longitud es menor que en el Ejercicio 1 porque el árbol no es una línea recta.");
    }

    private static int sumarLista(Lista_Simple<Integer> lista) {
        int suma = 0;
        for (int i = 0; i < lista.getTamanio(); i++) {
            suma += lista.get(i).getData();
        }
        return suma;
    }
}