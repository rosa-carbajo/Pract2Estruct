package clases;

// El 'NodoHoja<T>' consiste en una clase que actúa como el punto de finalización de una rama o subárbol... Su estructura es tal que [ * | E] y no posee bifurcaciones.
public class NodoHoja<T>{
    // Atributos necesarios para el nodo...
    private Elemento<T> elemento;           // E:valor (dato asociado al nodo)
    private NodoRama<T> anterior;           // es el nodo al que va asociado (puede ser la raíz u otra rama)

    // ¡Constructor del nodo!
    public NodoHoja(Elemento<T> elemento) {
        this.elemento = elemento;
        this.anterior = null;
    }

    // Getter del elemento al final de la rama
    public Elemento<T> getElemento() {
        return elemento;
    }

    // Setter del elemento al final de la rama
    public void setElemento(Elemento<T> elemento) {
        this.elemento = elemento;
    }

    // Getter de la rama prinicipal o subárbol que contiene a esta hoja
    public NodoRama<T> getAnterior() {
        return anterior;
    }

    // Setter de la rama principal o subárbol que contiene a esta hoja
    public void setAnterior(NodoRama<T> anterior) {
        this.anterior = anterior;
    }
}
