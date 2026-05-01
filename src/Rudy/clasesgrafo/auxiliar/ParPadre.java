package clasesgrafo.auxiliar;
import clasesgrafo.*;

// Esta clase consiste en el algoritmo que permite establecer un orden superior y ver quién genera a quién
public class ParPadre {
    // Por ejemplo en Einstein → Física → Nobel
    private Vertice hijo;       // Física sería el nodo actual, el hijo
    private Vertice padre;      // Y Einstein sería el ParPadre, el nodo que lo genera, que construye esa relación

    // Crear un ParPadre significa asociar a un vértice padre con un vértice hijo, establecer una relación (→)
    public ParPadre(Vertice h, Vertice p) {
        hijo = h;
        padre = p;
    }

    // Instanciamos un metodo equals como en Par, porque lo vamos a necesitar
    public boolean equals(Object o) {
        // Si no es del mismo tipo de instanciación, o sea, que el objeto no es un "ParPadre"
        if (!(o instanceof ParPadre)) {
            // ¡Pues es de cajón que no van a ser iguales!
            return false;
        }
        // Si no, se comparan los hijos para ver si ambos son el mismo padre...
        return ((ParPadre)o).hijo.equals(this.hijo);
    }

    // Getter del vértice hijo
    public Vertice getHijo() {
        return hijo;
    }

    // Setter del vértice hijo
    public void setHijo(Vertice hijo) {
        this.hijo = hijo;
    }

    // Getter del vértice padre
    public Vertice getPadre() {
        return padre;
    }

    // Setter del vértice padre
    public void setPadre(Vertice padre) {
        this.padre = padre;
    }
}
