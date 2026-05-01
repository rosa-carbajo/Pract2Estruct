//package es.uah.matcomp.ed.el3.grafo;

/**
 * Clase para mapear la estructura del JSON.
 * Usamos arreglos nativos [] para no depender de java.util.List.
 */
public class GrafoJSON {
    public String[] tipos;
    public TripletaJSON[] tripletas;

    public static class TripletaJSON {
        public String s; // Sujeto
        public String p; // Predicado
        public String o; // Objeto
    }
}