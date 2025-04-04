public class Paciente implements Comparable<Paciente>{
    // Atributos
    private String nombre;
    private String lesion;
    private char prioridad; 

    // Métodos
    /**
     * @return
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * @return
     */
    public String getLesion() {
        return lesion;
    }
    /**
     * @return
     */
    public char getPrioridad() {
        return prioridad;
    }

    /**
     * @param nombre
     * @param lesion
     * @param prioridad
     */
    public Paciente(String nombre, String lesion, char prioridad) {
        this.nombre = nombre;
        this.lesion = lesion;
        this.prioridad = prioridad;
    }

    // Método para comparar al paciente con otro, definido como "Siguente"
    @Override
    public int compareTo(Paciente siguiente){
        return -Character.compare(this.prioridad, siguiente.prioridad);
    }

    // Método par pasar la info del paciente como String
    @Override
    public String toString(){
        return nombre + ", " + lesion + ", " + prioridad;
    }
}
