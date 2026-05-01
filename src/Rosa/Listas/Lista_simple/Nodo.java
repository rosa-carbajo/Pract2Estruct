package Listas.Lista_simple;

public class Nodo<T> {
    private T data;
    private Nodo<T> siguiente;


    public Nodo(T data)
    {
        this.data = data;
        this.siguiente = null;
    }

    public T getData(){return this.data;}

    public Nodo<T> getSiguiente(){return this.siguiente;}

    public void setData(T data) {this.data = data;}

    public void setSiguiente(Nodo<T> siguiente) {this.siguiente = siguiente;}


}
