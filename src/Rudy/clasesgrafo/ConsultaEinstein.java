package clasesgrafo;

import extra.Lista;
import extra.NodoLista;

// Esta clase sirve para resolver la tercera pregunta sobre la consulta de Einstein
public class ConsultaEinstein {
    // Necesitaremos un grafo
    private Grafo grafo;

    // Y un constructor asociado a esta clase que será el grafo que pasemos con parámetro
    public ConsultaEinstein(Grafo grafo) {
        this.grafo = grafo;
    }

    // Lo primero que deberíamos de hacer será buscar el vértice según la etiqueta (relación)
    public Vertice<DatoVertice> buscarVerticePorEtiqueta(String etiqueta) {
        // Creamos una copia de los vértices del grafo (consiste en copiar una lista con los vértices del grafo)
        NodoLista<Vertice<DatoVertice>> v = grafo.getVertices().getElementos().getCabeza();
        // Ahora, iteramos mientras el conjunto deje de estar vacío
        while (v != null) {
            // Y si en algún punto el dato del conjunto de vértices del grafo equivale a la relación que buscamos
            if (v.getDato().getDato().equals(etiqueta)) {
                // Entonces podemos decir que hemos encontrado el vértice que necesitabamos
                return v.getDato();
            }
            // Actualizamos la posición iterando al miembro siguiente
            v = v.getSiguiente();
        }
        // Si no sucede nunca la igualdad, entonces, devolvemos 'null' como señal de que ese vértice con esa etiqueta NO está
        return null;
    }

    // Lo siguiente sería comprobar el predicado, viendo si el vértice que buscamos, es físico o no es físico
    public boolean esFisico(Vertice<DatoVertice> v) {
        // Se crea una copia del conjunto de las aristas (lista) del grafo
        NodoLista<Arista<DatoArista, DatoVertice>> a = grafo.getAristas().getElementos().getCabeza();
        // Y al igual que con los vértices, se itera hasta que el conjunto deje de estar vacío
        while (a != null) {
            // Se copia el dato asociado a la arista para poder empezar a comparar
            Arista<DatoArista, DatoVertice> arista = a.getDato();
            // Ahora, si el vértice de origen de la aristam es el mismo que buscabamos, su valor es "tipo", y el dato del vértice final es "físico"
            if (arista.getOrigen().equals(v) && arista.getDato().getValor().equals("tipo") && arista.getFin().getDato().equals("fisico")) {
                // Entonces sí, hay una relación de físicos
                return true;
            }
            // Como siempre, se actualiza para crear esa sensación de iteración...
            a = a.getSiguiente();
        }
        // Si no sucede nada de lo anterior en ningún punto, pues se puede concluir obviamente con que no existe ese tipo de relación
        return false;
    }

    // Ahora resolveremos la pregunta 3: "¿Qué físico famoso nació en la misma ciudad que Einstein?"
    public Lista<Vertice<DatoVertice>> fisicosMismaCiudadEinstein() {
        // Primero se busca si existe el vértice con el sujeto Einstein
        Vertice<DatoVertice> einstein = buscarVerticePorEtiqueta("persona:Einstein");
        if (einstein == null) {
            // Si está vacío, entonces no se puede hacer nada
            return null;
        }

        // Ahora, si Einstein existe en la tabla de relaciones, creamos un vértice vacío donde 'stackear' su ciudad de nacimiento
        Vertice<DatoVertice> ciudadEinstein = null;
        // Ahora copiamos la lista de aristas (relaciones) del grafo al RDF asociado
        NodoLista<Arista<DatoArista, DatoVertice>> a = grafo.getAristas().getElementos().getCabeza();
        // E iteramos hasta que este vacío...
        while (a != null) {
            Arista<DatoArista, DatoVertice> arista = a.getDato();
            // Se compara si existe la relación de nacer en (lugares de nacimiento) que contengan a Einstein como sujeto
            if (arista.getOrigen().equals(einstein) && arista.getDato().getValor().equals("nace_en")) {
                // Y si esto es verdad, entonces se obtiene el fín de la arista que es el vértice que contiene el objeto / ciudad de nacimiento
                ciudadEinstein = arista.getFin();
                // Y nos salimos del bucle
                break;
            }
            // Se actualiza la arista al miembro siguiente de la lista para crear sensación de iteración
            a = a.getSiguiente();
        }

        // Ahora si la ciudad es un vértice vacío entonces no hacemos nada, solo retornamos
        if (ciudadEinstein == null) {
            return null;
        }

        // Ahora si la ciudad también existe, pues creamos una lista vacía donde guardar las relaciones que contengan el mismo lugar de nacimiento que el sujeto 'Einstein'
        Lista<Vertice<DatoVertice>> resultado = new Lista<>();
        // Se crea una copia del conjunto de aristas/relaciones
        a = grafo.getAristas().getElementos().getCabeza();
        // Y se itera sobre ella hasta que no queden relaciones por recorrer
        while (a != null) {
            // Se obtiene el dato asociado a la arista
            Arista<DatoArista, DatoVertice> arista = a.getDato();
            // Y si la arista tiene el predicado de "nace_en" y el objeto que estamos buscando
            if (arista.getDato().getValor().equals("nace_en")  && arista.getFin().equals(ciudadEinstein)) {
                // Entonces copiamos el vértice de origen (persona/sujeto)
                Vertice<DatoVertice> persona = arista.getOrigen();
                // Y ahora comprobamos si se trata de un físico para poder añadirlo a la lista que habíamos creado
                if (esFisico(persona)) {
                    resultado.insertar(persona);
                }
            }
            // Actualizamos para iterar
            a = a.getSiguiente();
        }
        // Una vez hemos terminado de recorrer el grafo y sus conjuntos, devolvemos la lista de personas que son físicas y nacidas en el mismo lugar que Einstein
        return resultado;
    }

    // Y con este metodo podemos resolver la otra pregunta del apartado 4: "Liste cuáles son los lugares de
    //nacimiento de los premios Nobel. ¿Qué caminos necesita recorrer para que su respuesta fuese correcta?"
    public Lista<Vertice<DatoVertice>> lugaresNacimientoNobel() {
        // Se crea una lista vacía de vértice donde 'stakear' la información
        Lista<Vertice<DatoVertice>> resultado = new Lista<>();
        // Se hace una copia de las relaciones o aristas del grafo RDF
        NodoLista<Arista<DatoArista, DatoVertice>> a = grafo.getAristas().getElementos().getCabeza();
        // Y se itera sobre el conjunto hasta que esté vacío, miembro a miembro
        while (a != null) {
            // Se obtiene el dato asociado a la arista
            Arista<DatoArista, DatoVertice> arista = a.getDato();
            // Y primero, nos aseguramos si se trata de un premio Nóbel comprobanddo la relación (predicado, y objeto)
            if (arista.getDato().getValor().equals("premio") && arista.getFin().getDato().equals("Nobel")) {
                // Si la arista establece ese tipo de relación, copiamos el vértice de origen que contiene a la persona
                Vertice<DatoVertice> persona = arista.getOrigen();
                // Ahora se vuelve a hacer una copia del conjunto de todas las relaciones del grafo
                NodoLista<Arista<DatoArista, DatoVertice>> b = grafo.getAristas().getElementos().getCabeza();
                // Y se itera internamente...
                while (b != null) {
                    Arista<DatoArista, DatoVertice> arista2 = b.getDato();
                    // Comparando si la persona tiene una relación de nacimiento existente
                    if (arista2.getOrigen().equals(persona) && arista2.getDato().getValor().equals("nace_en")) {
                        // Si esto es así, se inserta el objeto de esa relación, que es el vértice final de la arista
                        resultado.insertar(arista2.getFin());
                    }
                    // Se actualiza e itera...
                    b = b.getSiguiente();
                }
            }
            // Se actualiza e itera...
            a = a.getSiguiente();
        }
        // ¡Y finalmente se devuelve la lista ya modificada!
        return resultado;
    }
}

