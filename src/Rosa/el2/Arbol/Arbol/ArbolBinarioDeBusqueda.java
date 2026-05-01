package es.uah.matcomp.ed.el2.Arbol.Arbol;
import Listas.Lista_simple.Lista_Simple;

import static java.lang.Math.max;

public class ArbolBinarioDeBusqueda<T extends Comparable<T>> {
    protected es.uah.matcomp.ed.el2.Arbol.Arbol.Nodo_Arbol_Binario<T> root;

    //--------CONSTRUCTORES---------------
    public ArbolBinarioDeBusqueda() {
        this.root = null;
    }
    public ArbolBinarioDeBusqueda(T valor) {
        this.root = new es.uah.matcomp.ed.el2.Arbol.Arbol.Nodo_Arbol_Binario<>(valor);
    }

    // Este constructor lo hago protected para evitar que el usuario pueda alterar la estructura del árbol y que sus hijos puedan
    ArbolBinarioDeBusqueda(Nodo_Arbol_Binario<T> nodo) {
        this.root = nodo;
    }


    //-------- GET SUBÁRBOLES ------------------

    //---SUBÁRBOL IZQUIERDO---
    /**
     * Devuelve el subárbol izquierdo del árbol actual como un nuevo
     * {@code ArbolBinarioDeBusqueda}.
     * Si la raíz es nula o no tiene hijo izquierdo, devuelve un árbol vacío.
     *<p>
     * @return El subárbol izquierdo.
     */
    public ArbolBinarioDeBusqueda<T> getSubArbolIzquierda()
    {
        // ¿Si la raíz es nula o no tiene hijo izquierdo, devolvemos un árbol vacío
        // Estoy pensando que es innecesario el que no tenga hijo izquierdo no? En plan la raíz se crea con lo que le pases
        //Luego si le pasas un null no ocurriría nada
        if (this.root == null || this.root.getNodoIzq() == null) {
            return new ArbolBinarioDeBusqueda<>((T) null);
        }

        // Creamos y devolvemos un nuevo objeto Árbol cuyo inicio es el hijo izquierdo
        return new ArbolBinarioDeBusqueda<>(this.root.getNodoIzq());
    }

    //---SUBÁRBOL DERECHO---
    /**
     * Devuelve el subárbol derecho del árbol actual como un nuevo
     * {@code ArbolBinarioDeBusqueda}.
     * Si la raíz es nula o no tiene hijo derecho, devuelve un árbol vacío.
     * <p>
     * @return El subárbol derecho.
     */
    public ArbolBinarioDeBusqueda<T> getSubArbolDerecha()
    {
        // Si la raíz es nula o no tiene hijo derecho, devolvemos un árbol vacío
        // La misma duda que con el izquierdo
        if (this.root == null || this.root.getNodoDrch() == null) {
            return new ArbolBinarioDeBusqueda<>((T) null);
        }

        // Creamos y devolvemos un nuevo objeto Árbol cuyo inicio es el hijo derecho
        return new ArbolBinarioDeBusqueda<>(this.root.getNodoDrch());
    }




    //------- AÑADIR ---------------

    // --- FUNCIÓN PARA EL USUARIO----
    /**
     * Inserta un nuevo elemento en el árbol binario de búsqueda manteniendo el orden.
     * Si el elemento ya existe, no se inserta (no se permiten duplicados).
     *
     * @param element El valor de tipo {@code T} que se desea insertar en el árbol.
     **/
    public void anadir(T element)
    {
        // Hacemos una función pública donde el usuario solo tiene que mandar el elemento

        root = anadirRecursivo(root, element);
        // Y la mandamos a uno recursivo donde le pasamos el nodo y el elemento

        //El Balanceo lo hacemos en el árbol AVL

    }

    // ---FUNCIÓN VERDADERA---
    // Me he dado cuenta de que al usar y crear árboles en la estructura (usando getSubArbolIzquierda() y getSubArbolDerecha())
    // para la recursividad es increíblemente ineficiente,
    // debido a que se pueden llegar a crear cientos de objetos temporales para moverte por el árbol.
    // Por tanto, simplemente pasarle la dirección del nodo es suficiente, ya que al final lo que añadimos es el dato del nodo,
    // para ello no necesito crear ningún árbol nuevo.
    /**
     * Metodo recursivo auxiliar que realiza la inserción de un valor en el subárbol
     * cuya raíz es {@code raiz_actual}.
     * <p>
     * Desciende por el árbol comparando el valor con cada nodo hasta encontrar
     * una posición vacía (null), donde crea el nuevo nodo. Si el valor ya existe,
     * no realiza ninguna acción (no se permiten duplicados).
     * <p>
     * @param raiz_actual El nodo raíz del subárbol en el que se está evaluando la inserción.
     * @param valor       El elemento que se desea añadir.
     * @return El nodo raíz del subárbol, ya con el nuevo elemento insertado.
     */
    private Nodo_Arbol_Binario<T> anadirRecursivo(Nodo_Arbol_Binario<T> raiz_actual, T valor)
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
            raiz_actual.setNodoIzq(anadirRecursivo(raiz_actual.getNodoIzq(), valor));
        }

        //Opción B: El valor es mayor que el root del árbol actual y por eso lo mandamos (después de crearlo) al árbol de la derecha
        if (raiz_actual.getDato().compareTo(valor) < 0)
        {
            raiz_actual.setNodoDrch(anadirRecursivo(raiz_actual.getNodoDrch(), valor));

        }
        // Si el elemento es igual al actual, pues no hacemos nada
        return raiz_actual;
    }


    //---- DEVOLVER GRADO: Tiene más sentido calcular el grado por nodo. Véase Nodo_Arbol_Binario
    //---- DEVOLVER ALTURA-----
    /**
     * Devuelve la altura del árbol, definida como el número máximo de niveles
     * desde la raíz hasta el nodo hoja más alejado.
     * Un árbol vacío tiene altura 0. Un árbol con un único nodo tiene altura 1.
     *
     * @return La altura del árbol como entero.
     */
    public int getAltura(){
        //También se hace de manera recursiva
        if (this.root == null)
            return 0;
        //Creo los árboles izquierdos y derechos para así recorrer cada uno
        ArbolBinarioDeBusqueda<T> Izq = this.getSubArbolIzquierda();
        ArbolBinarioDeBusqueda<T> Drch = this.getSubArbolDerecha();

        return 1 + max(Izq.getAltura(),Drch.getAltura());
    }

    //----LISTA DE DATOS DE UN NIVEL----

    // ---METODO PARA EL USUARIO---
    public Lista_Simple<T> getListaDatosNivel(int nivelObjetivo) {
        Lista_Simple<T> listaNivel = new Lista_Simple<>();
        getListaDatosNivelRecursivo(root,nivelObjetivo, listaNivel );
        //No necesito que la función recursiva devuelva nada porque como en Java se trabaja con referencias,
        // lo que añado a la lista durante la recursividad, se queda en la lista original

        return listaNivel;
    }

    //----METODO VERDADERO----
    /**
     * Método recursivo auxiliar que recorre el árbol en busca de los nodos
     * pertenecientes a un nivel concreto y los añade a la lista proporcionada.
     * <p>
     * Desciende por el árbol decrementando {@code nivel_restante} en cada llamada.
     * Cuando llega a 0, añade el nodo actual a la lista. Si el nodo es nulo,
     * termina la rama sin añadir nada.
     *
     * @param raiz_actual    El nodo que se está evaluando en la iteración actual.
     * @param nivel_restante Niveles que quedan por descender hasta alcanzar el nivel objetivo.
     * @param lista          La lista donde se acumulan los datos encontrados en el nivel.
     */
    private void getListaDatosNivelRecursivo(Nodo_Arbol_Binario<T> raiz_actual, int nivel_restante, Lista_Simple<T> lista ) {
        //Caso Base 1: El nodo podría ser nulo que implica no tener más hijos. Entonces para evitar problemas no hacemos nada
        if (raiz_actual == null)
            return;

        //Caso Base 2: Nos encontramos en el nivel deseado así que añadimos el nodo actual a la lista
        if (nivel_restante == 0){
            lista.anadir(raiz_actual.getDato());
        }

        //Caso Recursivo: En caso de que no sea el nivel restante, obligo a que en los subárboles se encuentre el nivel y cuando se encuentre
        // referirnos al caso base 2
        getListaDatosNivelRecursivo(raiz_actual.getNodoIzq(), nivel_restante - 1, lista);
        getListaDatosNivelRecursivo(raiz_actual.getNodoDrch(), nivel_restante - 1, lista);

    }


    //------------GET CAMINO--------------------

    //PRIMERO CREAMOS UN METODO QUE COMPRUEBE QUE EL DATO ESTÁ EN EL ÁRBOL

    //----METODO PARA EL USUARIO----
    /**
     * Comprueba si el árbol contiene el valor indicado.
     *
     * @param dato El valor de tipo {@code T} que se desea buscar.
     * @return {@code true} si el valor está en el árbol, {@code false} en caso contrario.
     */
    public boolean contiene(T dato){
        return contiene_recursivo(dato, this.root);
    }

    //----METODO VERDADERO-----
    /**
     * Método recursivo auxiliar que comprueba si un valor está contenido
     * en el subárbol cuya raíz es {@code raiz_actual}.
     * <p>
     * Aprovecha la propiedad de orden del árbol binario de búsqueda para
     * descartar ramas enteras en cada paso, logrando una búsqueda en O(log n)
     * en árboles balanceados.
     *
     * @param dato        El valor que se desea localizar.
     * @param raiz_actual El nodo raíz del subárbol en el que se busca.
     * @return {@code true} si el valor se encuentra en el subárbol, {@code false} en caso contrario.
     */
    private boolean contiene_recursivo(T dato, Nodo_Arbol_Binario<T> raiz_actual) {
        //Caso Base: El dato no está contenido en la lista, lo que implicaría que se ha llegado a un nodo nulo
        if(raiz_actual == null){
            return false;
        }
        //Caso Base: El dato es igual al dato del nodo, luego está contenido en el árbol
        if(dato.compareTo(raiz_actual.getDato()) == 0){
            return true;
        }
        //Casos Recursivos
        //Opción A: El dato es menor que el dato de raíz_actual
        if(raiz_actual.getDato().compareTo(dato) > 0){
            return contiene_recursivo(dato, raiz_actual.getNodoIzq());
        }
        //Opción B: El dato es mayor que el dato de raíz_actual
        return contiene_recursivo(dato, raiz_actual.getNodoDrch());

    }
    // FUNCIÓN QUE DEVUELVE EL CAMINO

    //-----METODO PARA EL USUARIO----
    /**
     * Devuelve la lista de nodos que forman el camino desde la raíz hasta el nodo
     * que contiene el valor indicado, sin incluir el propio nodo destino.
     * Si el valor no existe en el árbol, devuelve una lista vacía.
     *
     * @param dato El valor de tipo {@code T} al que se desea llegar.
     * @return Una {@code Lista_Simple<T>} con los nodos recorridos hasta el destino.
     */
    public Lista_Simple<T> getCamino(T dato) {
        Lista_Simple<T> Camino = new Lista_Simple<>();
        if (this.contiene(dato)) {
            getCaminoRecursivo(dato, root, Camino);
        }
        //De esta manera si dato no está en el árbol devolvemos una lista vacía
        return Camino;
    }

    //----METODO VERDADERO----
    /**
     * Método recursivo auxiliar que construye el camino desde {@code raiz_actual}
     * hasta el nodo que contiene {@code dato}, añadiendo cada nodo visitado a la lista.
     * <p>
     * Se asume que el dato existe en el árbol (verificado previamente por {@link #contiene(Comparable)}).
     * El nodo destino no se incluye en la lista, solo los nodos intermedios recorridos.
     *
     * @param dato        El valor destino al que se quiere llegar.
     * @param raiz_actual El nodo desde el que se continúa la búsqueda del camino.
     * @param lista       La lista donde se acumulan los nodos del camino recorrido.
     */
    private void getCaminoRecursivo(T dato, Nodo_Arbol_Binario<T> raiz_actual, Lista_Simple<T> lista){
        //Como sabemos que el dato está en el árbol, no es necesario un Caso en el que el nodo sea nulo

        //Caso Base: El dato es igual al dato del nodo
        if(raiz_actual.getDato().compareTo(dato) ==0){
            //Cuando lleguemos al dato, paramos
            return;
        }

        //Casos Recursivos
        //Opción A: El dato es menor que el dato de raíz_actual, luego vamos al nodo izquierdo
        if(raiz_actual.getDato().compareTo(dato) >0){
            lista.anadir(raiz_actual.getDato());
            getCaminoRecursivo(dato, raiz_actual.getNodoIzq() , lista);
        }
        //Opción B: El dato es mayor que el dato de raíz_actual, luego vamos al nodo derecho
        if(raiz_actual.getDato().compareTo(dato) < 0){
            lista.anadir(raiz_actual.getDato());
            getCaminoRecursivo(dato, raiz_actual.getNodoDrch(),  lista);
        }
    }


    //----------DISTINTAS IMPRESIONES DEL ÁRBOL------------------

    //---IN ORDER----
    /**
     * Devuelve una lista con los elementos del árbol en recorrido en orden central (in-order):
     * Se visita primero el nodo izquierdo, luego la raíz y por último el nodo derecho
     * <p>
     * @return Una {@code Lista_Simple<T>} con los elementos en orden central.
     */
    public Lista_Simple<T> getListaInOrden(){
        Lista_Simple<T> lista = new Lista_Simple<>();
        getListaInOrdenRecursivo(root, lista);
        return lista;
    }
    /**
     * Método recursivo auxiliar que realiza el recorrido en orden central (in-order)
     * del subárbol cuya raíz es {@code raiz_actual}.
     * <p>
     * Visita el subárbol izquierdo, luego el nodo actual y finalmente el subárbol derecho.
     * Si el nodo es nulo, termina sin hacer nada.
     *
     * @param raiz_actual El nodo raíz del subárbol que se está recorriendo.
     * @param lista       La lista donde se acumulan los elementos en orden central.
     */
    private void getListaInOrdenRecursivo(Nodo_Arbol_Binario<T> raiz_actual, Lista_Simple<T> lista) {
        if(raiz_actual != null) {
            getListaInOrdenRecursivo(raiz_actual.getNodoIzq(), lista);
            lista.anadir(raiz_actual.getDato());
            getListaInOrdenRecursivo(raiz_actual.getNodoDrch(), lista);
        }
        //En caso de que sea nulo no hacemos nada
    }


    //---POST ORDER----
    /**
     * Devuelve una lista con los elementos del árbol en recorrido post-orden:
     * Se visita primero el nodo izquierdo, luego el nodo derecho y por último la raíz
     * <p>
     * @return Una {@code Lista_Simple<T>} con los elementos en post-orden.
     */
    public Lista_Simple<T> getListaPostOrden(){
        Lista_Simple<T> lista = new Lista_Simple<>();
        getListaPostOrdenRecursivo(root, lista);
        return lista;
    }
    /**
     * Método recursivo auxiliar que realiza el recorrido en post-orden
     * del subárbol cuya raíz es {@code raiz_actual}.
     * <p>
     * Visita el subárbol izquierdo, luego el subárbol derecho y finalmente el nodo actual.
     * Si el nodo es nulo, termina sin hacer nada.
     * <p>
     * @param raiz_actual El nodo raíz del subárbol que se está recorriendo.
     * @param lista       La lista donde se acumulan los elementos en post-orden.
     */
    private void getListaPostOrdenRecursivo(Nodo_Arbol_Binario<T> raiz_actual, Lista_Simple<T> lista) {
        if(raiz_actual != null){
            getListaPostOrdenRecursivo(raiz_actual.getNodoIzq(), lista);
            getListaPostOrdenRecursivo(raiz_actual.getNodoDrch(), lista);
            lista.anadir(raiz_actual.getDato());
        }
        //En caso de que sea nulo no hacemos nada
    }


    //----PRE ORDER-----
    /**
     * Devuelve una lista con los elementos del árbol en recorrido pre-orden:
     * Se visita primero la raíz, luego el nodo izquierdo y por último el nodo derecho
     * <p>
     * @return Una {@code Lista_Simple<T>} con los elementos en pre-orden.
     */
    public Lista_Simple<T> getListaPreOrden(){
        Lista_Simple<T> lista = new Lista_Simple<>();
        getListaPreOrdenRecursivo(root, lista);
        return lista;

    }
    /**
     * Método recursivo auxiliar que realiza el recorrido en pre-orden
     * del subárbol cuya raíz es {@code raiz_actual}.
     * <p>
     * Visita primero el nodo actual, luego el subárbol izquierdo y finalmente el subárbol derecho.
     * Si el nodo es nulo, termina sin hacer nada.
     *
     * @param raiz_actual El nodo raíz del subárbol que se está recorriendo.
     * @param lista       La lista donde se acumulan los elementos en pre-orden.
     */
    private void getListaPreOrdenRecursivo(Nodo_Arbol_Binario<T> raiz_actual, Lista_Simple<T> lista) {
        //Caso Recursivo: Si el nodo no es nulo lo añadimos a la lista y de forma recursiva le pasamos su hijo izquierdo y su hijo derecho
        if (raiz_actual != null){
            lista.anadir(raiz_actual.getDato());
            getListaPreOrdenRecursivo(raiz_actual.getNodoIzq(), lista);
            getListaPreOrdenRecursivo(raiz_actual.getNodoDrch(), lista);

        }
        //En caso de que sea nulo no hacemos nada

    }

    //---------------TIPO DE ÁRBOL------------------------

    // ¿ÁRBOL HOMOGÉNEO?
    /**
     * Comprueba si el árbol es homogéneo.
     * Aquel árbol donde sus nodos solo tienen grado 2 o 0(es decir cada nodo tiene dos hijos,
     * y si no tiene dos hijos es una hoja)
     * @return true si el árbol es homogéneo, false en caso contrario
     */
    public boolean isArbolHomogeneo(){
        return isArbolHomogeneoRecursivo(root);
    }
    /**
     * Método recursivo auxiliar que recorre el árbol comprobando si cada nodo
     * cumple la condición de homogeneidad.
     *
     * @param raiz_actual nodo que se está evaluando en la iteración actual
     * @return true si el subárbol con raíz en raíz_actual es homogéneo, false si
     *         algún nodo tiene exactamente un hijo
     */
    private boolean isArbolHomogeneoRecursivo(Nodo_Arbol_Binario<T> raiz_actual){
        // Caso Base: el nodo es hoja (grado 0) cumple la condición
        if (raiz_actual.getGrado() == 0)
            return true;
        // Si el nodo solo tiene un hijo, el árbol no es homogéneo
        else if (raiz_actual.getGrado() == 1){
            return false;
        }
        // Caso Recursivo:Si el grado es 2 continuamos iterando,
        // verificando que el subárbol izquierdo como el derecho sean homogéneos.
        return isArbolHomogeneoRecursivo(raiz_actual.getNodoIzq()) && isArbolHomogeneoRecursivo(raiz_actual.getNodoDrch());
        // Empleamos && para que si el izquierdo ya es false no evaluemos el derecho
    }


    // ¿ÁRBOL COMPLETO?
    /**
     * Comprueba si el árbol es completo.
     * Un árbol completo es aquel en el que todas sus hojas se encuentran en el mismo nivel,
     * y todos los nodos internos tienen exactamente dos hijos.
     * <p>
     * @return true si el árbol es completo, false en caso contrario
     */
    public boolean isArbolCompleto(){
        int altura = this.getAltura();
        return isArbolCompletoRecursivo(this.root, altura);
    }

    /**
     * Método recursivo auxiliar que verifica la condición de árbol completo
     * nivel por nivel.
     * <p>
     * @param raiz_actual nodo que se está evaluando en la iteración actual
     * @param altura      niveles restantes hasta llegar al último nivel
     * @return true si el subárbol con raíz en raíz_actual es completo,
     *         false si algún nodo no cumple la condición
     */
    private boolean isArbolCompletoRecursivo(Nodo_Arbol_Binario<T> raiz_actual, int  altura){
        //Caso Base: Comprobamos que la raíz actual no sea nula, si lo es, automáticamente es falso
        if (raiz_actual == null)
            return false;
        //Nunca llegará a null con altura > 0 porque si el grado != 2 ya lo cortamos antes.
        // Por tanto si entra falso automáticamente

        //Caso Base: La altura es 0 lo que implica que estamos en el último nivel
        if(altura == 0){
            return (raiz_actual.getGrado() == 0);
        }

        //En caso de que la altura no sea 0, vemos si el grado del nodo es 2, si no lo es automáticamente no es homogéneo
        if(raiz_actual.getGrado() != 2){
            return false;
        }
        // Caso Recursivo: comprobamos ambos subárboles bajando un nivel
        return isArbolCompletoRecursivo(raiz_actual.getNodoIzq(), altura - 1) && isArbolCompletoRecursivo(raiz_actual.getNodoDrch(), altura - 1);
    }

    // ¿ÁRBOL CASI COMPLETO?

    //----METODO PARA EL USUARIO-----
    /**
     * Comprueba si el árbol es casi completo.
     * Un árbol de N nodos es casi completo si al recorrerlo,
     * ningún nodo tiene un índice >= n (es decir, sabiendo que la raíz tiene índice 0,
     * entonces para cualquier nodo con índice i se cumple que:
     *  - Su hijo izquierdo tiene el índice 2i + 1
     *  - Su hijo derecho tiene el índice 2i + 2
     * Por eso mismo como el árbol tiene N nodos, ningún índice puede ser mayor o igual a N.)
     * @return true si el árbol es casi completo, false en caso contrario
     */
    public boolean isArbolCasiCompleto() {
        if (root == null) return true;
        // Necesito crear un metodo que calcule el número total de nodos del árbol
        int numeroNodos = this.getNumNodos();
        return isArbolCasiCompletoRecursivo(this.root, 0, numeroNodos);
    }

    //-----METODO VERDADERO-----
    /**
     * Método recursivo auxiliar que verifica si el subárbol cuya raíz es {@code raiz_actual}
     * cumple la condición de árbol casi completo mediante indexación teórica.
     * @param raiz_actual El nodo actual
     * @param indice El índice teórico que le corresponde al nodo actual
     * @param numNodos El número total de nodos en el árbol
     */
    private boolean isArbolCasiCompletoRecursivo(Nodo_Arbol_Binario<T> raiz_actual, int indice, int numNodos){
        // Caso Base: Si el nodo es nulo, es correcto (no rompe la estructura)
        if (raiz_actual == null) {
            return true;
        }

        // Si el índice calculado para este nodo es mayor o igual al número total de nodos,
        // significa que hay un "hueco" antes de este nodo.
        if (indice >= numNodos) {
            return false;
        }

        // Caso Recursivo:
        // Izquierda: 2 * indice + 1
        // Derecha: 2 * indice + 2
        return isArbolCasiCompletoRecursivo(raiz_actual.getNodoIzq(), 2 * indice + 1, numNodos) &&
                isArbolCasiCompletoRecursivo(raiz_actual.getNodoDrch(), 2 * indice + 2, numNodos);
    }


    //---CALCULO DE NUMERO DE NODOS---

    //---METODO PARA EL USUARIO---
    /**
     * Devuelve el número total de nodos que contiene el árbol.
     *
     * @return El número de nodos del árbol como entero. Si el árbol está vacío, devuelve 0.
     */
    public int getNumNodos() {
        return getNumNodosRecursivo(root);
    }

    //-----METODO VERDADERO----
    /**
     * Método recursivo auxiliar que cuenta el número total de nodos
     * del subárbol cuya raíz es {@code actual}.
     *
     * @param actual El nodo raíz del subárbol que se está contando.
     * @return El número de nodos contenidos en el subárbol.
     */
    private int getNumNodosRecursivo(Nodo_Arbol_Binario<T> actual) {
        if (actual == null) return 0;
        return 1 + getNumNodosRecursivo(actual.getNodoIzq()) + getNumNodosRecursivo(actual.getNodoDrch());
    }

}

