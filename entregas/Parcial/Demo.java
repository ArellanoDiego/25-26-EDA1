public class Demo {
    public static void main(String[] args) {
        ArraySimulado arr = new ArraySimulado(5);
        for (int i = 0; i < arr.length(); i++) arr.set(i, i * 10);
        System.out.print("ArraySimulado: ");
        for (int i = 0; i < arr.length(); i++) System.out.print(arr.get(i) + " ");
        System.out.println();

        ListaUsandoArraySimulado lista = new ListaUsandoArraySimulado();
        lista.add(1);
        lista.add(3);
        lista.add(5);
        lista.add(1, 2); 
        lista.add(3, 4);
        System.out.print("Lista (tras inserciones): ");
        for (int i = 0; i < lista.size(); i++) System.out.print(lista.get(i) + " ");
        System.out.println();

        lista.remove(0); 
        lista.remove(2); 
        System.out.print("Lista (tras eliminaciones): ");
        for (int i = 0; i < lista.size(); i++) System.out.print(lista.get(i) + " ");
        System.out.println();

        lista.set(1, 30); 
        System.out.print("Lista (tras set): ");
        for (int i = 0; i < lista.size(); i++) System.out.print(lista.get(i) + " ");
        System.out.println();
    }
}
