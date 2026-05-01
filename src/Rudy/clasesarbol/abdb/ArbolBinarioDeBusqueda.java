package clasesarbol.abdb;
import extra.*;

// TAD ÁRBOL BINARIO DE BÚSQUEDA
public class  ArbolBinarioDeBusqueda<T extends Comparable<T>> implements InterfazABDB<T> {
    // Elemento imprescindible de la estructura/arquitectura de este TAD...
    private NodoArbol<T> raiz;

    // Constructor POR DEFECTO del árbol binario
    public ArbolBinarioDeBusqueda() {
        this.raiz = null;
    }

    // Constructor del árbol binario
    public ArbolBinarioDeBusqueda(NodoArbol<T> raiz) {
        this.raiz = raiz;
    }

    // Getter de la raíz del arbol...
    public NodoArbol<T> getRaiz() {
        return raiz;
    }

    // Metodo con implementación recursiva para poder introducir un elemento al árbol
    public void add(Elemento<T> elemento) {
        // Lo que hacemos es que la nueva raíz es el resultado de haberle insertado el elemento a la que teníamos antiguamente
        raiz = insertar(raiz, elemento);
    }

    // Con este metodo, es cómo se va a implementar la recursividad...
    private NodoArbol<T> insertar(NodoArbol<T> actual, Elemento<T> elemento) {
        // Pasamos un nodo actual (que siempre será la raiz de la iteración/versión anterior del árbol), y según su estado, hay dos posibles casos:
        if (actual == null) {
            // Si la raíz está vacía, (lo que hemos establecido como raíz), se añadirá un nodo en lugar con el elemento que queríamos añadir
            return new NodoArbol<>(elemento);
        }

        // Si la raíz no está vacía, entonces tenemos que obtener...
        T valorNuevo = elemento.getValor();                     // el valor del elemento que queremos introducir
        T valorActual = actual.getInicial().getValor();         // el valor del elemento que está en la raíz del árbol/subárbol

        // Si sale que el elemento que queremos introducir es menor que el de la raíz (pues se irá a la izquierda)
        if (valorNuevo.compareTo(valorActual) < 0) {
            // Se llama de forma recursiva bajando la raíz un nivel a la izquierda, con el mismo elemento
            actual.setIzquierda(insertar(actual.getIzquierda(), elemento));
        }
        // Si no, será para el otro lado del árbol, es decir, para la derecha
        else {
            // Se llama de forma recursiva bajando la raíz un nivel a la derecha, con el mismo elemento
            actual.setDerecha(insertar(actual.getDerecha(), elemento));
        }
        // Y se devuelve el nodo ya modificado (con la modificación recursiva de subárboles ya hecha) que es el que se establecerá en add()
        return actual;
    }

    // Para obtener los subárboles solo es 'bajar' al lado que queremos y ver si se cumplen las condiciones necesarias:
    // SUBÁRBOL DE LA IZQUIERDA
    public ArbolBinarioDeBusqueda<T> getSubArbolIzquierda() {
        // Si la raiz está vacía o lo que hay a la izquierda está vacía, entonces no hay nada que se pueda devolver
        if (raiz == null || raiz.getIzquierda() == null) {
            // Un arbol con nada simboliza ese conjunto vacío {{Ø}}, básicamente
            return new ArbolBinarioDeBusqueda<>(null);
        }
        // Si no ha pasado nada de lo anterior, significa que podemos devolver el subárbol correspondiente (izquierda)
        return new ArbolBinarioDeBusqueda<>(raiz.getIzquierda());
    }
    // SUBÁRBOL DE LA DERECHA
    public ArbolBinarioDeBusqueda<T> getSubArbolDerecha() {
        // Si la raiz está vacía o lo que hay a la derecha está vacía, entonces no hay nada que se pueda devolver
        if (raiz == null || raiz.getDerecha() == null) {
            // Un arbol con nada simboliza ese conjunto vacío {{Ø}}, básicamente
            return new ArbolBinarioDeBusqueda<>(null);
        }
        // Si no ha pasado nada de lo anterior, significa que podemos devolver el subárbol correspondiente (derecha)
        return new ArbolBinarioDeBusqueda<>(raiz.getDerecha());
    }

    // Metodo para ver si el árbol está vacío
    public boolean isArbolEmpty() {
        // Simplemente, es usar una de las condiciones anteriores, o preguntar: "¿hay algo en la raíz?" -> NO -> ARBOL VACÍO
        return raiz == null;
    }


    // ¡METODOS IMPLEMENTADOS POR LA INTERFAZABDB! (Junto a algunos extras necesarios para que estos funcionen)
    // I. GRADO DEL ÁRBOL
    @Override
    public int getGrado() {
        // Si la raíz está vacía, entonces tiene 0 posibles hijos -> grado 0
        if (raiz == null) {
            return 0;
        }
        // Inicializamos un contador
        int grado = 0;
        // Contamos tanto individualmente por la izquierda como por la derecha (una ÚNICA VEZ)
        if (raiz.getIzquierda() != null) {
            // Equivalente a decir: Si hay algo a la izquierda, grado 1 o grado 2 (si hay en la derecha)
            grado = grado + 1;
        }
        if (raiz.getDerecha() != null) {
            // Equivalente a decir: Si también hay algo a la derecha, grado 1, o grado 2 (si hay en la izquierda)
            grado = grado + 1;
        }
        // ¡Se acaba devolviendo la suma del grado del árbol que puede ser 0, 1 o 2!
        return grado;
    }

    // II. ALTURA DEL ÁRBOL MÁXIMA
    @Override
    public int getAltura() {
        // Como hay que implementar recursividad, preguntando constantemente, se usa un metodo secundario
        return altura(raiz);
    }

    // Este es el metodo secundario que permitirá hacer posible el cálculo de la altura
    private int altura(NodoArbol<T> nodo) {
        // Pasamos la raíz del árbol como parámetro
        if (nodo == null) {
            // Si la raiz está vacía, el árbol está vacio, y por ende no hay aristas en cierto modo, entonces la altura es negativa por conveniencia
            return -1;
        }

        // Si no está el árbol vacio, se calcula 1, junto al máximo de la llamada recursiva del subárbol de la izquierda y el subárbol de la derecha
        return 1 + Math.max(altura(nodo.getIzquierda()), altura(nodo.getDerecha()));
        // Básicamente: Si no hay nada a la izquierda ni a la derecha, y solo tenemos la raíz, pues el grado debe de ser 0: 1 + máx{-1,-1}
    }

    // III. ¡Lista de datos del mismo nivel del árbol!
    @Override
    public Lista<T> getListaDatosNivel(int nivel) {
        // Se crea una lista vacía que guarde todos los datos del mismo nivel
        Lista<T> resultado = new Lista<>();
        // Si el nivel que se ha pasado como parámetro es negativo, o el árbol (raíz) está vacío, entonces se devuelve una lista vacía
        if (raiz == null || nivel < 0) {
            return resultado;
        }
        // Usamos como en los metodos anteriores una función auxiliar recursiva que permita preguntar a cada nodo los elementos de dicho nivel
        obtenerNivel(raiz, nivel, 0, resultado);
        // Devolvemos la lista ya con los datos insertados
        return resultado;
    }

    // Metodo auxiliar que permitirá que getListaDatosNivel() funcione correctamente
    public void obtenerNivel(NodoArbol<T> nodo, int nivelBuscado, int nivelActual, Lista<T> lista) {
        // Si la raíz está vacía, no se hace nada (obedeciendo las condiciones anteriores)
        if (nodo == null) {
            return;
        }
        // Ahora, lo que hacemos es establecer la condición de que si nos encontramos en el nivel correspondiente...
        if (nivelActual == nivelBuscado) {
            // Extraer la información de ese nodo e insertarla en la lista
            lista.insertar(nodo.getInicial().getValor());
            // Salimos de la condición para seguir con nuevas iteraciones
            return;
        }

        // La recursividad mediante estos dos métodos permite bifurcarnos hacia cualquier lado en cualquier nodo, y al subir de nivel, o bajar en el árbol, podemos reiterar hasta encontrarnos en la rama adecuada en el nivel pedido...
        obtenerNivel(nodo.getIzquierda(), nivelBuscado, nivelActual + 1, lista);        // tanto para la izquierda
        obtenerNivel(nodo.getDerecha(), nivelBuscado, nivelActual + 1, lista);          // como para la derecha
    }

    // IV. METODO PARA VER SI UN ÁRBOL ES HOMOGÉNEO (preguntar recursivamente nodo a nodo, "¿tienes n hijos?")
    @Override
    public boolean isArbolHomogeneo() {
        // Si la raíz es nula entonces el árbol está vacío y técnicamente tiene 0 hijos para todas sus bifurcaciones o nodos
        if (raiz == null) {
            // Así que se devuelve el booleano "verdad"
            return true;
        }
        // Ahora si la raíz no es nula hay que hacer lo que se había planteado inicialmente
        int gradoReferencia = gradoNodo(raiz);      // caso inicial: si la raíz tiene 3 hijos
        // Entonces habrá que comprobar si todos sus subárboles tienen 3 hijos también...
        return comprobarHomogeneo(raiz, gradoReferencia);
    }

    // Este metodo permite saber el número de hijos (máximo posible: 2) del nodo en el que se encuentra la iteración
    public int gradoNodo(NodoArbol<T> nodo) {
        // El grado es 0...
        int grado = 0;
        // Si hay izquierda entonces se suma 1
        if (nodo.getIzquierda() != null) grado++;
        // Si hay derecha también se suma 1
        if (nodo.getDerecha() != null) grado++;
        // Se devuelve un posible valor entre 0,1 y 2
        return grado;
    }

    // Y este es el metodo recursivo auxiliar para poder comprobarlo
    public boolean comprobarHomogeneo(NodoArbol<T> nodo, int gradoRef) {
        // Si el nodo en el que nos encontramos está vacío entonces obedece la condición del metodo superior (ser verdad)
        if (nodo == null) {
            return true;
        }
        // Si no está vacío y su grado no es el mismo que el de referencia entonces esto es mentira
        if (gradoNodo(nodo) != gradoRef) {
            return false;
        }

        // Y ahora comprobaremos si se cumple tanto para su izquierda como para la derecha... si es así (siempre), podemos concluir que el árbol es homogéneo
        return comprobarHomogeneo(nodo.getIzquierda(), gradoRef) &&
                comprobarHomogeneo(nodo.getDerecha(), gradoRef);
    }

    // V. METODO PARA VER SI UN ÁRBOL ESTÁ COMPLETO
    @Override
    public boolean isArbolCompleto() {
        // Si la raiz es nula esto es cierto porque todos los hijos están al mismo nivel (-1, no existen)
        if (raiz == null) {
            return true;
        }

        // Calculamos la altura máxima del árbol y ahora lo que hacemos es...
        int altura = getAltura();
        // Llamar recursivamente al metodo auxiliar
        return hojasEnNivel(raiz, 0, altura);
    }

    // Metodo empleado para poder implementar la recursividad de ir preguntando nodo a nodo
    public boolean hojasEnNivel(NodoArbol<T> nodo, int nivel, int altura) {
        // Si el árbol está vacío, de nuevo, cumple la condición superior siendo esto verdad
        if (nodo == null) {
            return true;
        }

        // Si no lo está, hay que comprobar entonces si nos encontramos en un nodo hoja (no hay bifurcaciones)
        if (nodo.getIzquierda() == null && nodo.getDerecha() == null) {
            // Y ver si el nivel es el mismo que el de la altura
            return nivel == altura;
        }

        // Si existe alguna bifurcación entonces habrá que volver a llamar al metodo recursivamente hasta encontrarnos en un nodo hoja
        return hojasEnNivel(nodo.getIzquierda(), nivel + 1, altura) && hojasEnNivel(nodo.getDerecha(), nivel + 1, altura);
        // (significa comprobar que se cumple tanto para la izquierda como para la derecha)
    }

    // VI. ¿ESTE ÁRBOL ESTÁ CASI COMPLETO?
    @Override
    public boolean isArbolCasiCompleto() {
        // Solo hay que ver la altura máxima
        int altura = getAltura();
        // Y emplear recursividad
        return comprobarCasiCompleto(raiz, 0, altura);
    }

    // METODO AUXILIAR PARA EMPLEAR RECURSIVIDAD:
    public boolean comprobarCasiCompleto(NodoArbol<T> nodo, int nivel, int altura) {
        if (nodo == null) {
            // Si la raíz (el árbol) está vacía, esto es cierto
            return true;
        }

        // Si estamos en el último nivel o penúltimo, también es cierto
        if (nivel >= altura - 1) {
            return true;
        }

        // Si tiene derecha, pero no izquierda → esto es inválido
        if (nodo.getIzquierda() == null && nodo.getDerecha() != null) {
            // Es decir, ¡que es falso!
            return false;
        }

        // Y vamos comprobando nodo a nodo recursivamente para poder acercanos a las condiciones anteriores
        return comprobarCasiCompleto(nodo.getIzquierda(), nivel + 1, altura) && comprobarCasiCompleto(nodo.getDerecha(), nivel + 1, altura);
    }

    // VII. METODO QUE PERMITE OBTENER EL CAMINO MÁS LARGO DESDE LA RAIZ HASTA EL NODO MÁS ALEJADO DEL ÁRBOL
    @Override
    public Lista<NodoArbol<T>> getCamino() {
        // Construir un camino entre dos nodos consiste en obtener una lista con los nodos correspondientes
        Lista<NodoArbol<T>> lista = new Lista<>();
        // Llamamos al metodo recursivo que permite construir el camino
        construirCamino(raiz, lista);
        // Y finalmente retornamos la lista que contiene ya al camino creado...
        return lista;
    }

    // Metodo auxiliar...
    public void construirCamino(NodoArbol<T> nodo, Lista<NodoArbol<T>> lista) {
        // Si el árbol está vacío no hay caminos posibles que se puedan devolver (nos salimos del metodo)
        if (nodo == null) {
            return;
        }

        // Si no, entonces insertamos el nodo a la lista
        lista.insertar(nodo);

        // Y ahora obtenemos las alturas de las dos bifurcaciones (esto permite decidir si existen)
        int alturaIzq = altura(nodo.getIzquierda());
        int alturaDer = altura(nodo.getDerecha());

        // Si la izquierda es mayor, significa que hay un camino más largo existente por ahí, y que la derecha no tiene más bifurcaciones
        if (alturaIzq > alturaDer) {
            construirCamino(nodo.getIzquierda(), lista);
        }
        // Si no, entonces habrá que llamar recursivamente por el lado de la derecha...
        else {
            construirCamino(nodo.getDerecha(), lista);
        }
    }

    // VIII. MÉTODOS QUE DEVUELVEN LOS TRES TIPOS DE LISTAS DIFERENTES
    // A. PREORDEN
    @Override
    public Lista<T> getListaPreorden() {
        // Se crea una lista vacía para introducir los resultados
        Lista<T> lista = new Lista<>();
        // Y se llama al metodo recursivo para poder llevar esto a cabo
        preorden(raiz, lista);
        // Una vez acaba el proceso, se devuelve la lista ya finalmente modificada
        return lista;
    }

    // Metodo auxiliar para el preorden...
    public void preorden(NodoArbol<T> n, Lista<T> l) {
        // Si el árbol está vacío, pues obviamente no se devuelve nada, solo una lista vacía
        if (n == null) {
            return;
        }
        // Para realizar el preorden primero se introduce el elemento
        l.insertar(n.getInicial().getValor());
        // Y luego se hace para la izquierda
        preorden(n.getIzquierda(), l);
        // Y para la derecha, de forma recursiva
        preorden(n.getDerecha(), l);
    }

    // B. POSTORDEN
    @Override
    public Lista<T> getListaPostorden() {
        // Se crea una lista vacía para introducir los resultados
        Lista<T> lista = new Lista<>();
        // Y se llama al metodo recursivo para poder llevar esto a cabo
        postorden(raiz, lista);
        // Una vez acaba el proceso, se devuelve la lista ya finalmente modificada
        return lista;
    }

    // Metodo auxiliar para el postorden...
    public void postorden(NodoArbol<T> n, Lista<T> l) {
        // Si el árbol está vacío, pues obviamente no se devuelve nada, solo una lista vacía
        if (n == null) {
            return;
        }
        // En el postorden primero se lleva a cabo la recursividad (empezando por la izquierda)
        postorden(n.getIzquierda(), l);
        // Luego por la derecha...
        postorden(n.getDerecha(), l);
        // Y finalmente se introducen los elementos correspondientes
        l.insertar(n.getInicial().getValor());
    }

    // C. ORDEN CENTRAL
    @Override
    public Lista<T> getListaOrdenCentral() {
        // Se crea una lista vacía para introducir los resultados
        Lista<T> lista = new Lista<>();
        // Y se llama al metodo recursivo para poder llevar esto a cabo
        inorden(raiz, lista);
        // Una vez acaba el proceso, se devuelve la lista ya finalmente modificada
        return lista;
    }

    // Metodo auxiliar para el orden central...
    public void inorden(NodoArbol<T> n, Lista<T> l) {
        // Si el árbol está vacío, pues obviamente no se devuelve nada, solo una lista vacía
        if (n == null) {
            return;
        }
        // Y para poder emplear primero el orden  central, se llama por la izquierda
        inorden(n.getIzquierda(), l);
        // Luego se inserta el valor que corresponde
        l.insertar(n.getInicial().getValor());
        // Y luego finalmente se inserta recursivamente por la derecha
        inorden(n.getDerecha(), l);
        // Esto debería de dar como resultado una lista de un árbol equilibrado..., o la lista original
    }

    // EXTRA: METODO QUE PERMITE CALCULAR CAMINOS DESDE LA RAÍZ HACIA A OTRO NODO
    public Lista<NodoArbol<T>> getCaminoA(T valor) {
        // Se crea una lista vacía de nodos que permita guardar el camino
        Lista<NodoArbol<T>> lista = new Lista<>();
        // Y se hace una copia del árbol (es decir, de la raíz)
        NodoArbol<T> actual = raiz;
        // Mientras la el árbol no esté vacío
        while (actual != null) {
            // Se inserta el nodo correspondiente (siempre se empieza por la raíz)
            lista.insertar(actual);
            // Ahora lo que hacemos es ver dónde se encuentra nuestro valor en el árbol mediante una comparación
            int comp = valor.compareTo(actual.getInicial().getValor());
            // Si es igual, pues paramos y devolvemos la lista que representa el camino
            if (comp == 0) {
                // ¡Se ha encontrado! Podemos parar de iterar
                return lista;
            }
            // Si se obtiene un número negativo de la comparación
            else if (comp < 0) {
                // Significa que hay que navegar recursivamente hacia la izquierd
                actual = actual.getIzquierda();
            }
            else {
                // Y si no, hacia la derecha...
                actual = actual.getDerecha();
            }
        }
        // Si al final no suecede (comp == 0), eso es que NO existe el valor en el árbol
        return new Lista<>();       // se devuelve una lista vacía que simboliza que no hay camino
    }

    public int longitudCamino(Lista<NodoArbol<Integer>> camino) {
        // Se usa un contador inicializado en 0...
        int contador = 0;
        // Y lo único que hay que hacer es recorrer nodo a nodo la lista de caminos
        NodoLista<NodoArbol<Integer>> actual = camino.getCabeza();
        // Hasta que no queden nodos que recorrer, es decir, que esté vacía
        while (actual != null) {
            contador = contador + 1;
            actual = actual.getSiguiente();
        }
        // Y finalmente empleamos la fórmula:  'longitud = nodos - 1', siendo nodos el contador
        return contador - 1;
    }
}