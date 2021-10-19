package bl.logic;

import bl.entities.ArbolB;

public class Main {

    public static void main(String[] args) {
        ArbolB arbolB = new ArbolB();

        arbolB.insertar(190);
        arbolB.insertar(57);
        arbolB.insertar(89);
        arbolB.insertar(90);
        arbolB.insertar(121);
        arbolB.insertar(170);
        arbolB.insertar(35);
        arbolB.insertar(48);
        arbolB.insertar(91);
        arbolB.insertar(22);
        arbolB.insertar(126);
        arbolB.insertar(132);
        arbolB.insertar(80);
        arbolB.imprimir();
        /*
        190,57,89,90,121,170,35,48,
91,22,126,132 y 80
         */
    }
}
