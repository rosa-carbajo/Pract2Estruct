package es.uah.matcomp.ed.el2.Arbol.Arbol;

import Listas.Lista_simple.Lista_Simple;

public interface ArbolBinarioDeBusqueda_Interfaz<T extends Comparable<T>> {
    // Constructores del Arbol
    /*
    void ArbolBinarioDeBusqueda();
    void ArbolBinarioDeBusqueda(T dato);
    void ArbolBinarioDeBusqueda(Nodo_Arbol_Binario<T> nodo);
    */

    Nodo_Arbol_Binario<T> getRaiz();
    ArbolBinarioDeBusqueda<T> getSubArbolIzquierda();
    ArbolBinarioDeBusqueda<T>  getSubArbolDerecha();
    void anadir(T dato);


    Integer getGrado();
    Integer getAltura();
    Lista_Simple<T> getListaDatosNivel(int nivel);
    Lista_Simple<T> getCamino(T dato);
    Lista_Simple<T> getCaminoRecursivo(T dato, Nodo_Arbol_Binario<T> nodo, Lista_Simple<T> lista);

    Boolean isArbolCasiCompleto();
    Boolean isArbolHomogeneo();
    Boolean isArbolCompleto();


    Lista_Simple<T> getListaOrdenCentral();
    Lista_Simple<T> getListaPostOrden();
    Lista_Simple<T> getListaPreOrden();






}
