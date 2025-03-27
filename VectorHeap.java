import java.util.Vector;

public class VectorHeap<E extends Comparable<E>> implements PriorityQueue<E> {
    public Vector<E> datos;

    /**
     * 
     */
    public VectorHeap() {
        datos = new Vector<>();
    }

    @Override
    public void insertar(E elemento) {
        datos.add(elemento);
        ordenarPorPadre(datos.size() - 1);
    }

    /**
     * @param indice
     */
    private void ordenarPorPadre(int indice) {
        int padre = (indice - 1) / 2;
        while (indice > 0) {
            if ((indice % 2) == 0) {
                padre = (indice - 2) / 2;
            }
            else {
                padre = (indice - 1) / 2;
            }
            if (datos.get(padre).compareTo(datos.get(indice)) > 0) {
                return;
            }

            intercambiar(indice, padre);
            indice = padre;
        }
    }

    /**
     * @param i
     * @param j
     */
    private void intercambiar(int i, int j) {
        E temporal = datos.get(i);
        datos.set(i, datos.get(j));
        datos.set(j, temporal);
    }

    public E eliminarMax() {
        if (datos.isEmpty()) {
            throw new IllegalStateException("No hay elementos en la cola.");
        }
        else {
            E max = datos.get(0);
            datos.set(0, datos.get(datos.size() - 1));
            datos.remove(datos.size() - 1);
            ordenarPorHijo(0);
            return max;
        }
    }

    private void ordenarPorHijo(int indice) {
        int hijoIzquierdo = (2 * indice) + 1;
        int hijoDerecho = (2 * indice) + 2;
        int maximo = indice;

        while (hijoIzquierdo < datos.size()) {
            if (datos.get(hijoIzquierdo).compareTo(datos.get(maximo)) > 0) {
                if (datos.get(hijoIzquierdo).compareTo(datos.get(hijoDerecho)) > 0) {
                    intercambiar(maximo, hijoIzquierdo);
                }
                else {
                    intercambiar(maximo, hijoDerecho);
                }
            }
            else if (datos.get(hijoDerecho).compareTo(datos.get(maximo)) > 0) {
                intercambiar(maximo, hijoDerecho);
            }
            else {
                return;
            }
        }
    }

    public E devolverMax() {
        if (datos.isEmpty()) {
            throw new IllegalStateException("No hay elementos en la cola.");
        }
        else {
            E max = datos.get(0);
            return max;
        }
    }
}
