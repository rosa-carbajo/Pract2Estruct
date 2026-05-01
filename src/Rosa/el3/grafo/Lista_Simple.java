package es.uah.matcomp.ed.el3.grafo;

import Listas.Lista_simple.Nodo;

public class Lista_Simple <T>
{
    private Nodo<T> primero;
    // Cuando creo un objeto de Lista_Simple, Java reserva memoria en el heap para el objeto completo(la lista),
    // incluyendo un header interno y sus atributos.
    //Si los atributos son objetos de otra clase, lo que se almacena dentro del objeto es una referencia.
    //(no el objeto completo). El objeto real se almacena en otra zona del heap.
    // Si los atributos son tipos primitivos, el valor se almacena directamente dentro del objeto
    // Ejemplo: si Java usa 4 bytes para cada referencia (compressed oops típico en JVM de 64 bits),
    // y la clase tiene 2 atributos que son referencias, entonces el objeto tendrá al menos 8 bytes
    // dedicados a esas referencias, más el header del objeto y otros posibles bytes de alineación.

    private Nodo<T> ultimo;

    public Lista_Simple()
    {
        this.primero = null;
        this.ultimo = null;
    }

    //Añado un nodo al final
    public void anadir(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (this.primero == null) {
            this.primero = nuevo;
        } else {
            this.ultimo.setSiguiente(nuevo);
        }
        this.ultimo = nuevo;
    }

    //Borra el elemento que se quiere eliminar
    public boolean borrar(T dato) {
        if (primero == null) return false;

        // Caso 1: El elemento es el primero
        if (primero.getData().equals(dato)) {
            primero = primero.getSiguiente();
            if (primero == null)//Y si era el único
                ultimo = null;
            return true;
        }

        // Caso 2: Buscar en el resto de la lista
        Nodo<T> actual = primero;
        while (actual.getSiguiente() != null) {
            if (actual.getSiguiente().getData().equals(dato)) {
                // Si el que vamos a borrar es el último, actualizamos 'último'
                if (actual.getSiguiente() == ultimo) {
                    ultimo = actual;
                }
                // Saltamos el nodo (reconexión de referencias)
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    //Inserta manteniendo el orden ascendente.
    // Es una version de ordenación menos eficiente, pero se hace directamente
    /*
    public void insertar_order(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);

        // Caso 1: Lista vacía o dato menor que el primero
        if (primero == null || dato.compareTo(primero.getData()) < 0) {
            nuevo.setSiguiente(primero);
            primero = nuevo;
            if (ultimo == null) ultimo = primero;
            return;//Para que se salga
        }

        // Caso 2: Buscar la posición de inserción
        Nodo<T> actual = primero;
        while (actual.getSiguiente() != null &&
                actual.getSiguiente().getData().compareTo(dato) < 0) {
            actual = actual.getSiguiente();
        }

        // Insertar entre actual y actual.getSiguiente()
        nuevo.setSiguiente(actual.getSiguiente());
        actual.setSiguiente(nuevo);

        // Si se inserta al final, actualizar el puntero último
        if (nuevo.getSiguiente() == null) {
            ultimo = nuevo;
        }
    }
     */

    //Imprime la lista por pantalla
    public void visualizar() {
        if (primero == null) {
            System.out.println("[]");
            return;
        }
        Nodo<T> act = primero;
        System.out.print("[ ");
        while (act != null) {
            System.out.print(act.getData() + (act.getSiguiente() != null ? ", " : ""));
            act = act.getSiguiente();
        }
        System.out.println(" ]");
    }

    //MODIFICADO
    //Busca si el dato esta o no, si esta devuelve la dirección del act, si no, devuelve nulo
    public Nodo<T> buscar(T dato) {
        Nodo<T> act = primero;
        while (act != null) {
            if (act.getData().equals(dato))
                return act;
            act = act.getSiguiente();
        }
        return null;
    }

    // Método necesario para obtener el inicio de la lista y poder recorrerla
    public Nodo<T> getPrimero() {
        return primero;
    }
}