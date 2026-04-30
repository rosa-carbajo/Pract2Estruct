package es.uah.matcomp.ed.el3.grafo;

public class Arista<T extends Comparable<T>>{
    private Vertice<T> verticeIni;
    private Vertice<T> verticeFin;
    private String anotacion;
    private Integer peso;

    //--CONSTRUCTORES--
    public Arista(Vertice<T> inicial, Vertice<T> fin, String anotacion, int peso) {
        this.verticeIni = inicial;
        this.verticeFin = fin;
        this.anotacion = anotacion;
        this.peso = peso;
    }
    public Arista(Vertice<T> inicial, Vertice<T> fin, String anotacion) {
        this.verticeIni = inicial;
        this.verticeFin = fin;
        this.anotacion = anotacion;
        this.peso = 0;
    }
    public Arista(Vertice<T> inicial, Vertice<T> fin) {
        this.verticeIni = inicial;
        this.verticeFin = fin;
    }

    //-------GETTERS Y SETTERS------
    public Vertice<T> getVerticeIni() {
        return verticeIni;
    }
    public void setVerticeIni(Vertice<T> verticeIni) {
        this.verticeIni = verticeIni;
    }

    public Vertice<T> getVerticeFin() {
        return verticeFin;
    }
    public void setVerticeFin(Vertice<T> verticeFin) {
        this.verticeFin = verticeFin;
    }

    public String getAnotacion() {
        return anotacion;
    }
    public void setAnotacion(String anotacion) {
        this.anotacion = anotacion;
    }

    public Integer getPeso() {
        return peso;
    }
    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    //--TO STRING--
    public String toString(){
        return "{"+ this.verticeIni+ ", "+ this.verticeFin+ ", "+ this.anotacion+ ", "+ this.peso+ " }";
    }


    //------------
    //Esta función la sobreescribimos porque a la hora de borrar las aristas en el grafo,
    // tenemos el problema de que el usuario solamente introducirá el vertice inicial y final
    // sin tener en cuenta el peso ni la anotación. Entonces para solucionar esto cambiamos el método equals
    // para que solo compare los vertices
    @Override
    public boolean equals(Object o){
        if(!(o instanceof Arista))return false;
        Arista<T> otra = (Arista<T>) o;
        // Y como el grafo es no dirigido, nos da igual el orden
        //Aquí comparamos si tiene la misma anotacion, peso, vertices iniciales y finales con alguna de las aristas ya existentes
        return (this.verticeIni.equals(otra.verticeIni) && this.verticeFin.equals(otra.verticeFin) && this.peso.equals(otra.peso) && this.anotacion.equals(otra.anotacion))
                || (this.verticeIni.equals(otra.verticeFin) && this.verticeFin.equals(otra.verticeIni)&& this.peso.equals(otra.peso) && this.anotacion.equals(otra.anotacion));

    }

}
