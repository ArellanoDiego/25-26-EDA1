package evaluaciones.retos.Reto004;

public class ListaPorBloquesEnteros {

    private Bloque[] bloques;
    private int numBloques;
    private final int capacidadBloque;
    private int totalElementos;

    public ListaPorBloquesEnteros(int capacidadBloque) {
        if (capacidadBloque <= 0)
            throw new IllegalArgumentException("La capacidad debe ser mayor que 0.");

        this.capacidadBloque = capacidadBloque;
        this.bloques = new Bloque[4];       
        this.bloques[0] = new Bloque(capacidadBloque);
        this.numBloques = 1;
        this.totalElementos = 0;
    }

    public void agregarAlFinal(int elemento) {
        Bloque ultimo = bloques[numBloques - 1];
        if (ultimo.estaLleno()) {
            agregarNuevoBloque();
            ultimo = bloques[numBloques - 1];
        }
        ultimo.agregar(elemento);
        totalElementos++;
    }

    public void eliminarEn(int posicion) {
        validarRango(posicion);
        Localizacion loc = localizar(posicion);
        bloques[loc.idxBloque].eliminar(loc.offset);

        for (int i = loc.idxBloque; i < numBloques - 1; i++) {
            Bloque actual = bloques[i];
            Bloque siguiente = bloques[i + 1];
            if (!siguiente.estaVacio()) {
                int valor = siguiente.obtener(0);
                siguiente.eliminar(0);
                actual.agregar(valor);
            }
        }

        if (bloques[numBloques - 1].estaVacio() && numBloques > 1) {
            bloques[--numBloques] = null;
        }

        totalElementos--;
    }

    public int obtener(int posicion) {
        validarRango(posicion);
        Localizacion loc = localizar(posicion);
        return bloques[loc.idxBloque].obtener(loc.offset);
    }

    public void reemplazar(int posicion, int elemento) {
        validarRango(posicion);
        Localizacion loc = localizar(posicion);
        bloques[loc.idxBloque].reemplazar(loc.offset, elemento);
    }

    public int totalElementos() {
        return totalElementos;
    }

    public boolean estaVacia() {
        return totalElementos == 0;
    }

    public void limpiar() {
        bloques = new Bloque[4];
        bloques[0] = new Bloque(capacidadBloque);
        numBloques = 1;
        totalElementos = 0;
    }

    public int[] aArray() {
        int[] resultado = new int[totalElementos];
        int pos = 0;
        for (int i = 0; i < numBloques; i++) {
            int[] datos = bloques[i].obtenerDatos();
            for (int valor : datos) resultado[pos++] = valor;
        }
        return resultado;
    }

    public void mostrarEstructura() {
        System.out.println("Total elementos: " + totalElementos + " | Bloques usados: " + numBloques);
        for (int i = 0; i < numBloques; i++) {
            int[] datos = bloques[i].obtenerDatos();
            System.out.print("Bloque " + i + ": [");
            for (int j = 0; j < datos.length; j++) {
                System.out.print(datos[j]);
                if (j < datos.length - 1) System.out.print(", ");
            }
            System.out.println("]");
        }
    }


    private void agregarNuevoBloque() {
        if (numBloques == bloques.length) {
            Bloque[] nuevo = new Bloque[bloques.length * 2];
            System.arraycopy(bloques, 0, nuevo, 0, bloques.length);
            bloques = nuevo;
        }
        bloques[numBloques++] = new Bloque(capacidadBloque);
    }

    private void validarRango(int posicion) {
        if (posicion < 0 || posicion >= totalElementos) {
            throw new IndexOutOfBoundsException("Posición fuera de rango: " + posicion);
        }
    }

    private Localizacion localizar(int pos) {
        int acumulado = 0;
        for (int i = 0; i < numBloques; i++) {
            int tam = bloques[i].usados();
            if (pos < acumulado + tam) {
                return new Localizacion(i, pos - acumulado);
            }
            acumulado += tam;
        }
        throw new IllegalStateException("No se encontró la posición: " + pos);
    }

    private static class Localizacion {
        int idxBloque;
        int offset;
        Localizacion(int idxBloque, int offset) {
            this.idxBloque = idxBloque;
            this.offset = offset;
        }
    }
}
