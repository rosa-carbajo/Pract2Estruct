package clasesgrafo;

import extra.Lista;
import extra.NodoLista;

public class Conjunto<T> {
    // El conjunto es una lista de 'X', tipo de elementos
    private Lista<T> elementos;

    // Crear un conjunto equivale a crear una lista
    public Conjunto() {
        elementos = new Lista<>();
    }

    // Si queremos insertar algo en el conjunto
    public void insertar(T elem) {
        // Primero miramos si contiene al elemento que se quiere insertar para evitar duplicados
        if (!contiene(elem)) {
            // Si no lo tiene, lo insertamos en la lista con el metodo correspondiente a su clase
            elementos.insertar(elem);
        }
    }

    // Construimos un metodo que permita verificar si un elemento se encuentra o no en el conjunto
    public boolean contiene(T elem) {
        // Se crea una copia del nodo de la cabeza de la lista de los elementos
        NodoLista<T> actual = elementos.getCabeza();

        // Mientras no esté vacía (no queden elementos por iterar)
        while (actual != null) {
            // Comprobamos uno a uno si el dato en la posición en la que estamos es igual que el de "T elemento"
            if (actual.getDato().equals(elem)) {
                // Si esto es cierto hay que devolver que sí es verdad y salirnos delbucle
                return true;
            }
            // Si no, pues seguimos iterando, actualizando el elemento a la siguiente posición
            actual = actual.getSiguiente();
        }
        // Si al final no quedan elementos por recorrer y no ha pasado nada, pues retornamos que es falso. que no está
        return false;
    }

    // Para eliminar un elemento, equivale a llamar al metodo correspondiente para eliminar un elemento en la clase Lista
    public void eliminar(T elem) {
        elementos.eliminar(elem);
    }

    // Para ver el tamaño de la lista se hace lo siguiente (ya que no hemos establecido el parámetro size)
    public int size() {
        // Instanciamos un contador en 0 y creamos una copia del nodo de la cabeza de la lista
        int contador = 0;
        NodoLista<T> actual = elementos.getCabeza();

        // Iteramos como en el metodo contiene(), solo que en este caso aumentando el contador a 1 por cada elemento que se va recorriendo
        while (actual != null) {
            contador = contador + 1;
            actual = actual.getSiguiente();
        }
        // Una vez se ha terminado de recorrer la lista, es decir, el conjunto, se devuelve el contador
        return contador;
    }

    // Para ver si es un conjunto vacío, ¡solo equivale a ver si la cabeza de la lista está vacía o no!
    public boolean isEmpty() {
        return elementos.getCabeza() == null;
    }

    // Getter de la lista de los elementos
    public Lista<T> getElementos() {
        return elementos;
    }

    // Setter de la lista de los elementos
    public void setElementos(Lista<T> elementos) {
        this.elementos = elementos;
    }
}