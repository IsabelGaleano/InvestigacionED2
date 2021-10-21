package bl.logic;

import bl.entities.ArbolAVL;

public class Gestor {
    private ArbolAVL arbolAVL;
    private String resultado;
    public Gestor() {
        arbolAVL = new ArbolAVL();
        resultado = "";
    }

    public void insertar(int elemento) {
        arbolAVL.insertar(elemento);
    }

    public int totalNodos() {
       return arbolAVL.getNumeroTotalNodos();
    }

    public String buscarElemento(int elemento) {
        if (arbolAVL.buscarElemento(elemento)) {
            return "Elemento encontrado";
        } else {
            return "El elemento no se encuentra";
        }
    }

    public String inOrden() {
        resultado = arbolAVL.inOrder();
        arbolAVL.setResultado("");
        return resultado;
    }

    public String postOrden() {
        resultado = arbolAVL.postOrden();
        arbolAVL.setResultado("");
        return resultado;
    }

    public String preOrden() {
        resultado = arbolAVL.preOrder();
        arbolAVL.setResultado("");
        return resultado;
    }



}
