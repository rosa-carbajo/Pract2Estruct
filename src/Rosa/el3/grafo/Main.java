package es.uah.matcomp.ed.el3.grafo;
import Listas.Lista_simple.Lista_Simple;
import Listas.Lista_simple.Nodo;
import com.google.gson.Gson;

// Coordina la carga del grafo desde archivos JSON, la manipulación manual de datos
// y la ejecución de consultas lógicas para demostrar la funcionalidad de la
// estructura de datos del grafo de conocimiento.
public class Main {

    // --- CLASES DE MODELO PARA GSON

    //Modelo interno para la deserialización de tipos desde JSON.
    static class GrafoJSON {
        public String[] tipos; 
        public TripletaJSON[] tripletas;
    }

    //Estructura clásica de tripletas
    static class TripletaJSON {
        public String s; // Sujeto
        public String p; // Predicado
        public String o; // Objeto
    }

    /**
     * Inicia la aplicación, carga el grafo de físicos y realiza inserciones manuales.
     * Muestra por consola el estado de los vértices, las aristas y el resultado
     * de las consultas semánticas.
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        String nombreArchivo = "datos.json";

        String nombreArchivoF = "fisicos.json";

        System.out.println("Iniciando la carga del Grafo de Conocimiento...");
        System.out.println("----------------------------------------------");

        // 1. Cargamos el grafo desde el archivo
        //Grafo_noDirigido<String> miGrafo = CargadorConocimiento.cargarGrafo(nombreArchivo);
        Grafo_noDirigido<String> miGrafo = CargadorConocimiento.cargarGrafo(nombreArchivoF);
        
        if (miGrafo != null) {
            // 2. Añadimos a Antonio manualmente como pide la práctica
            Vertice<String> vAntonio = new Vertice<>("persona:Antonio");
            Vertice<String> vLugar = new Vertice<>("lugar:Villarrubia de los Caballeros");
            miGrafo.addVertice(vAntonio);
            miGrafo.addVertice(vLugar);
            Arista<String> relacionAntonio = new Arista<>(vAntonio, vLugar, "nace_en");
            miGrafo.addArista(relacionAntonio);
            
            // 3. Visualizamos el estado del grafo
            System.out.println("\n[Vértices detectados]:");
            miGrafo.visualizarVertices();

            System.out.println("\n[Relaciones (Tripletas) procesadas]:");
            miGrafo.visualizarAristas();

            // --- 4. AQUÍ LLAMAMOS AL NUEVO CÓDIGO ---
            System.out.println("\n Ejecutando consulta: ¿Quién nació en la misma ciudad que Einstein?");
            responderConsultaFisico(miGrafo); 

            System.out.println("\n----------------------------------------------");
            System.out.println("Proceso finalizado.");
            
        } else {
            System.err.println("Error: No se pudo cargar el grafo.");
        }
    }

    /**
     * Realiza una consulta lógica sobre el grafo para identificar personas vinculadas
     * por el mismo lugar de nacimiento.
     * @param grafo El {@link Grafo_noDirigido} sobre el cual realizar la búsqueda.
     */
    // El metodo debe ser 'static' para poder llamarlo desde el main
    public static void responderConsultaFisico(Grafo_noDirigido<String> grafo) {
        // 1. Localizar el vértice de Einstein
        Vertice<String> einstein = new Vertice<>("persona:Albert Einstein");
        Nodo<Vertice<String>> nodoE = grafo.getVertices().buscar(einstein);

        if (nodoE != null) {
            // 2. Buscar en sus aristas el lugar de nacimiento usando getPrimero()
            Lista_Simple<Arista<String>> aristasE = nodoE.getData().getAristas_hijo();
            Nodo<Arista<String>> tempA = aristasE.getPrimero(); //[cite: 1]
            
            while (tempA != null) {
                Arista<String> arista = tempA.getData();
                if (arista.getAnotacion().equals("nace_en")) {
                    // Usamos tus nombres de métodos: getVerticeFin
                    Vertice<String> ciudad = arista.getVerticeFin();
                    System.out.println("-> Einstein nació en: " + ciudad.getDato());

                    // 3. Buscar quién más nació allí usando aristas_antes
                    Nodo<Arista<String>> tempC = ciudad.getAristas_antes().getPrimero();
                    while (tempC != null) {
                        // Usamos el nombre del metodo: getVerticeIni[cite: 2]
                        Vertice<String> personaEncontrada = tempC.getData().getVerticeIni();
                        
                        if (!personaEncontrada.getDato().equals("persona:Albert Einstein")) {
                            System.out.println("-> RESPUESTA ENCONTRADA: " + personaEncontrada.getDato() + " también nació en " + ciudad.getDato());
                        }
                        tempC = tempC.getSiguiente();
                    }
                }
                tempA = tempA.getSiguiente();
            }
        } else {
            System.out.println("No se encontró a Einstein en el grafo.");
        }
    }
}