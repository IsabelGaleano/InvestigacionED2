import java.util.*;
public class ArbolBP {
    int m;
    public NodoInterno root;
    public Hoja primeraHoja;

    // Busqueda
    private int binarySearch(ParDiccionario[] dps, int numpares, int t) {
        Comparator<ParDiccionario> c = new Comparator<ParDiccionario>() {
            @Override
            public int compare(ParDiccionario o1, ParDiccionario o2) {
                Integer a = Integer.valueOf(o1.key);
                Integer b = Integer.valueOf(o2.key);
                return a.compareTo(b);
            }
        };
        return Arrays.binarySearch(dps, 0, numpares, new ParDiccionario(t, 0), c);
    }
    public Double buscar(int key) {

        if (isEmpty()) {
            return null;
        }

        Hoja ln = (this.root == null) ? this.primeraHoja : findHoja(key);

        ParDiccionario[] dps = ln.dictionary;
        int index = binarySearch(dps, ln.numPares, key);

        if (index < 0) {
            return null;
        } else {
            return dps[index].value;
        }
    }

    public ArrayList<Double> buscar(int limiteInferior, int limiteSuperior) {

        ArrayList<Double> values = new ArrayList<Double>();

        Hoja currNodoBP = this.primeraHoja;
        while (currNodoBP != null) {

            ParDiccionario dps[] = currNodoBP.dictionary;
            for (ParDiccionario dp : dps) {

                if (dp == null) {
                    break;
                }

                if (limiteInferior <= dp.key && dp.key <= limiteSuperior) {
                    values.add(dp.value);
                }
            }
            currNodoBP = currNodoBP.hermanoDerecho;

        }

        return values;

    }

    // Encontrar Hoja
    private Hoja findHoja(int key) {

        Integer[] keys = this.root.keys;
        int i;

        for (i = 0; i < this.root.grado - 1; i++) {
            if (key < keys[i]) {
                break;
            }
        }

        NodoBP child = this.root.punterosHijo[i];
        if (child instanceof Hoja) {
            return (Hoja) child;
        } else {
            return findHoja((NodoInterno) child, key);
        }
    }

    // Encontrar hoja
    private Hoja findHoja(NodoInterno NodoBP, int key) {

        Integer[] keys = NodoBP.keys;
        int i;

        for (i = 0; i < NodoBP.grado - 1; i++) {
            if (key < keys[i]) {
                break;
            }
        }
        NodoBP childNodoBP = NodoBP.punterosHijo[i];
        if (childNodoBP instanceof Hoja) {
            return (Hoja) childNodoBP;
        } else {
            return findHoja((NodoInterno) NodoBP.punterosHijo[i], key);
        }
    }

    // Encontrar el índice de un puntero
    private int findIndexOfPuntero(NodoBP[] punteros, Hoja NodoBP) {
        int i;
        for (i = 0; i < punteros.length; i++) {
            if (punteros[i] == NodoBP) {
                break;
            }
        }
        return i;
    }

    // Conseguir el punto medio
    private int getpuntoMedio() {
        return (int) Math.ceil((this.m + 1) / 2.0) - 1;
    }

    //Balancear arbol
    private void manejarDeficiencia(NodoInterno in) {

        NodoInterno hermano;
        NodoInterno padre = in.padre;

        if (this.root == in) {
            for (int i = 0; i < in.punterosHijo.length; i++) {
                if (in.punterosHijo[i] != null) {
                    if (in.punterosHijo[i] instanceof NodoInterno) {
                        this.root = (NodoInterno) in.punterosHijo[i];
                        this.root.padre = null;
                    } else if (in.punterosHijo[i] instanceof Hoja) {
                        this.root = null;
                    }
                }
            }
        }

        else if (in.hermanoIzquierdo != null && in.hermanoIzquierdo.isPrestable()) {
            hermano = in.hermanoIzquierdo;
        } else if (in.hermanoDerecho != null && in.hermanoDerecho.isPrestable()) {
            hermano = in.hermanoDerecho;

            int borrowedKey = hermano.keys[0];
            NodoBP pointer = hermano.punterosHijo[0];

            in.keys[in.grado - 1] = padre.keys[0];
            in.punterosHijo[in.grado] = pointer;

            padre.keys[0] = borrowedKey;

            hermano.removePuntero(0);
            Arrays.sort(hermano.keys);
            hermano.removePuntero(0);
            moverAbajo(in.punterosHijo, 1);
        } else if (in.hermanoIzquierdo != null && in.hermanoIzquierdo.isMezclable()) {

        } else if (in.hermanoDerecho != null && in.hermanoDerecho.isMezclable()) {
            hermano = in.hermanoDerecho;
            hermano.keys[hermano.grado - 1] = padre.keys[padre.grado - 2];
            Arrays.sort(hermano.keys, 0, hermano.grado);
            padre.keys[padre.grado - 2] = null;

            for (int i = 0; i < in.punterosHijo.length; i++) {
                if (in.punterosHijo[i] != null) {
                    hermano.preagregarPunteroHijo(in.punterosHijo[i]);
                    in.punterosHijo[i].padre = hermano;
                    in.removePuntero(i);
                }
            }

            padre.removePuntero(in);

            hermano.hermanoIzquierdo = in.hermanoIzquierdo;
        }

        if (padre != null && padre.isDeficiente()) {
            manejarDeficiencia(padre);
        }
    }

    private boolean isEmpty() {
        return primeraHoja == null;
    }

    private int linearNullSearch(ParDiccionario[] dps) {
        for (int i = 0; i < dps.length; i++) {
            if (dps[i] == null) {
                return i;
            }
        }
        return -1;
    }

    private int linearNullSearch(NodoBP[] punteros) {
        for (int i = 0; i < punteros.length; i++) {
            if (punteros[i] == null) {
                return i;
            }
        }
        return -1;
    }

    private void moverAbajo(NodoBP[] punteros, int amount) {
        NodoBP[] newpunteros = new NodoBP[this.m + 1];
        for (int i = amount; i < punteros.length; i++) {
            newpunteros[i - amount] = punteros[i];
        }
        punteros = newpunteros;
    }

    private void sortDictionary(ParDiccionario[] dictionary) {
        Arrays.sort(dictionary, new Comparator<ParDiccionario>() {
            @Override
            public int compare(ParDiccionario o1, ParDiccionario o2) {
                if (o1 == null && o2 == null) {
                    return 0;
                }
                if (o1 == null) {
                    return 1;
                }
                if (o2 == null) {
                    return -1;
                }
                return o1.compareTo(o2);
            }
        });
    }

    private NodoBP[] splitpunterosHijo(NodoInterno in, int split) {

        NodoBP[] punteros = in.punterosHijo;
        NodoBP[] halfpunteros = new NodoBP[this.m + 1];

        for (int i = split + 1; i < punteros.length; i++) {
            halfpunteros[i - split - 1] = punteros[i];
            in.removePuntero(i);
        }

        return halfpunteros;
    }

    private ParDiccionario[] splitDictionary(Hoja ln, int split) {

        ParDiccionario[] dictionary = ln.dictionary;

        ParDiccionario[] halfDict = new ParDiccionario[this.m];

        for (int i = split; i < dictionary.length; i++) {
            halfDict[i - split] = dictionary[i];
            ln.delete(i);
        }

        return halfDict;
    }

    private void splitNodoInterno(NodoInterno in) {

        NodoInterno padre = in.padre;

        int puntoMedio = getpuntoMedio();
        int newpadreKey = in.keys[puntoMedio];
        Integer[] halfKeys = splitKeys(in.keys, puntoMedio);
        NodoBP[] halfpunteros = splitpunterosHijo(in, puntoMedio);

        in.grado = linearNullSearch(in.punterosHijo);

        NodoInterno hermano = new NodoInterno(this.m, halfKeys, halfpunteros);
        for (NodoBP pointer : halfpunteros) {
            if (pointer != null) {
                pointer.padre = hermano;
            }
        }

        hermano.hermanoDerecho = in.hermanoDerecho;
        if (hermano.hermanoDerecho != null) {
            hermano.hermanoDerecho.hermanoIzquierdo = hermano;
        }
        in.hermanoDerecho = hermano;
        hermano.hermanoIzquierdo = in;

        if (padre == null) {

            Integer[] keys = new Integer[this.m];
            keys[0] = newpadreKey;
            NodoInterno newRoot = new NodoInterno(this.m, keys);
            newRoot.insertPunteroHijo(in);
            newRoot.insertPunteroHijo(hermano);
            this.root = newRoot;

            in.padre = newRoot;
            hermano.padre = newRoot;

        } else {

            padre.keys[padre.grado - 1] = newpadreKey;
            Arrays.sort(padre.keys, 0, padre.grado);

            int pointerIndex = padre.findIndexOfPuntero(in) + 1;
            padre.insertPunteroHijo(hermano, pointerIndex);
            hermano.padre = padre;
        }
    }

    private Integer[] splitKeys(Integer[] keys, int split) {

        Integer[] halfKeys = new Integer[this.m];

        keys[split] = null;

        for (int i = split + 1; i < keys.length; i++) {
            halfKeys[i - split - 1] = keys[i];
            keys[i] = null;
        }

        return halfKeys;
    }
    //método de inserción
    public void insertar(int key, double value) {
        if (isEmpty()) {

            Hoja ln = new Hoja(this.m, new ParDiccionario(key, value));

            this.primeraHoja = ln;

        } else {
            Hoja ln = (this.root == null) ? this.primeraHoja : findHoja(key);

            if (!ln.insert(new ParDiccionario(key, value))) {

                ln.dictionary[ln.numPares] = new ParDiccionario(key, value);
                ln.numPares++;
                sortDictionary(ln.dictionary);

                int puntoMedio = getpuntoMedio();
                ParDiccionario[] halfDict = splitDictionary(ln, puntoMedio);

                if (ln.padre == null) {

                    Integer[] padre_keys = new Integer[this.m];
                    padre_keys[0] = halfDict[0].key;
                    NodoInterno padre = new NodoInterno(this.m, padre_keys);
                    ln.padre = padre;
                    padre.insertPunteroHijo(ln);

                } else {
                    int newpadreKey = halfDict[0].key;
                    ln.padre.keys[ln.padre.grado - 1] = newpadreKey;
                    Arrays.sort(ln.padre.keys, 0, ln.padre.grado);
                }

                Hoja newHoja = new Hoja(this.m, halfDict, ln.padre);

                int pointerIndex = ln.padre.findIndexOfPuntero(ln) + 1;
                ln.padre.insertPunteroHijo(newHoja, pointerIndex);

                newHoja.hermanoDerecho = ln.hermanoDerecho;
                if (newHoja.hermanoDerecho != null) {
                    newHoja.hermanoDerecho.hermanoIzquierdo = newHoja;
                }
                ln.hermanoDerecho = newHoja;
                newHoja.hermanoIzquierdo = ln;

                if (this.root == null) {

                    this.root = ln.padre;

                } else {
                    NodoInterno in = ln.padre;
                    while (in != null) {
                        if (in.isLleno()) {
                            splitNodoInterno(in);
                        } else {
                            break;
                        }
                        in = in.padre;
                    }
                }
            }
        }
    }


}
