import java.util.Scanner;

// Molde
class Pila {
    private String[] datos = new String[100];
    private int tope = -1;

    public void push(String v) { datos[++tope] = v; }
    public String pop() { return datos[tope--]; }
    public boolean isEmpty() { return tope == -1; }
}

// clase principal!
public class MainScript {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Dos pilas (objetos) de la misma clase (molde pila)
        Pila undo = new Pila();
        Pila redo = new Pila();
        int opt = 0;

        while (opt != 5) {
            // "Limpieza" visual de consola
            System.out.println("\n".repeat(2) + "=== EDITOR SIMPLE (Pilas) ===");
            System.out.println("[1] Escribir  |  [2] Deshacer (Undo)  |  [3]. Rehacer (Redo) |  [4]. Ver  |  [5]. Salir");
            System.out.print("Selección: ");
            opt = sc.nextInt();
            sc.nextLine();

            switch (opt) {
                case 1:
                    System.out.print("Escribe: ");
                    undo.push(sc.nextLine());
                    while (!redo.isEmpty()) redo.pop(); // Nueva acción limpia Redo
                    break;
                case 2:
                    if (!undo.isEmpty()) redo.push(undo.pop()); // De Undo a Redo
                    else System.out.println("¡Nada que deshacer!");
                    break;
                case 3:
                    if (!redo.isEmpty()) undo.push(redo.pop()); // De Redo a Undo
                    else System.out.println("¡Nada que rehacer!");
                    break;
                case 4:
                    System.out.println("\n--- TEXTO ACTUAL ---");
                    imprimirPila(undo);
                    System.out.println("--------------------");
                    System.out.println("Presiona Enter para continuar...");
                    sc.nextLine();
                    break;
            }
        }
    }

    // Metodo simple para ver el texto sin borrarlo
    static void imprimirPila(Pila p) {
        if (p.isEmpty()) { System.out.println("(Vacío)"); return; }
        Pila aux = new Pila();
        while (!p.isEmpty()) aux.push(p.pop());
        while (!aux.isEmpty()) {
            String s = aux.pop();
            System.out.println("> " + s);
            p.push(s);
        }
    }
}
