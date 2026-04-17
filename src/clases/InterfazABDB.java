package clases;
import java.util.List;

public interface InterfazABDB<T> {
    // Metodo para calcular el grado del árbol (al ser binario, siempre es dos)
    int getGrado();
    // Metodo para obtener la altura del arbol
    int getAltura();
    // Metodo para obtener una lista de datos DEL MISMO NIVEL
    List<T> getListaDatosNivel(int nivel);
    // Metodo para ver si el arbol es de tipo homogeneo
    boolean isArbolHomogeneo();
    // Metodo para ver si el arbol está COMPLETO
    boolean isArbolCompleto();
    // Metodo para ver si el arbol está CASI COMPLETO
    boolean isArbolCasiCompleto();
    // Metodo para obtener el camino (¿de un nodo a otro?)
    List<NodoRama<T>> getCamino();          // considerar si devuelve List<T> o List<NodoRama<T>>
    // Se van a crear tres tipos de listas diferentes del árbol según el orden de la obtención de sus datos
    List<T> getListaPreorden();             // recorrido 1: ...
    List<T> getListaPostorden();            // recorrido 2: ...
    List<T> getListaOrdenCentral();         // recorrido 3: ...
}
