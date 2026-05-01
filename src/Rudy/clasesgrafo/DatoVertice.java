package clasesgrafo;

public class DatoVertice<T> {
    private T valor;

    // Constructor por defecto de la clase (se trata como un conjunto vacío {∅})
    public DatoVertice() {
        this.valor = null;
    }

    // Constructor de la clase,  con su argumento correspondiente
    public DatoVertice(T valor) {
        this.valor = valor;
    }

    // Getter del dato o atributo 'valor'
    public T getValor() {
        return valor;
    }

    // Setter del dato o atributo 'valor'
    public void setValor(T valor) {
        this.valor = valor;
    }
}
