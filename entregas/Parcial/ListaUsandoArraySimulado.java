
public class ListaUsandoArraySimulado {
    private ArraySimulado datos;
    private int size; 

    public ListaUsandoArraySimulado() {
        this.datos = new ArraySimulado(10); 
        this.size = 0;
    }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    public int get(int index) {
        checkIndex(index);
        return datos.get(index);
    }

    public void set(int index, int value) {
        checkIndex(index);
        datos.set(index, value);
    }

    public void add(int value) { 
        ensureCapacity(size + 1);
        datos.set(size, value);
        size++;
    }

    public void add(int index, int value) { 
        if (index < 0 || index > size) throw new IndexOutOfBoundsException(index + " ∉ [0," + size + "]");
        ensureCapacity(size + 1);
        
        for (int i = size - 1; i >= index; i--) {
            int v = datos.get(i);
            datos.set(i + 1, v);
        }
        datos.set(index, value);
        size++;
    }

    public int remove(int index) {
        checkIndex(index);
        int old = datos.get(index);
        for (int i = index + 1; i < size; i++) {
            int v = datos.get(i);
            datos.set(i - 1, v);
        }
        size--;
        if (size < capacidad()) datos.set(size, 0);
        return old;
    }

    private void ensureCapacity(int minCap) {
        if (minCap <= capacidad()) return;
        int newCap = Math.max(minCap, (capacidad() * 3) / 2 + 1);
        ArraySimulado nuevo = new ArraySimulado(newCap);
        for (int i = 0; i < size; i++) {
            nuevo.set(i, datos.get(i));
        }
        datos = nuevo;
    }

    private int capacidad() { return datos.length(); }

    private void checkIndex(int i) {
        if (i < 0 || i >= size) throw new IndexOutOfBoundsException(i + " ∉ [0," + (size-1) + "]");
    }
}
