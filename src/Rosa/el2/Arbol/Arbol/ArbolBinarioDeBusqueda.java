package es.uah.matcomp.ed.el2.Arbol.Arbol;
import Listas.Lista_simple.Lista_Simple;

import static java.lang.Math.max;

public class ArbolBinarioDeBusqueda<T extends Comparable<T>> {
    private Nodo_Arbol_Binario<T> root;

    public ArbolBinarioDeBusqueda() {
        this.root = null;
    }
    public ArbolBinarioDeBusqueda(T valor) {
        this.root = new Nodo_Arbol_Binario<>(valor);
    }

    // Este constructor lo hago protected para evitar que el usuario pueda alterar la estructura del árbol y que sus hijos puedan
    ArbolBinarioDeBusqueda(Nodo_Arbol_Binario<T> nodo) {
        this.root = nodo;
    }


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




    // Me he dado cuenta de que al usar y crear árboles en la estructura (usando getSubArbolIzquierda() y la otra) para la recursividad es increíblemente ineficiente,
    // debido a que se pueden llegar a crear cientos de objetos temporales para moverte por el árbol.
    // Por tanto, simplemente pasarle la dirección del nodo es suficiente, ya que al final lo que añadimos es el dato del nodo,
    // para ello no necesito crear ningún árbol nuevo.

    //------- AÑADIR ------------
    public void anadir(T element)
    {
        // Hacemos una función pública donde el usuario solo tiene que mandar el elemento

        root = anadirRecursivo(root, element);
        // Y la mandamos a uno recursivo donde le pasamos el nodo y el elemento

        //El Balanceo lo hacemos en el árbol AVL

    }

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

    // DEVOLVER GRADO: Tiene más
    //---- DEVOLVER ALTURA-----
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
    // Método principal que llama el usuario
    public Lista_Simple<T> getListaDatosNivel(int nivelObjetivo) {
        Lista_Simple<T> listaNivel = new Lista_Simple<>();
        getListaDatosNivelRecursivo(root,nivelObjetivo, listaNivel );
        //No necesito que la función recursiva devuelva nada porque como en Java se trabaja con referencias,
        // lo que añado a la lista durante la recursividad, se queda en la lista original

        return listaNivel;
    }
    //Método oculto recursivo
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
        getListaDatosNivelRecursivo(raiz_actual,nivel_restante -1, lista);
        getListaDatosNivelRecursivo(raiz_actual,nivel_restante -1, lista);

    }

    //--GET CAMINO---

    //PRIMERO CREAMOS UN METODO QUE COMPRUEBE QUE EL DATO ESTÁ EN EL ÁRBOL
    public boolean contiene(T dato){
        return contiene_recursivo(dato, this.root);
    }
    private boolean contiene_recursivo(T dato, Nodo_Arbol_Binario<T> raiz_actual) {
        //Caso Base: El dato no está contenido en la lista, lo que implicaría que se ha llegado a un nodo nulo
        if(raiz_actual == null){
            return false;
        }
        //Caso Base: El dato es igual al dato del nodo, luego está contenido en el árbol
        if(dato == raiz_actual.getDato()){
            return true;
        }
        //Casos Recursivos
        //Opción A: El dato es menor que el dato de raíz_actual
        if(raiz_actual.getDato().compareTo(dato) <0){
            return contiene_recursivo(dato, raiz_actual.getNodoIzq());
        }
        //Opción B: El dato es mayor que el dato de raíz_actual
        return contiene_recursivo(dato, raiz_actual.getNodoDrch());

    }

    //METODO PARA EL USUARIO
    public Lista_Simple<T> getCamino(T dato) {
        Lista_Simple<T> Camino = new Lista_Simple<>();
        if (this.contiene(dato)) {
            getCaminoRecursivo(dato, root, Camino);
        }
        //De esta manera si dato no está en el árbol devolvemos una lista vacía
        return Camino;
    }

    //METODO VERDADERO
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


    //DISTINTAS IMPRESIONES DEL ÁRBOL_______________

    //---IN ORDER----
    // Se visita primero el nodo izquierdo, luego la raíz y por último el nodo derecho
    public Lista_Simple<T> getListaInOrden(){
        Lista_Simple<T> lista = new Lista_Simple<>();
        getListaInOrdenRecursivo(root, lista);
        return lista;
    }
    private void getListaInOrdenRecursivo(Nodo_Arbol_Binario<T> raiz_actual, Lista_Simple<T> lista) {
        if(raiz_actual != null) {
            getListaInOrdenRecursivo(raiz_actual.getNodoIzq(), lista);
            lista.anadir(raiz_actual.getDato());
            getListaInOrdenRecursivo(raiz_actual.getNodoDrch(), lista);
        }
        //En caso de que sea nulo no hacemos nada
    }


    //---POST ORDER----
    // Se visita primero el nodo izquierdo, luego el nodo derecho y por último la raíz
    public Lista_Simple<T> getListaPostOrden(){
        Lista_Simple<T> lista = new Lista_Simple<>();
        getListaPostOrdenRecursivo(root, lista);
        return lista;
    }
    private void getListaPostOrdenRecursivo(Nodo_Arbol_Binario<T> raiz_actual, Lista_Simple<T> lista) {
        if(raiz_actual != null){
            getListaInOrdenRecursivo(raiz_actual.getNodoIzq(), lista);
            getListaInOrdenRecursivo(raiz_actual.getNodoDrch(), lista);
            lista.anadir(raiz_actual.getDato());
        }
        //En caso de que sea nulo no hacemos nada
    }


    //----PRE ORDER-----
    // Se visita primero la raíz, luego el nodo izquierdo y por último el nodo derecho
    public Lista_Simple<T> getListaPreOrden(){
        Lista_Simple<T> lista = new Lista_Simple<>();
        getListaPreOrdenRecursivo(root, lista);
        return lista;

    }
    private void getListaPreOrdenRecursivo(Nodo_Arbol_Binario<T> raiz_actual, Lista_Simple<T> lista) {
        //Caso Recursivo: Si el nodo no es nulo lo añadimos a la lista y de forma recursiva le pasamos su hijo izquierdo y su hijo derecho
        if (raiz_actual != null){
            lista.anadir(raiz_actual.getDato());
            getListaInOrdenRecursivo(raiz_actual.getNodoIzq(), lista);
            getListaInOrdenRecursivo(raiz_actual.getNodoDrch(), lista);

        }
        //En caso de que sea nulo no hacemos nada

    }

    //TIPO DE ÁRBOL

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
        // Caso Base: el nodo es hoja (grado 0) o tiene dos hijos (grado 2), cumple la condición
        if(raiz_actual.getGrado() == 2 ||  raiz_actual.getGrado() == 0)
            return true;
        // Si el nodo solo tiene un hijo, el árbol no es homogéneo
        else if (raiz_actual.getGrado() == 1){
            return false;
        }
        // Caso Recursivo: verificamos que tanto el subárbol izquierdo como el derecho sean homogéneos.
        return isArbolHomogeneoRecursivo(raiz_actual.getNodoIzq()) && isArbolHomogeneoRecursivo(raiz_actual.getNodoDrch());
        // Empleamos && para que si el izquierdo ya es false no evaluemos el derecho
    }


    // ¿ÁRBOL COMPLETO?
    /**
     * Comprueba si el árbol es completo.
     * Un árbol completo Es aquel árbol que tiene todos sus nodos, menos los nodos del último nivel, completos
     * @return true si el árbol es completo, false en caso contrario
     */
    public boolean isArbolCompleto(){
        int altura = this.getAltura();
        return isArbolCompletoRecursivo(this.root, altura);
    }
    /**
     * Método recursivo auxiliar que verifica la condición de árbol completo
     * nivel por nivel.
     *
     * @param raiz_actual nodo que se está evaluando en la iteración actual
     * @param altura      niveles restantes hasta llegar al último nivel
     * @return true si el subárbol con raíz en raiz_actual es completo,
     *         false si algún nodo no cumple la condición
     */
    private boolean isArbolCompletoRecursivo(Nodo_Arbol_Binario<T> raiz_actual, int  altura){
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
    public boolean isArbolCasiCompleto(){
        int altura = this.getAltura();
        return true;
        //WORK IN PROGRESS
        //return isArbolCasiCompletoRecursivo(this.root, altura);
    }
    //private boolean isArbolCasiCompletoRecursivo(Nodo_Arbol_Binario<T> raiz_actual, int altura){}





}

