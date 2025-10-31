package evaluaciones.retos.Reto004;

public class Array {

    private int[] datos;
    private int usados;

    public Array(int capacidad) {
        datos = new int[capacidad];
        usados = 0;
    }

    public boolean estaLleno() {
        return usados == datos.length;
    }

    public boolean estaVacio() {
        return usados == 0;
    }

    public int totalUsados() {
        return usados;
    }

    public void agregar(int valor) {
        if (estaLleno()) {
            throw new IllegalStateException("El array está lleno.");
        }
        datos[usados] = valor;
        usados++;
    }

    public void reemplazar(int indice, int valor) {
        validar(indice);
        datos[indice] = valor;
    }

    public int obtener(int indice) {
        validar(indice);
        return datos[indice];
    }

    public void eliminar(int indice) {
        validar(indice);
        for (int i = indice; i < usados - 1; i++) {
            datos[i] = datos[i + 1];
        }
        usados--;
    }

    public int[] obtenerDatos() {
        int[] copia = new int[usados];
        for (int i = 0; i < usados; i++) {
            copia[i] = datos[i];
        }
        return copia;
    }

    private void validar(int indice) {
        if (indice < 0 || indice >= usados) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + indice);
        }
    }
}
