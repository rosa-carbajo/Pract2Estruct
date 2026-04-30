package es.uah.matcomp.ed.el2.Arbol.AVL;

import es.uah.matcomp.ed.el2.Arbol.Arbol.ArbolBinarioDeBusqueda;
import es.uah.matcomp.ed.el2.Arbol.Arbol.Nodo_Arbol_Binario;

public class AVL<T extends Comparable<T>> extends ArbolBinarioDeBusqueda<T> {
    private Nodo_Arbol_Binario<T> root;

    //Constructores
    public AVL() {
        super();
    }
    public AVL(T dato) {
        super(dato);
    }
    AVL(Nodo_Arbol_Binario<T> root) {
        this.root = root;
    }

    @Override
    public void anadir(T elemento) {
        super.anadir(elemento);
        //Ahora comprobamos si está balanceado


        /*
        int balance = this.obtenerBalance();
        //Si no está balanceado entonces debemos rebalancearlo
        if (balance == -2){
            // Entonces el desbalance está a la izquierda


            return;
        }
        if (balance == 2){
            // Entonces el desbalance está a la derecha
            return;
        }
        public int obtenerBalance(){
        // El nodo estará balanceado si es -1, 0, -1
        // No estará balanceado si es 2 o -2
        // Además si es negativo sabemos que el desbalance está en la Izquierda, y si es positivo en la derecha
        return this.getSubArbolDerecha().getAltura() - this.getSubArbolIzquierda().getAltura();
        }
    }

}
