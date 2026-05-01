package es.uah.matcomp.ed.el2.Arbol.AVL;

import es.uah.matcomp.ed.el2.Arbol.Arbol.ArbolBinarioDeBusqueda;
import es.uah.matcomp.ed.el2.Arbol.Arbol.Nodo_Arbol_Binario;

//Self-balancing binary search tree
public class AVL<T extends Comparable<T>> extends ArbolBinarioDeBusqueda<T> {

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

    //------AÑADIR -------

    //-----METODO PARA EL USUARIO----
    /**
     * Inserta un nuevo elemento en el árbol de forma balanceada.
     * <p>
     * Este es el método de entrada para el usuario. Inicia el proceso recursivo
     * de inserción y asegura que, tras la operación, el árbol mantenga su
     * propiedad de equilibrio AVL actualizando la raíz si es necesario.
     * <p>
     * @param elemento El valor de tipo {@code T} que se desea integrar en el árbol.
     */
    @Override
    public void anadir(T elemento) {
        root = anadirRecursivoYReequilibrio(root, elemento);
    }

    //----METODO VERDADERO DE AÑADIR-----
    /**
     * Realiza la inserción recursiva de un valor y reequilibra el subárbol resultante.
     * <p>
     * El método desciende por el árbol comparando el valor con los nodos actuales hasta
     * encontrar una posición vacía (null). Durante la fase de retorno de la recursión
     * (en la "subida"), se invoca al método de reequilibrio en cada nodo visitado para
     * corregir posibles descompensaciones en la altura del árbol.
     * </p>
     * @param raiz_actual El nodo en el que se está evaluando la inserción actualmente.
     * @param valor       El elemento que se pretende añadir al árbol.
     * @return La referencia al nodo actual (posiblemente una nueva raíz tras el reequilibrio).
     */
    private Nodo_Arbol_Binario<T> anadirRecursivoYReequilibrio(Nodo_Arbol_Binario<T> raiz_actual, T valor)
    {
        //Caso Base: El árbol actual es vacío, lo que implica que ahí es donde vamos a añadir el valor que desea añadir el usuario
        if (raiz_actual == null)
        {
            //Hago que el actual ahora apunte a ese nuevo nodo
            raiz_actual = new Nodo_Arbol_Binario<>(valor);
        }

        //Opción A: El valor es menor que el root del árbol actual y, por tanto, lo mandamos a la izquierda
        if (raiz_actual.getDato().compareTo(valor) > 0)
        {
            //Como el valor es menor, queremos modificar el atributo izquierdo del nodo actual(raiz_actual) para que se recorra el izquierdo
            raiz_actual.setNodoIzq(anadirRecursivoYReequilibrio(raiz_actual.getNodoIzq(), valor));
        }
        //Opción B: El valor es mayor que el root del árbol actual y por eso lo mandamos (después de crearlo) al árbol de la derecha
        if (raiz_actual.getDato().compareTo(valor) < 0)
        {
            raiz_actual.setNodoDrch(anadirRecursivoYReequilibrio(raiz_actual.getNodoDrch(), valor));
        }
        // Si el elemento es igual al actual, pues no hacemos nada

        //Finalmente, en la subida vamos reequilibrando los hijos
        //Comprobamos este nodo y corregimos si hace falta
        return reequilibrio(raiz_actual);
    }


    //------METODO PARA REEQUILIBRAR EL ÁRBOL-----
    /**
     * Evalúa y restablece el equilibrio de un nodo siguiendo los criterios de un árbol AVL.
     * <p>
     * Cuando decimos que un árbol está equilibrado implica que
     * las alturas de los subárboles no difieren en más de una unidad
     * <p>
     * En caso de que se encuentre desequilibrado, el método analiza cuatro escenarios:
     *   Caso 1: Desequilibrio Exterior Derecho (Rotación Derecha-Derecha)
     *   Caso 2: Desequilibrio Interior Derecho (Rotación Derecha-Izquierda)
     *   Caso 3: Desequilibrio Exterior Izquierdo (Rotación Izquierda-Izquierda)
     *   Caso 4: Desequilibrio Interior Izquierdo (Rotación Izquierda-Derecha)
     * Si se encuentra equilibrado no hace nada.
     * @param nodo El nodo que se va a verificar y equilibrar.
     * @return El nodo (o la nueva raíz del subárbol) una vez aplicado el equilibrio necesario.
     **/
    private Nodo_Arbol_Binario<T> reequilibrio(Nodo_Arbol_Binario<T> nodo) {
        int balance = obtenerBalance(nodo);


        //Tenemos 2 posibilidades, o está equilibrado o no
        //Si está equilibrado, lo dejamos como está.

        //Si no está equilibrado distinguimos 4 casos:

        ///Caso 1:Rotación Simple Derecha (Caso Izquierda-Izquierda)
        //Tenemos que el nodo principal tiene a su izquierda otro nodo (nodo central) y a su vez este nodo tiene otro nodo a su izquierda.
        //Este caso se podría entonces reducir a que:
        // - El equilibrio del nodo principal es -2 (porque el desequilibrio está en la izquierda)
        // - El equilibrio del nodo central es -1 (porque hay más nodos en el lado izquierdo que el derecho)
        //   o 0 (que implicaría que sus subárboles izquierdos y derechos tiene la misma altura)

        if (balance == -2 && obtenerBalance(nodo.getNodoIzq()) <= 0)
        // SOLUCIÓN:Rotamos a la derecha
            return rotarDerecha(nodo);


        ///Caso 2: Rotación doble Derecha-Izquierda (Caso Derecha Izquierda)
        // Aquí tendríamos que el nodo principal tiene a su derecha otro nodo(nodo central) y este tiene un nodo a su izquierda.
        // Entonces al calcular los balances tendríamos que:
        // - El equilibrio del nodo principal es +2 (porque el desequilibrio está en la derecha)
        // - El equilibrio del nodo central es -1 (porque hay más nodos en el lado izquierdo que el derecho)

        if (balance == 2 && obtenerBalance(nodo.getNodoDrch()) < 0) {
            // SOLUCIÓN: Rotamos el nodo central a la izquierda lo cual resultaría en la estructura de Derecha-Derecha,
            // que se resuelve con una rotación izquierda
            nodo.setNodoDrch(rotarDerecha(nodo.getNodoDrch()));
            return rotarIzquierda(nodo);
        }

        ///Caso 3: Rotación Simple Izquierda (Caso Derecha-Derecha)
        // En este caso tenemos que el nodo principal tiene a su derecha otro nodo (que vamos a llamarlo nodo central)
        // y a su vez el nodo central tiene otro nodo a la derecha.
        // Este caso lo podemos reducir a que:
        // - El equilibrio del nodo principal es +2 (porque el desequilibrio está en la derecha)
        // - El equilibrio del nodo central es +1 (porque hay más nodos en el lado derecho que el izquierdo)
        //   o 0 (que implicaría que sus subárboles izquierdos y derechos tiene la misma altura)

        if (balance == 2 && obtenerBalance(nodo.getNodoDrch()) >= 0)
            // SOLUCIÓN:Rotamos a la izquierda
            return rotarIzquierda(nodo);


        ///Caso 4: Rotación doble Izquierda-Derecha (Caso Izquierda-Derecha)
        //Tenemos que el nodo principal tiene a su izquierda otro nodo (nodo central) y este tiene un nodo a su derecha
        //Este caso se reduce a:
        // - El equilibrio del nodo principal es -2 (porque el desequilibrio está en la izquierda)
        // - El equilibrio del nodo central es +1 (porque hay más nodos en el lado derecho que el izquierdo)

        if (balance == -2 && obtenerBalance(nodo.getNodoIzq()) > 0) {
            // SOLUCIÓN: Primero rotamos a la derecha el nodo intermedio lo cual resultaría en la estructura del caso anterior,
            // que se resuelve con una rotación derecha
            nodo.setNodoIzq(rotarIzquierda(nodo.getNodoIzq()));
            return rotarDerecha(nodo);
        }

        return nodo; // devolvemos el nodo ya equilibrado
    }


    //----METODO QUE CALCULA EL EQUILIBRIO DE UN NODO----
    /**
     * Calcula el balance de un nodo concreto.
     *    - El nodo estará balanceado si es -1, 0, -1
     *    - No estará balanceado si es 2 o -2
     * <p>
     * Además si es negativo sabemos que el desbalance está en la Izquierda
     * (porque la altura izquierda es mayor que la derecha), y si es positivo en la derecha
     * <p>
     * @param nodo nodo del que calcular el balance
     * @return altura derecha - altura izquierda
     */
    public int obtenerBalance(Nodo_Arbol_Binario<T> nodo) {
        if (nodo == null) {
            return 0;
        }
        ArbolBinarioDeBusqueda<T> Izq = new AVL<>(nodo.getNodoIzq());
        ArbolBinarioDeBusqueda<T> Drch = new AVL<>(nodo.getNodoDrch());
        return Drch.getAltura() - Izq.getAltura();

    }


    //---- ROTACIÓN HACIA LA IZQUIERDA----
    /**
     * Realiza una rotación simple a la izquierda sobre el nodo proporcionado.
     * @param raiz_vieja El nodo actual que se identifica como raíz del subárbol a rotar.
     * @return La nueva raíz del subárbol después de la rotación.
     */
    private Nodo_Arbol_Binario<T> rotarIzquierda(Nodo_Arbol_Binario<T> raiz_vieja) {
        //Definimos la nueva raíz como el nodo derecho
        Nodo_Arbol_Binario<T> nuevaRaiz = raiz_vieja.getNodoDrch();
        // Luego ponemos en el nodo derecho de la antigua raíz(que es lo que lo conectaría a la nueva raíz)
        // el nodo izquierdo de la nueva raíz.
        // Esto preserva el orden jerárquico (ya que ese nodo(nodo hijo izquierdo de nueva raiz) es mayor que la antigua raíz pero menor que la nueva) y
        // desconecta ambos nodos principales para evitar referencias circulares.
        raiz_vieja.setNodoDrch(nuevaRaiz.getNodoIzq());
        //Finalmente, seteamos el nodo izquierdo de la nueva raíz como la antigua raíz(raiz_vieja)
        nuevaRaiz.setNodoIzq(raiz_vieja);
        return nuevaRaiz;
    }


    //---- ROTACIÓN HACIA LA DERECHA----
    /**
     * Realiza una rotación simple a la derecha sobre el nodo proporcionado.
     * @param raiz_vieja El nodo actual que se identifica como raíz del subárbol a rotar.
     * @return La nueva raíz del subárbol después de la rotación.
     */
    private Nodo_Arbol_Binario<T> rotarDerecha(Nodo_Arbol_Binario<T> raiz_vieja) {
        // Definimos la nueva raíz como el nodo Izquierdo
        Nodo_Arbol_Binario<T> nuevaRaiz = raiz_vieja.getNodoIzq();
        //Luego ponemos a la izquierda de la antigua raíz el nodo derecho de la nueva raíz
        // (por lo mismo que la rotación izquierda:
        // - para preservar el orden jerárquico (ya que ese nodo es menor que la antigua raíz(por eso se pone a la izquierda),
        //   pero mayor que la nueva (que no violaría nada porque la antigua raíz irá a la derecha de la nueva))
        // - evitar la formación de un grafo)
        raiz_vieja.setNodoIzq(nuevaRaiz.getNodoDrch());
        //Finalmente, ponemos la antigua raíz como el nodo derecho de la nueva raíz (porque es mayor)
        nuevaRaiz.setNodoDrch(raiz_vieja);
        return nuevaRaiz;
    }

}
