package ui;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import structures.PilaGenerica;
import structures.TablasHash;

public class Main {

    private Scanner sc;

    public Main() {
        sc = new Scanner(System.in);
    }

    public void ejecutar() {
        while (true) {
            System.out.println("\nSeleccione la opcion:");
            System.out.println("1. Punto 1, Verificar balanceo de expresión");
            System.out.println("2. Punto 2, Encontrar pares con suma objetivo");
            System.out.println("3. Salir del programa");
            System.out.print("Opcion: ");

            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese expresion a verificar:");
                    String expresion = sc.nextLine();
                    boolean resultado = verificarBalanceo(expresion);
                    System.out.println("Resultado: " + (resultado ? "TRUE" : "FALSE"));
                    break;

                case 2:
                    System.out.println("Ingrese numeros separados por espacio: ");
                    String lineaNumeros = sc.nextLine();
                    System.out.println("Ingrese suma objetivo: ");
                    int objetivo = Integer.parseInt(sc.nextLine());

                    String[] partes = lineaNumeros.trim().split("\\s+");
                    int[] numeros = new int[partes.length];
                    for (int i = 0; i < partes.length; i++) {
                        numeros[i] = Integer.parseInt(partes[i]);
                    }

                    encontrarParesConSuma(numeros, objetivo);
                    break;

                case 3:
                    System.out.println("Chao");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opcion no permitida");
            }
        }
    }

    /**
     * Verifica si la expresion esta balanceada usando PilaGenerica
     * @param s expresion a verificar
     * @return true si esta balanceada, false si no
     */
    public boolean verificarBalanceo(String s) {
        PilaGenerica<Character> pila = new PilaGenerica<>(s.length());

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                pila.Push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                if (pila.isEmpty()) return false;
                Character tope = pila.Pop();
                if (tope == null || !coinciden(tope, c)) return false;
            }
        }
        return pila.isEmpty();
    }

    private boolean coinciden(char apertura, char cierre) {
        return (apertura == '(' && cierre == ')') ||
                (apertura == '[' && cierre == ']') ||
                (apertura == '{' && cierre == '}');
    }

    /**
     * Encuentra y muestra todos los pares unicos de numeros que sumen objetivo usando TablasHash.
     * @param numeros arreglo de numeros enteros
     * @param objetivo suma objetivo
     */
    public void encontrarParesConSuma(int[] numeros, int objetivo) {
        TablasHash tabla = new TablasHash(numeros.length * 2);
        Set<String> pares = new HashSet<>();

        for (int num : numeros) {
            int complemento = objetivo - num;
            if (tabla.search(complemento, complemento)) {
                int menor = Math.min(num, complemento);
                int mayor = Math.max(num, complemento);
                pares.add("(" + menor + ", " + mayor + ")");
            }
            tabla.insert(num, num);
        }

        if (pares.isEmpty()) {
            System.out.println("No se encontraron pares.");
        } else {
            System.out.println("Pares encontrados: " + pares);
        }
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.ejecutar();
    }
}
