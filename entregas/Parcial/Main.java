public class Main {
    public static void main(String[] args) {
        System.out.println("=== Simulación: Lista sobre ArraySimulado ===");

        ListaUsandoArraySimulado lista = new ListaUsandoArraySimulado();

        lista.add(10);
        lista.add(20);
        lista.add(30);
        lista.add(40);
        lista.add(50);
        System.out.println("Lista inicial:");
        imprimir(lista);

        lista.add(2, 25);
        System.out.println("\nTras insertar 25 en la posición 2:");
        imprimir(lista);

        lista.remove(4);
        System.out.println("\nTras eliminar el elemento en posición 4:");
        imprimir(lista);

        lista.set(1, 200);
        System.out.println("\nTras modificar el elemento en posición 1:");
        imprimir(lista);

        System.out.println("\nAgregando más elementos para forzar crecimiento...");
        for (int i = 60; i <= 200; i += 20) {
            lista.add(i);
        }
        imprimir(lista);

        System.out.println("\n=== Fin de la simulación ===");
    }

    private static void imprimir(ListaUsandoArraySimulado lista) {
        System.out.print("[");
        for (int i = 0; i < lista.size(); i++) {
            System.out.print(lista.get(i));
            if (i < lista.size() - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}
