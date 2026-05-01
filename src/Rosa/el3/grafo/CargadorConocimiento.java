package es.uah.matcomp.ed.el3.grafo;

import Listas.Lista_simple.Nodo;
import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;


//Clase que permite transformar una estructura de datos en formato JSON (siguiendo el modelo de tripletas)
// a una instancia funcional de Grafo_noDirigido

public class CargadorConocimiento {

    /**
     * Carga y construye un grafo no dirigido a partir de un archivo JSON.
     * El método utiliza la librería Gson para deserializar el archivo en un modelo
     * intermedio (GrafoJSON) y luego procesa cada tripleta (Sujeto, Predicado, Objeto)
     * para generar los vértices y aristas correspondientes.
     *
     * @param rutaArchivo Ruta completa o relativa al archivo .json que contiene el grafo.
     * @return Una instancia de {@link Grafo_noDirigido} poblada con los datos del archivo,
     *         o {@code null} si ocurre un error de lectura o el archivo no existe.
     */
    public static Grafo_noDirigido<String> cargarGrafo(String rutaArchivo) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(rutaArchivo)) {
            // 1. Leemos el JSON a nuestro modelo 
            GrafoJSON datos = gson.fromJson(reader, GrafoJSON.class);
            
            // 2. Creamos tu instancia de grafo
            Grafo_noDirigido<String> grafo = new Grafo_noDirigido<>();
            
            if (datos.tripletas != null) {
                for (GrafoJSON.TripletaJSON t : datos.tripletas) {
                    // Obtenemos o creamos los vértices usando tus clases
                    Vertice<String> vSujeto = obtenerOCrear(grafo, t.s);
                    Vertice<String> vObjeto = obtenerOCrear(grafo, t.o);

                    // Creamos la arista con el predicado como anotación[cite: 5]
                    Arista<String> arista = new Arista<>(vSujeto, vObjeto, t.p);
                    
                    // Añadimos la arista al grafo
                    grafo.addArista(arista);
                }
            }
            return grafo;
            
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return null;
        }
    }

    /**
     * Recupera un vértice existente del grafo o crea uno nuevo si no se encuentra.
     * Este método garantiza que no se dupliquen vértices con el mismo identificador
     * (dato) dentro del grafo, manteniendo la integridad referencial para las aristas.
     *
     * @param grafo  El grafo en el cual buscar o añadir el vértice.
     * @param nombre El valor textual (dato) que identifica al vértice.
     * @return El {@link Vertice} encontrado en el grafo o el nuevo vértice recién creado.
     */
    private static Vertice<String> obtenerOCrear(Grafo_noDirigido<String> grafo, String nombre) {
        // Usamos el método buscar de tu Lista_Simple
        // Nota: Vertice debe ser comparable para que funcione correctamente
        Vertice<String> temporal = new Vertice<>(nombre);
        
        // Buscamos en la lista de vértices del grafo
        Nodo<Vertice<String>> nodoEncontrado = grafo.getVertices().buscar(temporal);
        
        if (nodoEncontrado != null) {
            return nodoEncontrado.getData();
        } else {
            // Si no existe, usamos tu método de añadir vértice
            grafo.addVertice(temporal);
            return temporal;
        }
    }
}