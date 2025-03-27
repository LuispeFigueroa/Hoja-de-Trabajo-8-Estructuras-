public class Paciente implements Comparable<Paciente>{

    private String nombre;
    private String lesion;
    private char prioridad; 

    //getters
    public String getNombre() {
        return nombre;
    }
    public String getLesion() {
        return lesion;
    }
    public char getPrioridad() {
        return prioridad;
    }



    public Paciente(String nombre, String lesion, char prioridad) {
        this.nombre = nombre;
        this.lesion = lesion;
        this.prioridad = prioridad;
    }
        // metodo para comparar al paciente con otro, definido como "Siguente"

    public int compareTo(Paciente siguiente){
        return Character.compare(this.prioridad, siguiente.prioridad);
    }
    //metodo par pasar la info del pacietne como String

    public String toString(){
        return nombre + "," + lesion + "," + prioridad;
    }
}
