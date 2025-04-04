import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class VectorHeapTest {
    // Pruebas para el método insertar
    /**
     * 
     */
    @Test
    public void testInsertarEnHeapVacio() {
        VectorHeap<Integer> heap = new VectorHeap<Integer>();
        heap.insertar(10);
        assertEquals(1, heap.getDatos().size());
        assertEquals(Integer.valueOf(10), heap.getDatos().get(0));
    }
    
    /**
     * 
     */
    @Test
    public void testInsertarVariosElementosOrdenados() {
        VectorHeap<Integer> heap = new VectorHeap<Integer>();
        heap.insertar(5);
        heap.insertar(10);
        heap.insertar(15);
        
        assertEquals(3, heap.getDatos().size());
        // En un heap máximo, el mayor valor debe estar en la raíz
        assertEquals(Integer.valueOf(15), heap.getDatos().get(0));
    }
    
    /**
     * 
     */
    @Test
    public void testInsertarVariosElementosDesordenados() {
        VectorHeap<Integer> heap = new VectorHeap<Integer>();
        heap.insertar(15);
        heap.insertar(5);
        heap.insertar(10);
        
        assertEquals(3, heap.getDatos().size());
        // En un heap máximo, el mayor valor debe estar en la raíz
        assertEquals(Integer.valueOf(15), heap.getDatos().get(0));
    }
    
    // Pruebas para el método eliminarMax
    /**
     * 
     */
    @Test
    public void testEliminarMaxEnHeapConUnElemento() {
        VectorHeap<Integer> heap = new VectorHeap<Integer>();
        heap.insertar(10);
        Integer max = heap.eliminarMax();
        
        assertEquals(Integer.valueOf(10), max);
        assertEquals(0, heap.getDatos().size());
    }
    
    /**
     * 
     */
    @Test
    public void testEliminarMaxEnHeapConVariosElementos() {
        VectorHeap<Integer> heap = new VectorHeap<Integer>();
        heap.insertar(10);
        heap.insertar(20);
        heap.insertar(15);
        heap.insertar(30);
        heap.insertar(5);
        
        Integer max = heap.eliminarMax();
        
        // El máximo debe ser 30
        assertEquals(Integer.valueOf(30), max);
        // El tamaño debe haberse reducido en 1
        assertEquals(4, heap.getDatos().size());

        max = heap.eliminarMax();

        // El nuevo máximo debe ser 20
        assertEquals(Integer.valueOf(20), max);
    }

    /**
     * 
     */
    @Test
    public void testEliminarMaxSecuencia() {
        VectorHeap<Integer> heap = new VectorHeap<Integer>();
        // Insertamos en orden aleatorio
        heap.insertar(15);
        heap.insertar(30);
        heap.insertar(10);
        heap.insertar(20);
        heap.insertar(5);
        
        // Los elementos deberían ser eliminados en orden descendente
        assertEquals(Integer.valueOf(30), heap.eliminarMax());
        assertEquals(Integer.valueOf(20), heap.eliminarMax());
        assertEquals(Integer.valueOf(15), heap.eliminarMax());
        assertEquals(Integer.valueOf(10), heap.eliminarMax());
        assertEquals(Integer.valueOf(5), heap.eliminarMax());
        
        // El heap debería estar vacío
        assertEquals(0, heap.getDatos().size());
    }
}
