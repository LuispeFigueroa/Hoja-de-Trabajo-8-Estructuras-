public interface PriorityQueue<E> {
    /**
     * @param elemento 
     * Inserta un nuevo elemento en la cola y reeordena la cola para mantener el
     * orednamiento por prioridad.
     */
    void insertar(E elemento);

    /**
     * @return
     * Elimina y devuelve el elemento de mayor prioridad.
     */
    E eliminarMax();

    /**
     * @return
     * Devuelve el elemento de mayor prioridad.
     */
    E devolverMax();
}