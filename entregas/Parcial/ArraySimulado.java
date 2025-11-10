
public class ArraySimulado {
    private static final class Nodo {
        int dato;
        Nodo sig;
        Nodo(int d) { this.dato = d; }
    }

    private final int length;
    private final Nodo cabeza; 

    public ArraySimulado(int length) {
        if (length < 0) throw new IllegalArgumentException("length < 0");
        this.length = length;
        Nodo prev = null;
        Nodo first = null;
        for (int i = 0; i < length; i++) {
            Nodo n = new Nodo(0);
            if (first == null) first = n;
            if (prev != null) prev.sig = n;
            prev = n;
        }
        this.cabeza = first;
    }

    public int length() { return length; }

    private Nodo nodoEn(int i) {
        if (i < 0 || i >= length) throw new IndexOutOfBoundsException(i + " ∉ [0," + (length-1) + "]");
        Nodo cur = cabeza;
        for (int k = 0; k < i; k++) cur = cur.sig;
        return cur;
    }

    public int get(int index) { return nodoEn(index).dato; }

    public void set(int index, int value) { nodoEn(index).dato = value; }
}
