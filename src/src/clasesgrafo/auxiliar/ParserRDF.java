package clasesgrafo.auxiliar;
import clasesgrafo.*;
import extra.NodoLista;

// Esta clase lo que hace es actuar como un convertidor entre tripletas RDF de formato JSON a una estructura de grafo mediante clases como Par, ParPadre, Grafo... etc
public class ParserRDF {
    private Grafo grafo;                    // el grafo sobre el que crearemos la estructura
    private Conjunto<Par> tabla;            // una tabla que contenga las relaciones (las cosas ya vistas)
    private long contador = 1;              // un contador que genera identificadores únicos

    // Constructor del Parser (convertidor)
    public ParserRDF(Grafo g) {
        // Se pasa un grafo...
        this.grafo = g;
        // Se crea una tabla a partir de un conjunto vacío donde cargar los datos
        this.tabla = new Conjunto<>();
    }

    // Este metodo consigue transformar de una tripleta de a un grafo
    public void procesarTripleta(String s, String p, String o) {
        // Busca si el sujeto existe, si es que ya está en la tabla de relaciones
        Long idS = obtenerId(tabla, s);
        // Si este no existe pues...
        if (idS == null) {
            // Se le atribuye un identificador único
            idS = contador++;
            // Se crea la relación par y se introduce en la tabla
            tabla.insertar(new Par(s, idS));
            // ¡Y se añade en el grafo!
            grafo.agregarVertice(idS, new DatoVertice<>(s));
        }

        // ¡Ahora se hace lo mismo con el objeto!
        Long idO = obtenerId(tabla, o);
        if (idO == null) {
            // Si no existe, se hace lo mismo que para el sujeto, crear un nodo...
            idO = contador++;
            tabla.insertar(new Par(o, idO));
            grafo.agregarVertice(idO, new DatoVertice<>(o));
        }
        // Esto lo que permite es crear el predicado, creando una nueva arista que conecte tanto al sujeto como al objeto, creando una relación única en el grafo
        grafo.agregarArista(contador++, new DatoArista<>(p), idS, idO);
    }

    // Este es para ver si existe ya alguna entidad/tripleta o no
    public Long obtenerId(Conjunto<Par> tabla, String etiqueta) {
        // Crea una copia del conjunto de los pares del parser
        NodoLista<Par> actual = tabla.getElementos().getCabeza();
        // Y se itera sobre ella hasta que no queden pares/relaciones por recorrer
        while (actual != null) {
            // Si se encuentra la etiqueta necesaria (relación que buscamos)
            Par p = actual.getDato();
            if (p.getEtiqueta().equals(etiqueta)) {
                // Se devuelve el identificador relacionado a la relación
                return p.getId();
            }
            // Con esto iteramos par a par para comprobar si se cumple
            actual = actual.getSiguiente();
        }
        // Y si no, pues devolvemos 'null' como señal de que esta relación ("este nodo") no existe...
        return null;
    }
}
