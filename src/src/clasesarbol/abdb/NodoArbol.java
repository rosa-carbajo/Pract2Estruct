package clasesarbol.abdb;

// El 'NodoArbol' consiste una clase que actúa como el punto de partida del árbol, tal que tiene una estructura [ E | * || * ]... no puede tener ningún nodo por detrás
public class NodoArbol<T> {
    // Atributos necesarios para el nodo...
    private Elemento<T> inicial;            // E:valor (se le llama inicial para facilitar notación)
    private NodoArbol<T> izquierda;              // opción 1: izquierda
    private NodoArbol<T> derecha;                // opción 2: derecha

    // ¡Constructor del nodo!
    public NodoArbol(Elemento<T> inicial) {
        this.inicial = inicial;
        this.izquierda = null;
        this.derecha = null;
    }

    // Getter del dato en la raíz
    public Elemento<T> getInicial() {
        return inicial;
    }

    // Setter del dato en la raíz
    public void setInicial(Elemento<T> inicial) {
        this.inicial = inicial;
    }

    // Getter de la rama que se bifurca hacia la izquierda
    public NodoArbol<T> getIzquierda() {
        return izquierda;
    }

    // Setter de los subárboles bifurcados hacia la izquierda
    public void setIzquierda(NodoArbol<T> izquierda) {
        this.izquierda = izquierda;
    }

    // Getter de la rama que se bifurca hacia la derecha
    public NodoArbol<T> getDerecha() {
        return derecha;
    }

    // Setter de los subárboles bifurcados hacia la derecha
    public void setDerecha(NodoArbol<T> derecha) {
        this.derecha = derecha;
    }
}
