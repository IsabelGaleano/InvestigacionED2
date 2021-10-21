package bl.logic;

import bl.entities.ArbolB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Controller {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static PrintStream out = System.out;

    ArbolB arbolB = new ArbolB();

    public Controller() {
    }

    public void start() throws IOException {
        boolean salir = false;
        do {
            imprimirMenu();
            int opcion = leerOpcion();
            salir = ejecutarOpcion(opcion);

        } while (!salir);


    }

    public void imprimirMenu() {
        System.out.println("======= MENU DEL PROGRAMA ======= ");
        System.out.println("");
        System.out.println("======= ARBOL B ======= ");
        System.out.println("1. Insertar");
        System.out.println("2. Buscar elemento");
        System.out.println("3. Imprimir");
        System.out.println("4. Eliminar");
        System.out.println("5. Salir");

    }

    public int leerOpcion() throws IOException {
        System.out.println("Ingrese la opcion que desea: ");
        return Integer.parseInt(in.readLine());
    }

    public boolean ejecutarOpcion(int opcion) throws IOException {
        boolean salir = false;
        int dato = 0;
        switch (opcion) {
            case 1:
                boolean out = false;
                do {
                    System.out.print("Digite el numero o ingrese (-1) para salir: ");
                    dato = Integer.parseInt(in.readLine());
                    if (dato == -1) {
                        out = true;
                    } else {
                        arbolB.insertar(dato);
                    }

                } while (!out);
                break;
            case 2:
                System.out.println("============ BUSCAR ELEMENTO ============");
                System.out.print("Digite el numero que desea buscar: ");
                dato = Integer.parseInt(in.readLine());
                if (arbolB.buscar(dato)) {
                    System.out.println("El elemento fue encontrado!!");
                }  else {
                    System.out.println("El elemento no existe!!");
                }

                break;

            case 3:
                System.out.println("============ IMPRIMIR ARBOL B ============");
                arbolB.imprimir();
                break;

            case 4:
                System.out.println("============ ELIMINAR ELEMENTO ============");
                System.out.print("Digite el numero que desea eliminar: ");
                dato = Integer.parseInt(in.readLine());
                if (arbolB.eliminar(dato)) {
                    System.out.println("El elemento fue eliminado!!");
                }  else {
                    System.out.println("El elemento no se pudo eliminar!!");
                }
                break;

            case 5:
                salir = true;
                break;

        }

        return salir;
    }
}
