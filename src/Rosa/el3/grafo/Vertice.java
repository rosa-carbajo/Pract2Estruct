public class Vertice<T extends Comparable<T>>{
    //todo lo comentado que tiene que ver con el ID lo he eliminado porque me parece innecesario
    // ya que pensándolo mejor no tiene sentido que en un grafo existan dos vertices iguales,
    // pero aun asi lo dejo porque la idea de utilizar static para ir enumerando los vértices me parecía muy buena idea

    //private static Integer IDContador = 0;
    //private Integer ID;
    private T dato;
    private Lista_Simple<Arista<T>> aristas_antes;
    private Lista_Simple<Arista<T>> aristas_hijo;

    //--CONSTRUCTOR--
    public Vertice( T dato) {
        //IDContador++;
        //this.ID = IDContador;
        this.dato = dato;
        aristas_antes = new Lista_Simple<>();
        aristas_hijo = new Lista_Simple<>();
    }

    //--AÑADIR ARISTA---
    public void addArista_antes(Arista<T> nueva_arista){this.aristas_antes.anadir(nueva_arista);}
    public void addArista_hijo(Arista<T> nueva_arista){
        this.aristas_hijo.anadir(nueva_arista);
    }

    //public Integer getID() {return ID;}
    //public void setID(Integer ID) {this.ID = ID;}

    //-------GETTERS Y SETTERS------
    //DEL DATO
    public T getDato()
    {
        return dato;
    }
    public void setDato(T dato) {
        this.dato = dato;
    }

    //DE LAS ARISTAS ANTERIORES
    public Lista_Simple<Arista<T>> getAristas_antes() {
        return aristas_antes;
    }
    public void setAristas_antes(Lista_Simple<Arista<T>> aristas_antes) {
        this.aristas_antes = aristas_antes;
    }

    //DE LAS ARISTAS POSTERIORES
    public Lista_Simple<Arista<T>> getAristas_hijo() {
        return aristas_hijo;
    }
    public void setAristas_hijo(Lista_Simple<Arista<T>> aristas_hijo) {
        this.aristas_hijo = aristas_hijo;
    }


    //--TO STRING--
    public String toString()
    {
        //El usuario solo querrá saber el dato del vértice no sus aristas,
        // si quiere las aristas pedirá las aristas en su correspondiente método
        return dato.toString();
    }

    @Override
    public boolean equals(Object o) {
        // 1. ¿Es exactamente el mismo objeto en memoria?
        if (this == o) 
            return true;
        
        // 2. ¿Es nulo o de una clase diferente?
        if (o == null || getClass() != o.getClass()) 
            return false;
        
        // 3. Convertimos el objeto a Vertice para comparar su interior
        Vertice<?> other = (Vertice<?>) o;
        
        // 4. Comparamos el 'dato' (que es un String como "persona:Albert Einstein")
        if (this.dato == null) return other.dato == null;
        return this.dato.equals(other.getDato());
    }

}
