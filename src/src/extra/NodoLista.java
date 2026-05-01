package extra;

public class NodoLista<T> {
    // Parámetros necesarios que debe de implementar el nodo
    private T dato;
    private NodoLista<T> siguiente;

    // Constructor por defecto del nodo de la lista...
    NodoLista(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    // Getter del dato
    public T getDato() {
        return dato;
    }

    // Setter del dato
    public void setDato(T dato) {
        this.dato = dato;
    }

    // Getter de la referencia/nodo que va después
    public NodoLista<T> getSiguiente() {
        return siguiente;
    }

    // Setter de la referencia/nodo que iría después
    public void setSiguiente(NodoLista<T> siguiente) {
        this.siguiente = siguiente;
    }

}
