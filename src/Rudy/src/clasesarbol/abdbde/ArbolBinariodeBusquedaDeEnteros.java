package clasesarbol.abdbde;
import clasesarbol.abdb.ArbolBinarioDeBusqueda;
import clasesarbol.abdb.NodoArbol;

public class ArbolBinariodeBusquedaDeEnteros extends ArbolBinarioDeBusqueda<Integer> {
    // Se extiende la clase ABDB porque el ABDBDE es lo mismo pero como enteros como el parámetro característico del árbol
    public NodoArbol<Integer> getRaiz() {
        // Devuelve la raíz del árbol asociado, básicamente, es un getter
        return super.getRaiz();
    }

    // Constructor de la subclase por defecto
    public ArbolBinariodeBusquedaDeEnteros() {
        super();
    }

    // Constructor de la subclase pero con raíz
    public ArbolBinariodeBusquedaDeEnteros(NodoArbol<Integer> raiz) {
        super(raiz);
    }

    // Se añade la función que se ha pedido para el TAD de esta subclase (es recursiva)
    public int getSuma() {
        // ¡Llamada recursiva empezando desde la raíz del árbol!
        return sumaRec(getRaiz());
    }

    // Función auxiliar que permite la recursividad
    public int sumaRec(NodoArbol<Integer> n) {
        // Si no hay nada en el nodo/árbol, equivale a sumar 0 (siempre al principio o al final de cada ciclo de subárbol)
        if (n == null) {
            return 0;
        }
        // Si no, obtenemos el valor del nodo de dicha posición y vamos sumando con las bifurcaciones tanto a la izquierda como a la derecha
        return n.getInicial().getValor() + sumaRec(n.getIzquierda()) + sumaRec(n.getDerecha());
    }

    // Esto permite imprimir la suma del árbol cada vez que queramos...
    @Override
    public String toString() {
        return "Suma del árbol: " + getSuma();
    }
}
