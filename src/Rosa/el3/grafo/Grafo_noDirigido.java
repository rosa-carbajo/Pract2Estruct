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
    //--MÉTODO DEL VÉRTICE--
    public void addVertice(Vertice<T> vertice){
        this.vertices.anadir(vertice);
    }

    //--MÉTODO DE LA ARISTA--
    public void addArista(Arista<T> arista){
        this.aristas.anadir(arista);
        //Añadir esta arista a la lista de aristas hijo del vertice del que partimos(actualizar la lista aristas_hijo con esta arista)
        arista.getVerticeIni().addArista_hijo(arista);
        //Añadir esta arista a la lista de aristas anteriores del vertice de llegada(actualizar la lista aristas_antes con esta arista)
        arista.getVerticeFin().addArista_antes(arista);
    }

    //------BORRAR-------
    //--MÉTODO DE LA ARISTA--
    //Le doy varias opciones para eliminar una arista al usuario
    public void delArista(Vertice<T> vIni, Vertice<T> vDestino){
        // El usuario no tendría que crear una Arista para poder eliminarla, solo necesita saber los vértices de llegada y salida
        Arista<T> aBorrar = new Arista<>(vIni, vDestino );
        boolean veredicto = this.aristas.borrar(aBorrar);
        //Informo al usuario si se ha eliminado, y hago lo mismo con las otras variantes
        if (veredicto) System.out.println("Arista no encontrada, asegurate de que la arista existe");
        else System.out.println("Arista eliminada correctamente");

    }
    public void delArista(Arista<T> aBorrar){
        boolean veredicto = this.aristas.borrar(aBorrar);

        if (veredicto) System.out.println("Arista no encontrada, asegurate de que la arista existe");
        else System.out.println("Arista eliminada correctamente");

    }
    public void delArista(Vertice<T> vIni, Vertice<T> vDestino, String anotacion){
        Arista<T> aBorrar = new Arista<>(vIni, vDestino, anotacion);
        boolean veredicto = this.aristas.borrar(aBorrar);

        if (veredicto) System.out.println("Arista no encontrada, asegurate de que la arista existe");
        else System.out.println("Arista eliminada correctamente");
    }

    //--MÉTODO DEL VÉRTICE--
    //Le doy varias posibles maneras de eliminar un vertice
    public void delVertice(Vertice<T> vBorrar){
        // Aquí no debería haber problemas
        boolean veredicto = this.vertices.borrar(vBorrar);

        if (veredicto) System.out.println("Arista no encontrada, asegurate de que la arista existe");
        else System.out.println("Arista eliminada correctamente");
    }
    public void delVertice(T dato){
        Vertice<T> aBorrar = new Vertice<>(dato);
        boolean veredicto = this.vertices.borrar(aBorrar);

        if (veredicto) System.out.println("Arista no encontrada, asegurate de que la arista existe");
        else System.out.println("Arista eliminada correctamente");
    }

    //-----VISUALIZACIÓN DE VERTICES Y ARISTAS------
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
