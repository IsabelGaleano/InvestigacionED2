package bl.entities;

public class Nodo {
    private int elemento;
    private int altura;
    private Nodo izquierda;
    private Nodo derecha;

    public Nodo() {
        elemento = 0;
        altura = 0;
        izquierda = null;
        derecha = null;
    }

    public Nodo(int elemento) {
        this.elemento = elemento;
        this.altura = 0;
        this.izquierda = null;
        this.derecha = null;
    }

    public int getElemento() {
        return elemento;
    }

    public void setElemento(int elemento) {
        this.elemento = elemento;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public Nodo getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(Nodo izquierda) {
        this.izquierda = izquierda;
    }

    public Nodo getDerecha() {
        return derecha;
    }

    public void setDerecha(Nodo derecha) {
        this.derecha = derecha;
    }
}
