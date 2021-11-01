import java.util.Arrays;

public class Hoja extends NodoBP{
   public  int maxNumPares;
   public  int minNumPares;
   public  int numPares;
    public Hoja hermanoIzquierdo;
    public Hoja hermanoDerecho;
   public  ParDiccionario[] dictionary;

    public void delete(int index) {
        this.dictionary[index] = null;
        numPares--;
    }

    public boolean insert(ParDiccionario dp) {
        if (this.isFull()) {
            return false;
        } else {
            this.dictionary[numPares] = dp;
            numPares++;
            Arrays.sort(this.dictionary, 0, numPares);

            return true;
        }
    }
    //Metodos para balanceo
    public boolean isDeficiente() {
        return numPares < minNumPares;
    }

    public boolean isFull() {
        return numPares == maxNumPares;
    }

    public boolean isPrestable() {
        return numPares > minNumPares;
    }

    public boolean isMezclable() {
        return numPares == minNumPares;
    }

    public Hoja(int m, ParDiccionario dp) {
        this.maxNumPares = m - 1;
        this.minNumPares = (int) (Math.ceil(m / 2) - 1);
        this.dictionary = new ParDiccionario[m];
        this.numPares = 0;
        this.insert(dp);
    }

    public Hoja(int m, ParDiccionario[] dps, NodoInterno padre) {
        this.maxNumPares = m - 1;
        this.minNumPares = (int) (Math.ceil(m / 2) - 1);
        this.dictionary = dps;

        this.padre = padre;
    }
}
