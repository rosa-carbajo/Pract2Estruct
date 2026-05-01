package clasesgrafo;

public class Vertice<DatoVertice> {
    // Parámetros necesarios para poder crear un vértice:
    private long id;                    // un identificador
    private DatoVertice dato;           // un dato asociado al vértice

    // Constructor de los vértices
    public Vertice(Long id, DatoVertice dato) {
        this.id = id;
        this.dato = dato;
    }

    // Getter del identificador del vértice
    public long getId() {
        return id;
    }

    // Setter del identificador del vértice
    public void setId(long id) {
        this.id = id;
    }

    // Getter del dato asociado al vértice
    public DatoVertice getDato() {
        return dato;
    }

    // Setter del dato asociado al vértice
    public void setDato(DatoVertice dato) {
        this.dato = dato;
    }

    // Metodo para ver si hay algún vértice equivalente al que queremos crear o instanciar
    public boolean equals(Object o) {
        // Si el objeto con el que lo comparamos es literalmente el mismo...
        if (this == o) {
            // Devolvemos que, sí, se trata del mismo objeto
            return true;
        }

        // Si el objeto con el que lo comparamos no se trata de uno de tipo vértice (única posibilidad, ya que el metodo solo se empleará para eso)
        if (!(o instanceof Vertice)) {
            // Se devuelve que no, ¡que de ninguna manera iba a ser cierto!
            return false;
        }

        // Finalmente, si no es el mismo objeto, pero sí de la misma clase, pues se le hace un casteo
        Vertice<DatoVertice> v = (Vertice<DatoVertice>) o;
        // Y se comparan sus identificadores
        return (this.id == v.id);
    }
}
