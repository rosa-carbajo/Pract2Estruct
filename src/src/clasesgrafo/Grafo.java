package clasesgrafo;

import clasesgrafo.auxiliar.Par;
import clasesgrafo.auxiliar.ParPadre;
import extra.Lista;
import extra.NodoLista;

public class Grafo {
    // Un conjunto de un grafo, está definido tal que: G = {V,E}
    private Conjunto<Arista<DatoArista, DatoVertice>> aristas;          // donde E es el conjunto de las aristas
    private Conjunto<Vertice<DatoVertice>> vertices;                    // y V es el conjunto de todos los vértices del grafo
    Conjunto<Par> tabla = new Conjunto<>();
    long contador = 1;

    // Para poder crear un grafo, solo bastaría con crear dos conjuntos vacíos: G = {{Ø}, {Ø}}
    public Grafo() {
        vertices = new Conjunto<>();
        aristas = new Conjunto<>();
    }

    // ¿Cómo podríamos buscar un vértice dentro del conjunto de los vértices? Solo hay que hacerlo según su identificador...
    public Vertice<DatoVertice> buscarVertice(long id) {
        // Se copia la cabeza del conjunto es decir, la lista de los vértices
        NodoLista<Vertice<DatoVertice>> actual = vertices.getElementos().getCabeza();

        // Se itera sobre la cabeza elemento a elemento hasta que no quede ninguno, es decir, que esté vacía
        while (actual != null) {
            // Si en algún momento el identificador del vértice coincide con el id que hemos introducido, entonces se devolverá ese vértice (o su dato asociado, aún pendiente de decidir)
            if (actual.getDato().getId() == id) {
                // Devolver el dato del nodo equivale a devolver el elemento en esa posición de la lista, que en según el contexto de uso, se trataría de un vértice
                return actual.getDato();
            }
            // Si no sucede nada de lo anterior, se pasa al siguiente elemento
            actual = actual.getSiguiente();
        }
        // Y si el identificador que hemos aportado no se encuentra en la lista de vértices, pues, no se devuelve nada como tal...
        return null;
    }

    // Y, ¿cómo se haría para poder agregar un nuevo vértice?
    public void agregarVertice(long id, DatoVertice dato) {
        // Se crea un nuevo vértice que contenga al identificador, y al dato que nosotros hemos pasado como parámetros
        Vertice<DatoVertice> v = new Vertice<>(id, dato);

        // Si no está contenido en el conjunto (y por ende, en la lista), entonces se insertará
        if (!vertices.contiene(v)) {
            vertices.insertar(v);
        }
    }

    // Para las aristas, sigue la misma estructura
    public void agregarArista(Long id, DatoArista dato, long idorigen, long iddestino) {
        // Primeramente, se buscan los vértices correspondientes según los identificadores que nosotros hemos pasado como argumentos
        Vertice<DatoVertice> origen = buscarVertice(idorigen);
        Vertice<DatoVertice> destino = buscarVertice(iddestino);

        // Hay un caso muy especial que es, que si la arista no posee origen o destino, entonces no es posible crearla (o añadirla formalmente al grafo)
        if (origen == null || destino == null) {
            // Porque por definición, una arista debe conectar a dos puntos, no puede ser algo tipo: "A -> null"
            return;
        }

        // Si no ha sucedido lo anterior, podemos proceder a crear a la arista
        Arista<DatoArista, DatoVertice> a = new Arista<>(id, dato, origen, destino);

        // Y hacemos lo mismo que en buscarVertice() ya que ambos tienen la estructura de un conjunto
        if (!aristas.contiene(a)) {
            // Es decir, si a ∉ Aristas -> ¡insertarla en el conjunto de las aristas!
            aristas.insertar(a);
        }
    }

    // Para eliminar una arista tenemos que hacer lo siguiente:
    public void eliminarArista(long id) {
        // Cojemos una copia del conjunto de las aristas
        NodoLista<Arista<DatoArista, DatoVertice>> actual = aristas.getElementos().getCabeza();

        // Se itera mientras el conjunto no esté vacío
        while (actual != null) {
            // Y si el identificador de la arista en la que nos encontramos coincide con el identificador que hemos pasado
            if (actual.getDato().getId() == id) {
                // Llamamos a la función 'eliminar' para quitar la arista
                aristas.eliminar(actual.getDato());
                return;
            }
            // Actualizamos la posición de la arista para dar esa sensación de iteración
            actual = actual.getSiguiente();
        }
    }

    // Para eliminar un vértice solo se debe hacer lo siguiente
    public void eliminarVertice(long id) {
        // Se llama al metodo para buscar vértices según el identificador que hemos pasado como argumento
        Vertice<DatoVertice> v = buscarVertice(id);
        // Si no se encuentra ningún vértice con ese ID (return null), pues no se hace nada
        if (v == null) {
            return;
        }

        // Si no, primero se debe de eliminar las aristas que estén conectadas
        NodoLista<Arista<DatoArista, DatoVertice>> actual = aristas.getElementos().getCabeza();            // se copia el conjunto de las aristas

        // Mientras la lista no esté vacía
        while (actual != null) {
            // Se copia la arista de la posición actual del nodo de la lista que representa al conjunto
            Arista<DatoArista, DatoVertice> a = actual.getDato();
            //...
            NodoLista<Arista<DatoArista, DatoVertice>> siguiente = actual.getSiguiente();
            // Ahora, si el origen de o (||) el destino de dicha arista es el vértice que queremos borrar
            if (a.getOrigen().equals(v) || a.getFin().equals(v)) {
                // Pues lamentablemente se debe de sacrificar la arista
                aristas.eliminar(a);
            }

            // Si no, actualizamos la posición de la arista para crear la iteración
            actual = siguiente;
        }
        // Una vez hemos quitado todas las aristas asociadas, eliminamos al vértice aislado de la lista
        vertices.eliminar(v);
    }

    // Metodo para poder imprimir la estructura interna del grafo que ha sido creado
    public void imprimir() {
        // Se crea una copia del conjunto de los vértices
        NodoLista<Vertice<DatoVertice>> v = vertices.getElementos().getCabeza();

        // Mientras no esté vacío...
        while (v != null) {
            // Obtenemos el dato dos veces para poder obtener al "DatoVertice" de dicha posición
            System.out.print(v.getDato().getDato() + " -> ");

            // Se crea una copia del conjunto de las aristas
            NodoLista<Arista<DatoArista, DatoVertice>> a = aristas.getElementos().getCabeza();
            // Y mientras no esté vacía....
            while (a != null) {
                // Si el origen de la arista es el vértice en el que nos encontramos
                if (a.getDato().getOrigen().equals(v.getDato())) {
                    // Se debe de imprimir el dato que "carga" la arista asociada a ese vértice
                    System.out.print(a.getDato().getFin().getDato() + " ");
                }
                // Se actualiza la arista y se pasa a la siguiente para ver si lo cumple
                a = a.getSiguiente();
            }
            // Se imprime un '\n' para separar entre líneas, como si fuese un salto de línea
            System.out.println("\n");
            // Y se actualiza la posición del vértice para pasar al siguiente
            v = v.getSiguiente();
        }
    }

    // Busca el ID de una relación cualquiera...
    public Long obtenerId(Conjunto<Par> tabla, String etiqueta) {
        // Se copia la tabla de relaciones que contiene los identificadores únicos asociados
        NodoLista<Par> actual = tabla.getElementos().getCabeza();
        // Y se itera sobre el conjunto para buscar si...
        while (actual != null) {
            if (actual.getDato().getEtiqueta().equals(etiqueta)) {
                // Hay una relación que coincida, entonces devuelvo su ID
                return actual.getDato().getId();
            }
            // Se actualiza para poder seguir iterando miembro a miembro
            actual = actual.getSiguiente();
        }
        // Si no se da la igualdad, la relación que buscamos, no existe, no hay ID
        return null;
    }

    // Metodo para obtener el camino mínimo entre dos vértices según sus identificadores
    public Lista<Vertice<DatoVertice>> caminoMinimo(long idInicio, long idFin) {
        // Se crea una cola donde se va a guardar los vértices recorridos (camino)
        Lista<Vertice<DatoVertice>> cola = new Lista<>();
        // Se crea un conjunto vacío donde se guardarán los vértices ya visitados
        Conjunto<Vertice<DatoVertice>> visitados = new Conjunto<>();
        // Y se crea un conjunto de pares que contendrá las informaciones de las relaciones
        Conjunto<ParPadre> padres = new Conjunto<>();
        // Se buscan ambos vértices según sus identificadores
        Vertice<DatoVertice> inicio = buscarVertice(idInicio);
        Vertice<DatoVertice> fin = buscarVertice(idFin);
        // Y una vez hecho, se insertan (sea lo que sean) en la cola y en el conjunto
        cola.insertar(inicio);
        visitados.insertar(inicio);
        // Si la cola está vacía, es decir, que no hay vértice de inicio, no se hace nada
        while (cola.getCabeza() != null) {
            // Ahora se copia el dato del vértice que hemos insertado antes en la cola
            Vertice<DatoVertice> actual = cola.getCabeza().getDato();
            // Y lo eliminamos de la cola
            cola.eliminar(actual);
            // Ahora si ese dato es el mismo que el del vértice final, ya está (introducir el mismo identificador dos veces)
            if (actual.equals(fin)) {
                break;
            }
            // Si no, copiaremos y buscaremos en la lista de aristas
            NodoLista<Arista<DatoArista, DatoVertice>> a = aristas.getElementos().getCabeza();
            // Hasta que el conjunto deje de estar vacío...
            while (a != null) {
                // Se copia la arista de la posición en la que estamos
                Arista<DatoArista, DatoVertice> arista = a.getDato();
                // Se comprueba si mantiene relación con el vértice que inicialmente habíamos metido en al cola
                if (arista.getOrigen().equals(actual)) {
                    // Y se obtiene el fin de esa arista
                    Vertice<DatoVertice> vecino = arista.getFin();
                    // Si ese vértice final no está metido en vértices visitados (los vecinos)
                    if (!visitados.contiene(vecino)) {
                        visitados.insertar(vecino);
                        cola.insertar(vecino);
                        // Se mete y se modifica para reiterar (nos hemos movido a ese vértice)
                        padres.insertar(new ParPadre(vecino, actual));      // y se crea una relación hijo-padre entre ese vecino y el vértice anterior
                    }
                }
                // Se actualiza para iterar
                a = a.getSiguiente();
            }
        }
        // Finalmente, ahora se crea una lista para insertar el camino
        Lista<Vertice<DatoVertice>> camino = new Lista<>();
        // Y se copia el vértice final que habíamos pasado como parámetro
        Vertice<DatoVertice> paso = fin;
        // Ahora mientras el vértice final no esté vacío
        while (paso != null) {
            // Se va insertando en el camino,
            camino.insertar(paso);
            // Y se actualiza pasando a la relación superior con SU padre (o sea ir algo estilo D <- C <- B <- A ...)
            paso = buscarPadre(padres, paso);
        }
        // Y finalmente se devuelve la lista creada que es el camino correspondiente.
        return camino;
    }

    // Metodo que permite decidir si un grafo es conexo (no disjunto)
    public boolean esConexo() {
        // Se copia el conjunto de los vértices
        NodoLista<Vertice<DatoVertice>> v = vertices.getElementos().getCabeza();
        // Si no hay vertices el grafo es conexo
        if (v == null) {
            return true;
        }
        // Ahora creamos un conjunto de vértices que guardará los vértices visitados (definición)
        Conjunto<Vertice<DatoVertice>> visitados = new Conjunto<>();
        // Y una lista que guardará la cola que representa el camino (si existe) que indica la conexividad
        Lista<Vertice<DatoVertice>> cola = new Lista<>();
        // Se copia el dato al inicio del vértice
        Vertice<DatoVertice> inicio = v.getDato();
        // Y se inserta en el camino ese dato
        cola.insertar(inicio);
        // Y en los vértices visitados también
        visitados.insertar(inicio);
        // Ahora si la cola no está vacía pues
        while (cola.getCabeza() != null) {
            // Se copia el dato que habíamos añadido antes
            Vertice<DatoVertice> actual = cola.getCabeza().getDato();
            // Se elimina de la cola...
            cola.eliminar(actual);
            // Y ahora copiamos el conjunto de aristas para poder recorrer vértices no visitados
            NodoLista<Arista<DatoArista, DatoVertice>> a = aristas.getElementos().getCabeza();
            // Mientras la lista no esté vacía (no sea un conjunto vacío)
            while (a != null) {
                // Se copia el dato que carga con sí misma la arista
                Arista<DatoArista, DatoVertice> arista = a.getDato();
                // Y se compara el vértice del origen con el que habíamos añadido antes
                if (arista.getOrigen().equals(actual)) {
                    // Es decir, que buscamos aristas que tengan como origen al vértice de antes, y buscamos su vértice final
                    Vertice<DatoVertice> vecino = arista.getFin();
                    // Si la cola de visitados no contiene a ese vértice final
                    if (!visitados.contiene(vecino)) {
                        // Se cuenta como visitado (Ya hemos contado a la arista)
                        visitados.insertar(vecino);
                        // Y se inserta en la cola para ahora partir desde ese vértice nuevo buscando aristas y así sucesivamente...
                        cola.insertar(vecino);
                    }
                }
                // Se actualiza para crear la sensación de iteración en el bucle
                a = a.getSiguiente();
            }
        }
        // Y ahora, lo más importante, si no se ha visitado un vértice más de dos veces, entonces, es cierto que el tamaño de ambos conjuntos es el mismo
        return visitados.size() == vertices.size();     // ¡y por ende, hay un camino, y se trata de un grafo conexo!
    }

    public Vertice buscarPadre(Conjunto<ParPadre> padres, Vertice hijo) {
        // Para poder encontrar al padre que está asociado al vértice necesitamos primero copia la lista de los padres
        NodoLista<ParPadre> actual = padres.getElementos().getCabeza();
        // Si el conjunto no está vacío, entonces
        while (actual != null) {
            // Se copia el par padre de la posición en la que nos encontramos
            ParPadre p = actual.getDato();
            // Y comprobamos si tiene al vértice como hijo
            if (p.getHijo().equals(hijo)) {
                // Si es así, devolvemos al par (hemos encontrado el padre)
                return p.getPadre();
            }
            // Se actualiza para seguir iterando
            actual = actual.getSiguiente();
        }
        // Si no sucede nada, el vértice está 'huérfano'
        return null;
    }

    // Getter del conjunto de los vértices de un grafo
    public Conjunto<Vertice<DatoVertice>> getVertices() {
        return vertices;
    }

    // Getter del conjunto de las artistas de un grafo
    public Conjunto<Arista<DatoArista, DatoVertice>> getAristas() {
        return aristas;
    }
}
