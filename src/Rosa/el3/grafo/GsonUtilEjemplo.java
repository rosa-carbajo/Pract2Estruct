package es.uah.matcomp.ed.el3.grafo;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


//Clase de utilidad para la serialización y deserialización de objetos en formato JSON
//Proporciona métodos genéricos para facilitar la persistencia de datos en el sistema
//de archivos utilizando la librería Google Gson
public class GsonUtilEjemplo {
    // Metodo para guardar un objeto en un archivo JSON
    /**
     * Serializa un objeto de cualquier tipo y lo guarda en un archivo de texto.
     *
     * @param <T>         Tipo genérico del objeto a guardar.
     * @param rutaArchivo Ruta o nombre del archivo donde se almacenará el JSON.
     * @param objeto      Instancia del objeto que se desea persistir.
     */
    public static <T> void guardarObjetoEnArchivo(String rutaArchivo, T objeto) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(rutaArchivo)) {
            gson.toJson(objeto, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Metodo para cargar un objeto desde un archivo JSON
    /**
     * Lee un archivo JSON y lo transforma (deserializa) en una instancia de la clase especificada.
     *
     * @param <T>         Tipo genérico del objeto esperado.
     * @param rutaArchivo Ruta del archivo .json a leer.
     * @param clase       La clase (.class) que define el tipo del objeto a retornar.
     * @return Una instancia del tipo {@code T} con los datos cargados,
     *         o {@code null} si ocurre una excepción durante la lectura.
     */
    public static <T> T cargarObjetoDesdeArchivo(String rutaArchivo, Class<T> clase) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(rutaArchivo)) {
            return gson.fromJson(reader, clase);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    //Clase interna de ejemplo que representa la entidad de un usuario.
    //Utilizada exclusivamente para demostrar los procesos de guardado y carga de JSON.
    public static void main(String[] args) {
        // Crear una instancia del objeto Usuario
        Usuario usuario = new Usuario("Juan", 30, "juan@example.com");
        // Ruta del archivo donde se guardará el objeto
        String rutaArchivo = "usuario.json";
        // Guardar el objeto Usuario en un archivo JSON
        guardarObjetoEnArchivo(rutaArchivo, usuario);
        // Cargar el objeto Usuario desde el archivo JSON
        Usuario usuarioCargado = cargarObjetoDesdeArchivo(rutaArchivo, Usuario.class);
        if (usuarioCargado != null) {
            System.out.println("Usuario cargado: " + usuarioCargado.nombre);
        }
    }

    // Clase Usuario para los ejemplos
    static class Usuario {
        String nombre; //Nombre del usuario
        int edad; //Edad del usuario
        String correo; //Dirección de correo electrónico de contacto.
        /**
         * Constructor para inicializar una nueva instancia de Usuario.
         *
         * @param nombre Nombre completo.
         * @param edad   Edad en años.
         * @param correo Email único.
         */
        public Usuario(String nombre, int edad, String correo) {
            this.nombre = nombre;
            this.edad = edad;
            this.correo = correo;
        }
// Getters y setters no incluidos por brevedad
    }
}