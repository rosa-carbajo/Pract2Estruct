package es.uah.matcomp.ed.el3.grafo;


import Listas.Lista_simple.Lista_Simple;

public class Grafo_noDirigido<T extends Comparable<T>> {
    private Lista_Simple<Vertice<T>> vertices;
    private Lista_Simple<Arista<T>> aristas;
    private Integer contadorID;

    //--CONSTRUCTOR--
    public Grafo_noDirigido(){
        this.vertices = new Lista_Simple<>();
        this.aristas = new Lista_Simple<>();
    }

    //----AÑADIR----
    //--METODO DEL VÉRTICE--
    /**
     * Añade un nuevo vértice a la colección de vértices del grafo.
     * @param vertice El objeto {@link Vertice} que se desea integrar en el grafo.
     */
    public void addVertice(Vertice<T> vertice){
        this.vertices.anadir(vertice);
    }

    //--METODO DE LA ARISTA--
    /**
     * Añade una arista al grafo y actualiza las referencias de adyacencia
     * en los vértices de origen y destino.
     * @param arista La {@link Arista} que se desea añadir al grafo.
     */
    public void addArista(Arista<T> arista){
        this.aristas.anadir(arista);
        //Añadir esta arista a la lista de aristas hijo del vertice del que partimos(actualizar la lista aristas_hijo con esta arista)
        arista.getVerticeIni().addArista_hijo(arista);
        //Añadir esta arista a la lista de aristas anteriores del vertice de llegada(actualizar la lista aristas_antes con esta arista)
        arista.getVerticeFin().addArista_antes(arista);
    }

    //------BORRAR-------
    //--METODO DE LA ARISTA--
    //Le doy varias opciones para eliminar una arista al usuario

    /**
     * Elimina una arista del grafo basándose en sus vértices de origen y destino.
     * No es necesario que el usuario proporcione el objeto Arista exacto.
     *
     * @param vIni     Vértice de inicio de la arista.
     * @param vDestino Vértice de destino de la arista.
     */
    public void delArista(Vertice<T> vIni, Vertice<T> vDestino){
        // El usuario no tendría que crear una Arista para poder eliminarla, solo necesita saber los vértices de llegada y salida
        Arista<T> aBorrar = new Arista<>(vIni, vDestino );
        boolean veredicto = this.aristas.borrar(aBorrar);
        //Informo al usuario si se ha eliminado, y hago lo mismo con las otras variantes
        if (veredicto) System.out.println("Arista no encontrada, asegurate de que la arista existe");
        else System.out.println("Arista eliminada correctamente");

    }

    /**
     * Elimina una arista específica del grafo.
     * @param aBorrar El objeto {@link Arista} que se desea eliminar.
     */
    public void delArista(Arista<T> aBorrar){
        boolean veredicto = this.aristas.borrar(aBorrar);

        if (veredicto) System.out.println("Arista no encontrada, asegurate de que la arista existe");
        else System.out.println("Arista eliminada correctamente");

    }
    /**
     * Elimina una arista del grafo basándose en sus vértices y su anotación.
     * Útil para diferenciar aristas entre los mismos nodos con distintos metadatos.
     * @param vIni       Vértice de inicio.
     * @param vDestino   Vértice de destino.
     * @param anotacion  Anotación descriptiva de la arista.
     */
    public void delArista(Vertice<T> vIni, Vertice<T> vDestino, String anotacion){
        Arista<T> aBorrar = new Arista<>(vIni, vDestino, anotacion);
        boolean veredicto = this.aristas.borrar(aBorrar);

        if (veredicto) System.out.println("Arista no encontrada, asegurate de que la arista existe");
        else System.out.println("Arista eliminada correctamente");
    }

    //--MÉTODO DEL VÉRTICE--
    //Le doy varias posibles maneras de eliminar un vertice

    /**
     * Elimina un vértice del grafo a partir de su referencia de objeto.
     * @param vBorrar El objeto {@link Vertice} que se desea eliminar.
     */
    public void delVertice(Vertice<T> vBorrar){
        // Aquí no debería haber problemas
        boolean veredicto = this.vertices.borrar(vBorrar);

        if (veredicto) System.out.println("Arista no encontrada, asegurate de que la arista existe");
        else System.out.println("Arista eliminada correctamente");
    }

    /**
     * Elimina un vértice del grafo basándose en el dato que contiene.
     * Internamente, crea un vértice temporal para realizar la búsqueda y borrado.
     * @param dato El contenido o identificador del vértice que se desea eliminar.
     */
    public void delVertice(T dato){
        Vertice<T> aBorrar = new Vertice<>(dato);
        boolean veredicto = this.vertices.borrar(aBorrar);

        if (veredicto) System.out.println("Arista no encontrada, asegurate de que la arista existe");
        else System.out.println("Arista eliminada correctamente");
    }

    //-----VISUALIZACIÓN DE VERTICES Y ARISTAS------
    //Utilizamos el metodo visualizar de Lista_Simple para visualizarlas porque son Listas
    public void visualizarVertices(){
        this.vertices.visualizar();
    }
    public void visualizarAristas(){
        this.aristas.visualizar();
    }

    //-------GETTERS Y SETTERS------
    public Lista_Simple<Vertice<T>> getVertices(){
        return this.vertices;
    }
    public Lista_Simple<Arista<T>> getAristas(){
        return this.aristas;
    }

}
