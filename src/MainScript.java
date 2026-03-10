import java.util.Scanner;

// Clase Nodo: La base de la pila dinámica
class Nodo {
    String contenido;
    Nodo siguiente;

    public Nodo(String contenido, Nodo siguiente) {
        this.contenido = contenido;
        this.siguiente = siguiente;
    }
}

// Clase Pila: Implementación manual (sin arreglos)
class Pila {
    private Nodo cima = null;

    public void push(String v) {
        cima = new Nodo(v, cima);
    }

    public String pop() {
        if (isEmpty()) return null;
        String valor = cima.contenido;
        cima = cima.siguiente;
        return valor;
    }

    public String peek() {
        return (isEmpty()) ? null : cima.contenido;
    }

    public boolean isEmpty() {
        return cima == null;
    }
}
//main class
public class MainScript {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Pila undo = new Pila(); // Pila principal
        Pila redo = new Pila(); // Pila secundaria
        int opcion = 0;

        System.out.println("--- SIMULADOR DE EDITOR DE TEXTO ---");

        while (opcion != 5) {
            // Menú limpio en una sola línea
            System.out.print("\n[1]Escribir [2]Undo [3]Redo [4]Ver [5]Salir > ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (Exception e) { opcion = 0; }

            switch (opcion) {
                case 1:
                    System.out.print("Texto: ");
                    undo.push(sc.nextLine());
                    // Regla de oro: si escribes algo nuevo, vacías el Redo
                    while (!redo.isEmpty()) redo.pop();
                    break;

                case 2: // DESHACER
                    if (!undo.isEmpty()) {
                        redo.push(undo.pop());
                        System.out.println("-> Acción deshecha.");
                    } else {
                        System.out.println("(!) No hay nada para deshacer.");
                    }
                    break;

                case 3: // REHACER
                    if (!redo.isEmpty()) {
                        undo.push(redo.pop());
                        System.out.println("-> Acción rehecha.");
                    } else {
                        System.out.println("(!) No hay nada para rehacer.");
                    }
                    break;

                case 4:
                    System.out.println("\n--- TEXTO ACTUAL ---");
                    mostrarTexto(undo);
                    System.out.println("--------------------");
                    break;
            }
        }
        System.out.println("Saliendo del programa...");
    }

    // Método para imprimir sin destruir la pila original
    static void mostrarTexto(Pila p) {
        if (p.isEmpty()) {
            System.out.println("(Vacío)");
            return;
        }
        Pila auxiliar = new Pila();
        // Invertimos para leer desde la primera línea escrita
        while (!p.isEmpty()) auxiliar.push(p.pop());

        while (!auxiliar.isEmpty()) {
            String linea = auxiliar.pop();
            System.out.println(linea);
            p.push(linea); // Devolvemos a la original
        }
    }
}