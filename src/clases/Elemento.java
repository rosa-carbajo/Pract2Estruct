package clases;

// Se crea una clase 'Elemento' que está parametrizada por el tipo T, y actúa como el dato que se va a introducir dentro de un nodo del árbol
public class Elemento <T> {
    // Atributo que representa la información contenida
    private T valor;

    // Constructor por defecto de la clase (se trata como un conjunto vacío {∅})
    public Elemento() {
        this.valor = null;
    }

    // Constructor de la clase,  con su argumento correspondiente
    public Elemento(T valor) {
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
