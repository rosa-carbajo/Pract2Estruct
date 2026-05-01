import com.google.gson.Gson;

public class Main {

    // --- CLASES DE MODELO PARA GSON ---[cite: 3]
    static class GrafoJSON {
        public String[] tipos; 
        public TripletaJSON[] tripletas;
    }

    static class TripletaJSON {
        public String s; // Sujeto
        public String p; // Predicado
        public String o; // Objeto
    }

    public static void main(String[] args) {
        String nombreArchivo = "datos.json"; // Asegúrate de que este archivo tenga a Einstein y Max Planck

        String nombreArchivoF = "fisicos.json";

        System.out.println("Iniciando la carga del Grafo de Conocimiento...");
        System.out.println("----------------------------------------------");

        // 1. Cargamos el grafo desde el archivo[cite: 3]
        //Grafo_noDirigido<String> miGrafo = CargadorConocimiento.cargarGrafo(nombreArchivo);
        Grafo_noDirigido<String> miGrafo = CargadorConocimiento.cargarGrafo(nombreArchivoF);
        
        if (miGrafo != null) {
            // 2. Añadimos a Antonio manualmente como pide la práctica[cite: 3]
            Vertice<String> vAntonio = new Vertice<>("persona:Antonio");
            Vertice<String> vLugar = new Vertice<>("lugar:Villarrubia de los Caballeros");
            miGrafo.addVertice(vAntonio);
            miGrafo.addVertice(vLugar);
            Arista<String> relacionAntonio = new Arista<>(vAntonio, vLugar, "nace_en");
            miGrafo.addArista(relacionAntonio);
            
            // 3. Visualizamos el estado del grafo[cite: 3]
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

    // El método debe ser 'static' para poder llamarlo desde el main
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
                        // Usamos tu nombre de método: getVerticeIni[cite: 2]
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