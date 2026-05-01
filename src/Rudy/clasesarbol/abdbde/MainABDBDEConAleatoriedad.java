package clasesarbol.abdbde;

import clasesarbol.abdb.Elemento;
import clasesarbol.abdb.NodoArbol;
import extra.Lista;

import java.util.Random;

public class MainABDBDEConAleatoriedad {
    // Esta prueba es de forma aleatoria...
    public static void main(String[] args) {
        // Se crea el árbol necesario para poder realizar el test
        ArbolBinariodeBusquedaDeEnteros arbol = new ArbolBinariodeBusquedaDeEnteros();
        // i. Se añaden todos los números del 0 al 128 uno a uno de forma aleatoria (sin repeticiones)
        Random r = new Random();
        boolean[] usados = new boolean[129];
        int count = 0;
        while (count < 129) {
            int valor = r.nextInt(129);
            if (!usados[valor]) {
                usados[valor] = true;
                arbol.add(new Elemento<>(valor));
                count = count + 1;
            }
        }
        // ii. Se calcula la suma de ese árbol
        System.out.println("SUMA: " + arbol.getSuma());
        // iii. Se verifica que la suma es la misma accediendo a los diferentes tipos de órdenes
        System.out.println("PREORDEN: " + arbol.getListaPreorden());                // preorden
        System.out.println("POSTORDEN: " + arbol.getListaPostorden());              // postorden
        System.out.println("ORDEN CENTRAL: " + arbol.getListaOrdenCentral());       // orden central
        // iv. Comprobar que la suma es la misma sumando ambos subárboles
        ArbolBinariodeBusquedaDeEnteros izq = new ArbolBinariodeBusquedaDeEnteros(arbol.getSubArbolIzquierda().getRaiz()); // izquierda
        ArbolBinariodeBusquedaDeEnteros der = new ArbolBinariodeBusquedaDeEnteros(arbol.getSubArbolDerecha().getRaiz());
        int sumaIzq = izq.getSuma();
        int sumaDer = der.getSuma();
        int raiz = arbol.getRaiz().getInicial().getValor();
        System.out.println("LA SUMA MEDIANTE EL OTRO MÉTODO ES:" + sumaIzq + sumaDer + raiz);
        // v. Se calcula la altura del árbol
        System.out.println("ALTURA: " + arbol.getAltura());
        // vi. Obtener el camino al número 110 del árbol aleatorio:
        Lista<NodoArbol<Integer>> camino = arbol.getCaminoA(110);
        System.out.println("Camino a 110: " + camino);
        int longitud = arbol.longitudCamino(camino);
        System.out.println("Longitud del camino: " + longitud);
    }
}
