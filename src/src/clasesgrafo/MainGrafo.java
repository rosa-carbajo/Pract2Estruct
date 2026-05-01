package clasesgrafo;

import clasesgrafo.auxiliar.ParserRDF;
import extra.Lista;
import extra.NodoLista;

public class MainGrafo {
    public static void main(String[] args) {
        // Se crea un nuevo grafo
        Grafo grafo = new Grafo();
        // Y un parser (convertidor con bases de datos) asociado al grafo
        ParserRDF parser = new ParserRDF(grafo);
        // Se procesan diferentes tipos de tripletas con diferentes relaciones o personas
        parser.procesarTripleta("persona:Einstein", "nace_en", "lugar:Ulm");
        parser.procesarTripleta("persona:Einstein", "premio", "Nobel");
        parser.procesarTripleta("persona:Bohr", "nace_en", "lugar:Copenhague");
        parser.procesarTripleta("persona:Curie", "nace_en", "lugar:Varsovia");
        parser.procesarTripleta("persona:Feynman", "profesion", "fisico");

        // Tripleta de Antonio:
        parser.procesarTripleta("persona:Antonio", "nace_en", "lugar:Villarrubia de los Caballeros");

        // ¡Ahora se imprime el grafo!
        System.out.println("GRAFO: ");
        grafo.imprimir();

        // Consulta para obtener los ID's
        ConsultaEinstein consulta = new ConsultaEinstein(grafo);
        Vertice<DatoVertice> a = consulta.buscarVerticePorEtiqueta("persona:Einstein");
        Vertice<DatoVertice> b = consulta.buscarVerticePorEtiqueta("lugar:Ulm");
        // Pregunta 4 de la tripleta de Antonio y lo de lugares de nacimiento de los premios nóbeles:
        Lista<Vertice<DatoVertice>> lugares = consulta.lugaresNacimientoNobel();
        NodoLista<Vertice<DatoVertice>> aux2 = lugares.getCabeza();
        System.out.println("LUGARES DE NACIMIENTO DE PREMIOS NOBEL:");
        while (aux2 != null) {
            System.out.println(aux2.getDato().getDato());
            aux2 = aux2.getSiguiente();
        }

        // Y tratamos de buscar el camino mínimo
        System.out.println("CAMINO MÍNIMO: ");
        if (a != null && b != null) {
            Lista<Vertice<DatoVertice>> camino = grafo.caminoMinimo(a.getId(), b.getId());
            NodoLista<Vertice<DatoVertice>> aux = camino.getCabeza();
            while (aux != null) {
                System.out.print(aux.getDato().getDato() + " -> ");
                aux = aux.getSiguiente();
            }
            System.out.println("FIN");
        }

        // Esto es para poder ver si se trata de un grafo disjunto (disconexo) o no...
        System.out.println("¿Es conexo?: " + grafo.esConexo());
    }
}