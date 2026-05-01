package clasesarbol.abdb;
import extra.Lista;

public interface InterfazABDB<T extends Comparable<T>> {
    // Metodo para calcular el grado del árbol (al ser binario, siempre es dos)
    int getGrado();
    // Metodo para obtener la altura del arbol
    int getAltura();
    // Metodo para obtener una lista de datos DEL MISMO NIVEL
    Lista<T> getListaDatosNivel(int nivel);
    // Metodo para ver si el arbol es de tipo homogeneo
    boolean isArbolHomogeneo();
    // Metodo para ver si el arbol está COMPLETO
    boolean isArbolCompleto();
    // Metodo para ver si el arbol está CASI COMPLETO
    boolean isArbolCasiCompleto();
    // Metodo para obtener el camino (de la raiz hasta el nodo más alejado)
    Lista<NodoArbol<T>> getCamino();
    // Se van a crear tres tipos de listas diferentes del árbol según el orden de la obtención de sus datos
    // SEA LA LISTA: [9,7,3,5,8,10,22]
    Lista<T> getListaPreorden();             // recorrido 1: la misma lista
    Lista<T> getListaPostorden();            // recorrido 2: [5,3,8,7,22,10,9]
    Lista<T> getListaOrdenCentral();         // recorrido 3: [3,5,7,8,9,10,22] (lista ordenada)
}
