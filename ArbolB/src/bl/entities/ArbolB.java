package bl.entities;

public class ArbolB {

    public Nodo raiz;

    public ArbolB() {
        raiz = new Nodo();
        raiz.setLeaf(true);
        raiz.setNumeroNodos(0);
        raiz.clave[0] = -1;
    }

    public void insertar(int k) {
        // root node r
        Nodo tmpR = raiz;
        if (tmpR.getNumeroNodos() == 3) {
            Nodo tmpS = new Nodo();
            raiz = tmpS;
            tmpS.setNumeroNodos(0);
            tmpS.setLeaf(false);
            tmpS.hijos[0] = tmpR;
            splitChild(tmpS, 1, tmpR);
            insertNonfull(tmpS, k);
        } else {
            insertNonfull(tmpR, k);
        }
    }

    public void insertNonfull(Nodo nodo, int valor) {
        int i = nodo.getNumeroNodos();
        int numNodos = nodo.getNumeroNodos();

        if (nodo.isLeaf()) {
            while (i >= 1 && valor < nodo.clave[i - 1]) {
                nodo.clave[i] = nodo.clave[i - 1];
                i--;
            }
            nodo.clave[i] = valor;
            nodo.setNumeroNodos(nodo.getNumeroNodos() + 1);

        } else {
            while (i >= 1 && valor < nodo.clave[i - 1]) {
                i--;
            }
            i++;

            if (nodo.hijos[i - 1].getNumeroNodos() == 3) {
                splitChild(nodo, i, nodo.hijos[i - 1]);

                if (valor > nodo.clave[i - 1]) {
                    i++;
                }
            }

            insertNonfull(nodo.hijos[i - 1], valor);
        }
    }

    public void splitChild(Nodo nodoPadre, int childIndex, Nodo newHijo) {

        Nodo tmpZ = new Nodo();
        tmpZ.setLeaf(newHijo.isLeaf());
        tmpZ.setNumeroNodos(1);
        tmpZ.clave[0] = newHijo.clave[2];

        if (!newHijo.isLeaf()) {
            tmpZ.hijos[1] = newHijo.hijos[3];
            tmpZ.hijos[0] = tmpZ.hijos[2];
        }
        newHijo.setNumeroNodos(1);

        for (int j = nodoPadre.getNumeroNodos() + 1; j >= childIndex + 1; j--) {
            nodoPadre.hijos[j] = nodoPadre.hijos[j - 1];
            nodoPadre.hijos[j - 1] = nodoPadre.hijos[j - 2];
        }
        // assign the reference to the node z ( node z is a child of the node x)
        nodoPadre.hijos[childIndex] = tmpZ;
        nodoPadre.clave[childIndex - 1] = newHijo.clave[1];
        // increment
        nodoPadre.setNumeroNodos(nodoPadre.getNumeroNodos() + 1);

    }

    public boolean buscar(Nodo nodo, int valor) {
        int i = 1;
        while (i <= nodo.getNumeroNodos() && valor > nodo.clave[i - 1]) {
            i++;
        }
        if (i <= nodo.getNumeroNodos() && valor == nodo.clave[i - 1]) {
            return true;
        }
        if (!nodo.isLeaf()) {
            // recursive call to search in the subtree of the node x
            return buscar(nodo.hijos[i - 1], valor);
        }
        return false;
    }

    public boolean buscar(int k) {
        Nodo x = raiz;
        return buscar(x, k);
    }

    public boolean eliminar(int k) {
        Nodo x = raiz; // getting the root node
        return eliminar(x, k); // calling the delete with the root node as starting point
    }

    public boolean eliminar(Nodo nodo, int valor) {
        int i = 1;
        while (i <= nodo.getNumeroNodos() && valor > nodo.clave[i - 1]) {
            i++;
        }
        if (nodo.isLeaf()) {
            if (i <= nodo.getNumeroNodos() && valor == nodo.clave[i - 1]) {
                // return true if the key k is found in the B Tree
                nodo.clave[i - 1] = 0;
                // Deleting and sifting
                for (int j = i - 1; j < nodo.getNumeroNodos() - 1; j++) {
                    nodo.clave[j] = nodo.clave[j + 1];
                    if (j + 1 == nodo.getNumeroNodos() - 1) {
                        nodo.setNumeroNodos(nodo.getNumeroNodos() - 1);
                    }
                }
                return true;
            }
        } else {
            return eliminar(nodo.hijos[i - 1], valor);
        }
        return false;
    }

    public void imprimir() {
        imprimirArbol(raiz, "");
    }

    public void imprimirArbol(Nodo node, String indent) {
        if (node == null) {
            System.out.println(indent + "El arbol B está vacío");
        } else {
            System.out.println(indent + " ");
            String childIndent = indent + "\t";
            for (int i = node.getNumeroNodos()-1; i >= 0; i--) {
                if (!node.isLeaf()) // Recursive Call
                {
                    imprimirArbol(node.hijos[i], childIndent);
                }
                if(node.clave[i] > 0)
                    System.out.println(childIndent + node.clave[i]);
            }
            if (!node.isLeaf()) // Recirsive Call
            {
                imprimirArbol(node.hijos[node.getNumeroNodos()], childIndent);
            }
        }
    }


}
