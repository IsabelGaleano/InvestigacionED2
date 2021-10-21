package bl.entities;

public class ArbolAVL {

    private Nodo raiz;
    private String resultado;
    public ArbolAVL() {
        this.raiz = null;
    }

    public void eliminarArbol() {
        raiz = null;
    }

    public boolean esVacio() {
        if(raiz == null)
            return true;
        else
            return false;
    }

    public void insertar(int element)
    {
        raiz = insertar(element, raiz);
    }

    private int getAltura(Nodo nodo )
    {
        return nodo == null ? -1 : nodo.getAltura();
    }

    private int getAlturaMaxima(int alturaIzquierda, int alturaDerecha)
    {
        return alturaIzquierda > alturaDerecha ? alturaIzquierda : alturaDerecha;
    }

    private Nodo insertar(int element, Nodo nodo)
    {
        //check whether the node is null or not
        if (nodo == null)
            nodo = new Nodo(element);

        else if (element < nodo.getElemento())
        {
            nodo.setIzquierda(insertar(element, nodo.getIzquierda()));
            if( getAltura( nodo.getIzquierda() ) - getAltura( nodo.getDerecha() ) == 2 )
                if( element < nodo.getIzquierda().getElemento() )
                    nodo = rotacionSimpleIzquierda(nodo);
                else
                    nodo = rotacionDobleIzquierda(nodo);
        }
        else if( element > nodo.getElemento() )
        {
            nodo.setDerecha(insertar(element,nodo.getDerecha()));
            if( getAltura( nodo.getDerecha() ) - getAltura( nodo.getIzquierda() ) == 2 )
                if( element > nodo.getDerecha().getElemento())
                    nodo = rotacionSimpleDerecha(nodo);
                else
                    nodo = rotacionDobleDerecha(nodo);
        }
        else
            ;  // if the element is already present in the tree, we will do nothing
        nodo.setAltura(getAlturaMaxima( getAltura( nodo.getIzquierda() ), getAltura(nodo.getDerecha())) + 1);

        return nodo;

    }

    private Nodo rotacionSimpleIzquierda(Nodo nodo2)
    {
        Nodo nodo1 = nodo2.getIzquierda();
        nodo2.setIzquierda(nodo1.getDerecha());
        nodo1.setDerecha(nodo2);
        nodo2.setAltura(getAlturaMaxima( getAltura( nodo2.getIzquierda() ), getAltura( nodo2.getDerecha() ) ) + 1);
        nodo1.setAltura(getAlturaMaxima( getAltura( nodo1.getIzquierda() ), nodo2.getAltura() ) + 1);
        return nodo1;
    }

    private Nodo rotacionSimpleDerecha(Nodo nodo1)
    {
        Nodo nodo2 = nodo1.getDerecha();
        nodo1.setDerecha(nodo2.getIzquierda());
        nodo2.setIzquierda(nodo1);
        nodo1.setAltura(getAlturaMaxima( getAltura( nodo1.getIzquierda() ), getAltura( nodo1.getDerecha() ) ) + 1);
        nodo2.setAltura(getAlturaMaxima( getAltura( nodo2.getDerecha() ), nodo1.getAltura() ) + 1);
        return nodo2;
    }

    private Nodo rotacionDobleIzquierda(Nodo nodo3)
    {
        nodo3.setIzquierda(rotacionSimpleDerecha(nodo3.getIzquierda()));
        return rotacionSimpleIzquierda(nodo3);
    }

    private Nodo rotacionDobleDerecha(Nodo nodo1)
    {
        nodo1.setDerecha(rotacionSimpleIzquierda(nodo1.getDerecha()));
        return rotacionSimpleDerecha(nodo1);
    }

    public int getNumeroTotalNodos()
    {
        return getNumeroTotalNodos(raiz);
    }

    private int getNumeroTotalNodos(Nodo cabeza)
    {
        if (cabeza == null)
            return 0;
        else
        {
            int length = 1;
            length = length + getNumeroTotalNodos(cabeza.getIzquierda());
            length = length + getNumeroTotalNodos(cabeza.getDerecha());
            return length;
        }
    }

    public boolean buscarElemento(int element)
    {
        return buscarElemento(raiz, element);
    }

    private boolean buscarElemento(Nodo cabeza, int element)
    {
        boolean check = false;
        while ((cabeza != null) && !check)
        {
            int headElement = cabeza.getElemento();
            if (element < headElement)
                cabeza = cabeza.getIzquierda();
            else if (element > headElement)
                cabeza = cabeza.getDerecha();
            else
            {
                check = true;
                break;
            }
            check = buscarElemento(cabeza, element);
        }
        return check;
    }

    public String inOrder()
    {
       return inOrder(raiz);
    }
    private String inOrder(Nodo cabeza)
    {
        if (cabeza != null)
        {
            inOrder(cabeza.getIzquierda());
            resultado += cabeza.getElemento() + ",";
            inOrder(cabeza.getDerecha());
        }
        return resultado;
    }

    public String preOrder()
    {
        return preOrder(raiz);
    }
    private String preOrder(Nodo cabeza)
    {
        if (cabeza != null)
        {
            resultado += cabeza.getElemento() +",";
            preOrder(cabeza.getIzquierda());
            preOrder(cabeza.getDerecha());
        }
        return resultado;
    }

    public String postOrden()
    {

        return postOrden(raiz);
    }

    private String postOrden(Nodo cabeza) {

        if (cabeza != null)
        {
            postOrden(cabeza.getIzquierda());
            postOrden(cabeza.getDerecha());
            resultado += cabeza.getElemento() +",";
        }
        return resultado;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
