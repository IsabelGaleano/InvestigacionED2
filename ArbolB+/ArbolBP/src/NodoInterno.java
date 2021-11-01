public class NodoInterno extends NodoBP{
    int maxGrado;
    int minGrado;
    int grado;
     NodoInterno hermanoIzquierdo;
    NodoInterno hermanoDerecho;
    Integer[] keys;
    NodoBP[] punterosHijo;

    public void insertPunteroHijo(NodoBP puntero) {
        this.punterosHijo[grado] = puntero;
        this.grado++;
    }

    public int findIndexOfPuntero(NodoBP puntero) {
        for (int i = 0; i < punterosHijo.length; i++) {
            if (punterosHijo[i] == puntero) {
                return i;
            }
        }
        return -1;
    }

    public void insertPunteroHijo(NodoBP puntero, int index) {
        for (int i = grado - 1; i >= index; i--) {
            punterosHijo[i + 1] = punterosHijo[i];
        }
        this.punterosHijo[index] = puntero;
        this.grado++;
    }

    public boolean isDeficiente() {
        return this.grado < this.minGrado;
    }

    public boolean isPrestable() {
        return this.grado > this.minGrado;
    }

    public boolean isMezclable() {
        return this.grado == this.minGrado;
    }

    public boolean isLleno() {
        return this.grado == maxGrado + 1;
    }

    public void preagregarPunteroHijo(NodoBP puntero) {
        for (int i = grado - 1; i >= 0; i--) {
            punterosHijo[i + 1] = punterosHijo[i];
        }
        this.punterosHijo[0] = puntero;
        this.grado++;
    }

    public  void removeKey(int index) {
        this.keys[index] = null;
    }

    void removePuntero(int index) {
        this.punterosHijo[index] = null;
        this.grado--;
    }

    public void removePuntero(NodoBP puntero) {
        for (int i = 0; i < punterosHijo.length; i++) {
            if (punterosHijo[i] == puntero) {
                this.punterosHijo[i] = null;
            }
        }
        this.grado--;
    }
    //Constructores
    public NodoInterno(int m, Integer[] keys) {
        this.maxGrado = m;
        this.minGrado = (int) Math.ceil(m / 2.0);
        this.grado = 0;
        this.keys = keys;
        this.punterosHijo = new NodoBP[this.maxGrado + 1];
    }

    public NodoInterno(int m, Integer[] keys, NodoBP[] punteros) {
        this.maxGrado = m;
        this.minGrado = (int) Math.ceil(m / 2.0);
        this.keys = keys;
        this.punterosHijo = punteros;
    }

}
