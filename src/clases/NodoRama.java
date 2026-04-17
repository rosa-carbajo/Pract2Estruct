package clases;

// El 'NodoRama<T>' consiste en una clase que tiene una estructura tal que [ * [ E | * || * ]], tal que puede ser incrustado en cualquier '*' (punto) determinado del árbol
public class NodoRama<T>{
    // Atributos necesarios para el nodo...
    private Elemento<T> elemento;                       // E:valor (dato asociado al nodo)
    private NodoRama<T> anterior;                       // es el nodo al que va asociado (puede ser la raíz u otra rama)
    private NodoRama<T> izquierda;                      // opción 1: izquierda
    private NodoRama<T> derecha;                        // opción 2: derecha

    // ¡Constructor del nodo!
    public NodoRama(Elemento<T> elemento) {
        this.elemento = elemento;
        this.anterior = null;
        this.izquierda = null;
        this.derecha = null;
    }

    // Getter del elemento con el cual podremos extraer el valor después
    public Elemento<T> getElemento() {
        return elemento;
    }

    // Setter del elemento
    public void setElemento(Elemento<T> elemento) {
        this.elemento = elemento;
    }

    // Getter de la rama que iba antes del nodo actual
    public NodoRama<T> getAnterior() {
        return anterior;    // podemos saber en qué nivel nos encontramos
    }

    // Setter de la rama que va antes del nodo actual (permite reseteo y recursividad)
    public void setAnterior(NodoRama<T> anterior) {
        this.anterior = anterior;
    }

    // Getter de todos los subárboles de la izquierda
    public NodoRama<T> getIzquierda() {
        return izquierda;
    }

    // Setter de todos los subárboles de la izquierda
    public void setIzquierda(NodoRama<T> izquierda) {
        this.izquierda = izquierda;
    }

    // Getter de todos los subárboles de la derecha
    public NodoRama<T> getDerecha() {
        return derecha;
    }

    // Setter de todos los subárboles de la derecha
    public void setDerecha(NodoRama<T> ramados) {
        this.derecha = derecha;
    }
}
