package evaluaciones.retos.Reto004;

public class Bloque {
    private Array arrayInterno;

    public Bloque(int capacidad) {
        this.arrayInterno = new Array(capacidad);
    }

    public boolean estaLleno() {
        return arrayInterno.estaLleno();
    }

    public boolean estaVacio() {
        return arrayInterno.estaVacio();
    }

    public int usados() {
        return arrayInterno.totalUsados();
    }

    public void agregar(int valor) {
        arrayInterno.agregar(valor);
    }

    public void eliminar(int indice) {
        arrayInterno.eliminar(indice);
    }

    public int obtener(int indice) {
        return arrayInterno.obtener(indice);
    }

    public void reemplazar(int indice, int valor) {
        arrayInterno.reemplazar(indice, valor);
    }

    public int[] obtenerDatos() {
        return arrayInterno.obtenerDatos();
    }
}
