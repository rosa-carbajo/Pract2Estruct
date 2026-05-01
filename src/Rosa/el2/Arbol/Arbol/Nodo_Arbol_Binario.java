package es.uah.matcomp.ed.el2.Arbol.Arbol;

public class Nodo_Arbol_Binario<T> {
    private T dato;
    private Nodo_Arbol_Binario<T> izq;
    private Nodo_Arbol_Binario<T> drch;

    //---CONSTRUCTOR---
    public Nodo_Arbol_Binario(T dato) {
        this.dato = dato;
        this.drch = null;
        this.izq = null;
    }

    //----GETTERS Y SETTERS---
    public T getDato() {
        return dato;
    }
    public void setDato(T dato) {
        this.dato = dato;
    }
    public Nodo_Arbol_Binario<T> getNodoDrch() {
        return drch;
    }
    public void setNodoDrch(Nodo_Arbol_Binario<T> drch) {
        this.drch = drch;
    }
    public Nodo_Arbol_Binario<T> getNodoIzq() {
        return izq;
    }
    public void setNodoIzq(Nodo_Arbol_Binario<T> izq) {
        this.izq = izq;
    }

    //---GET GRADO---
    /**
     * Devuelve el grado del nodo, es decir, el número de hijos que tiene.
     * En un árbol binario el grado máximo es 2.
     * <p>
     * @return 0 si es hoja, 1 si tiene un hijo, 2 si tiene dos hijos
     */
    public int getGrado() {
        // Si ambos hijos existen, el nodo tiene grado 2 (nodo interno completo)
        if (this.izq != null && this.drch != null)
            return 2;
            // Si ambos hijos son nulos, el nodo es una hoja y tiene grado 0
        else if (this.izq == null && this.drch == null)
            return 0;
            // Si solo uno de los dos hijos existe, el grado es 1
        else
            return 1;
    }

    //--TO STRING---
    public String toString(){
        return dato.toString();
    }
}
