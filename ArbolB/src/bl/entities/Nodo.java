package bl.entities;

public class Nodo {
    private int numeroNodos;
    public int clave[];
    public Nodo hijos[];
    private boolean isLeaf;

    Nodo() {
        clave = new int[5];
        hijos = new Nodo[4];
        isLeaf = true;
    }

    public int getNumeroNodos() {
        return numeroNodos;
    }

    public void setNumeroNodos(int numeroNodos) {
        this.numeroNodos = numeroNodos;
    }

    public int[] getClave() {
        return clave;
    }

    public void setClave(int[] clave) {
        this.clave = clave;
    }

    public Nodo[] getHijos() {
        return hijos;
    }

    public void setHijos(Nodo[] hijos) {
        this.hijos = hijos;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }
}
