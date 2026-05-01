package clasesgrafo.auxiliar;

// Esta clase consiste en un tipo de relación dentro de la estructura del grafo
public class Par {
    // El par, por así decirlo, sería el predicado entre el sujeto y el objeto en el RDF...
    private String etiqueta;
    private long id;

    // Constructor con parámetros para un par indeterminado
    public Par(String e, long i) {
        etiqueta = e;
        id = i;
    }

    // Metodo que compara dos objetos para ver si se tratan del mismo 'par' / conjunto
    public boolean equals(Object o) {
        // Aquí primeramente habría que ver si el objeto instanciado es de la misma clase
        if (!(o instanceof Par)) {
            // Si no lo es, pues se descarta
            return false;
        }
        // Y si lo son, hay que ver si la etiqueta que cargan ambos miembros son iguales (si poseen el mismo tipo de relación)
        return ((Par)o).etiqueta.equals(this.etiqueta);
    }

    // Getter de la etiqueta del par
    public String getEtiqueta() {
        return etiqueta;
    }

    // Setter de la etiqueta del par
    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    // Getter del identificador del par
    public long getId() {
        return id;
    }

    // Setter del identificador del par
    public void setId(long id) {
        this.id = id;
    }
}