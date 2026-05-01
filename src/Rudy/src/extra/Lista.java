package extra;

public class Lista<T> {
    //Se trata de la implementacion de una LSE, la cual, posee solo un nodo que actúa como cabeza
    private NodoLista<T> cabeza;

    // Para construir una lista por defecto, se crea una lista vacía (que la cabeza esté en consecuente, vacía)
    public Lista() {
        cabeza = null;
    }

    // Para poder insertar un dato en la lista, se usa el siguiente metodo
    public void insertar(T dato) {
        // Se crea un nodo que contenga al dato y se revisan dos posibles casos:
        NodoLista<T> nuevo = new NodoLista<T> (dato);

        // Si la cabeza está vacía...
        if (cabeza == null) {
            this.cabeza = nuevo;
        }

        // En caso de no estarlo...
        else {
            // Se enlazan los nodos y se reestablece la nueva información
            nuevo.setSiguiente(cabeza);
            cabeza = nuevo;
        }
    }

    // Para eliminar un elemento de la lista se hace lo siguiente
    public void eliminar(T dato) {
        // Si está vacía, pues nada
        if (cabeza == null) {
            return;
        }

        // Si no lo está, ver si el elemento que se encuentra dentro es el que queremos quitar, y por ende, eliminar la cabeza
        if (cabeza.getDato().equals(dato)) {
            // [ A | [B | *] ] =  [ B | * ]
            cabeza = cabeza.getSiguiente();
            // Y ya habríamos acabado
            return;
        }

        // En caso de que no esté en la cabeza, hay que iterar elemento a elemento en la lista hasta encontrarlo
        NodoLista<T> actual = cabeza;       // copia de la cabeza

        // Se itera hasta que no quede nada
        while (actual.getSiguiente() != null) {
            // Si el dato que se encuentra después es el que estábamos buscando hacemos esto... ("saltar posiciones")
            if (actual.getSiguiente().getDato().equals(dato)) {
                // [ A | [ B | [ C | * ]]] = [ A | [ C | *]] (si el elemento que queríamos quitar era el B)
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                return;
            }
            // Y si no, pues "iteramos"
            actual = actual.getSiguiente();
        }
    }

    // Para ver si un elemento está contenido en la lista...
    public boolean contiene(T dato) {
        // Se crea una copia de la cabeza...
        NodoLista<T> actual = cabeza;
        // Y se itera sbre los elementos de la lista hasta que no queden más por recorrer
        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                // Si se encuentra el dato que buscábamos, retornar el parámetro "true"
                return true;
            }
            // Si no, iterar continuamente estableciendo una nueva posición...
            actual = actual.getSiguiente();
        }
        return false;
    }

    // Básicamente es el getter de la cabeza...
    public NodoLista<T> getCabeza() {
        return cabeza;
    }

    // Y por qué no, se incluye un setter también
    public void setCabeza(NodoLista<T> cabeza) {
        this.cabeza = cabeza;
    }
}
