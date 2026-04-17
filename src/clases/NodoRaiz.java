package clases;

// El 'NodoRaiz<T>' consiste una clase que actúa como el punto de partida del árbol, tal que tiene una estructura [ E | * || * ]... no puede tener ningún nodo por detrás
public class NodoRaiz<T> {
    // Atributos necesarios para el nodo...
    private Elemento<T> inicial;                // E:valor (se le llama inicial para facilitar notación)
    private NodoRama<T> izquierda;              // opción 1: izquierda
    private NodoRama<T> derecha;                // opción 2: derecha

    // ¡Constructor del nodo!
    public NodoRaiz(Elemento<T> inicial) {
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
    public NodoRama<T> getIzquierda() {
        return izquierda;
    }

    // Setter de los subárboles bifurcados hacia la izquierda
    public void setIzquierda(NodoRama<T> izquierda) {
        this.izquierda = izquierda;
    }

    // Getter de la rama que se bifurca hacia la derecha
    public NodoRama<T> getDerecha() {
        return derecha;
    }

    // Setter de los subárboles bifurcados hacia la derecha
    public void setDerecha(NodoRama<T> derecha) {
        this.derecha = derecha;
    }

}
