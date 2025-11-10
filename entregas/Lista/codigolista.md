public class ArrayEnteros {
        private int[] datos;
        private int usados;

        public ArrayEnteros(int capacidad) {
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

        public void agregarAlFinal(int valor) {
            if (estaLleno()) throw new IllegalStateException("El array está lleno");
            datos[usados++] = valor;
        }

        public int obtener(int indice) {
            validar(indice);
            return datos[indice];
        }

        public void reemplazar(int indice, int valor) {
            validar(indice);
            datos[indice] = valor;
        }

        public void eliminarEn(int indice) {
            validar(indice);
            for (int i = indice; i < usados - 1; i++) {
                datos[i] = datos[i + 1];
            }
            usados--;
        }

        public int eliminarPrimero() {
            if (estaVacio()) throw new IllegalStateException("Array vacío");
            int valor = datos[0];
            for (int i = 0; i < usados - 1; i++) {
                datos[i] = datos[i + 1];
            }
            usados--;
            return valor;
        }

        public int[] obtenerDatos() {
            int[] copia = new int[usados];
            System.arraycopy(datos, 0, copia, 0, usados);
            return copia;
        }

        private void validar(int indice) {
            if (indice < 0 || indice >= usados) {
                throw new IndexOutOfBoundsException("Índice fuera de rango: " + indice);
            }
        }
    }

    class Bloques{
    private final ArrayEnteros arrayInterno;

    public Bloque(int capacidad) {
        this.arrayInterno = new ArrayEnteros(capacidad);
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

    public void agregarAlFinal(int valor) {
        arrayInterno.agregarAlFinal(valor);
    }

    public int obtener(int indice) {
        return arrayInterno.obtener(indice);
    }

    public void reemplazar(int indice, int valor) {
        arrayInterno.reemplazar(indice, valor);
    }

    public void eliminarEn(int indice) {
        arrayInterno.eliminarEn(indice);
    }

    public int eliminarPrimero() {
        return arrayInterno.eliminarPrimero();
    }

    public int[] obtenerDatos() {
        return arrayInterno.obtenerDatos();
    }
}


public class ListaPorBloquesEnteros {

    private Bloque[] bloques;
    private int numBloques;
    private int totalElementos;
    private final int capacidadBloque;

    public ListaPorBloquesEnteros(int capacidadBloque) {
        if (capacidadBloque <= 0)
            throw new IllegalArgumentException("La capacidad del bloque debe ser mayor que 0.");
        this.capacidadBloque = capacidadBloque;
        this.bloques = new Bloque[4];
        this.numBloques = 1;
        this.bloques[0] = new Bloque(capacidadBloque);
        this.totalElementos = 0;
    }

    public void agregarAlFinal(int elemento) {
        Bloque ultimo = bloques[numBloques - 1];
        if (ultimo.estaLleno()) {
            agregarNuevoBloque();
            ultimo = bloques[numBloques - 1];
        }
        ultimo.agregarAlFinal(elemento);
        totalElementos++;
    }

    public void eliminarEn(int posicion) {
        validarRango(posicion);
        Localizacion loc = localizar(posicion);
        bloques[loc.idxBloque].eliminarEn(loc.offset);


        for (int i = loc.idxBloque; i < numBloques - 1; i++) {
            Bloque actual = bloques[i];
            Bloque siguiente = bloques[i + 1];
            if (!siguiente.estaVacio()) {
                int movido = siguiente.eliminarPrimero();
                actual.agregarAlFinal(movido);
            }
        }


        Bloque ultimo = bloques[numBloques - 1];
        if (ultimo.estaVacio() && numBloques > 1) {
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
        numBloques = 1;
        bloques[0] = new Bloque(capacidadBloque);
        totalElementos = 0;
    }

    public int[] aArray() {
        int[] resultado = new int[totalElementos];
        int k = 0;
        for (int i = 0; i < numBloques; i++) {
            int[] datos = bloques[i].obtenerDatos();
            for (int valor : datos) {
                resultado[k++] = valor;
            }
        }
        return resultado;
    }

    public void mostrarEstructura() {
        System.out.println("Total = " + totalElementos + " | Bloques = " + numBloques);
        for (int i = 0; i < numBloques; i++) {
            int[] datos = bloques[i].obtenerDatos();
            System.out.print("Bloque " + i + " (" + datos.length + "/" + capacidadBloque + "): [");
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
            if (pos < acumulado + tam)
                return new Localizacion(i, pos - acumulado);
            acumulado += tam;
        }
        throw new IllegalStateException("No se pudo localizar la posición " + pos);
    }

    private static final class Localizacion {
        final int idxBloque;
        final int offset;
        Localizacion(int idxBloque, int offset) {
            this.idxBloque = idxBloque;
            this.offset = offset;
        }
    }
}