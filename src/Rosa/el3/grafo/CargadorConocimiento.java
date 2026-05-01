import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;

public class CargadorConocimiento {

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