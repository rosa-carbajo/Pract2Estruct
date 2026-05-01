package es.uah.matcomp.ed.el3.grafo;

/**
 * Clase para mapear la estructura del JSON.
 * Usamos arreglos nativos [] para no depender de java.util.List.
 */
public class GrafoJSON {
    //Arreglo que contiene las categorías o tipos definidos en el JSON.
    public String[] tipos;

    /**Arreglo de objetos {@link TripletaJSON} que representan las conexiones y relaciones del grafo.*/
    public TripletaJSON[] tripletas;

    //Clase interna que representa una sentencia semántica o relación
    public static class TripletaJSON {
        //Sigue la estructura clásica de tripletas de conocimiento:
        public String s; // Sujeto (vértice de origen )
        public String p; // Predicado (anotación o nombre de la arista)
        public String o; // Objeto (vértice de destino)
    }
}