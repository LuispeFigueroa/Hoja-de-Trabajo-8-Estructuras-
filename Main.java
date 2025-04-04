import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    /**
     * @param args
     */
    public static void main(String[] args) {
        PriorityQueue<Paciente> cola = new VectorHeap<>();
        Scanner scanner = new Scanner(System.in);

        try (BufferedReader br = new BufferedReader(new FileReader("pacientes.txt"))) {
            String linea;
            br.readLine();
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");

                String nombre = datos[0].trim();
                String lesion = datos[1].trim();
                char prioridad = datos[2].trim().charAt(0);

                Paciente paciente = new Paciente(nombre, lesion, prioridad);
                cola.insertar(paciente);
            }
        }
        catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        boolean seguir = true;
        while (seguir) {
            System.out.println("\nMENU");
            System.out.println("1. Mostrar siguiente en cola");
            System.out.println("2. Atender paciente");
            System.out.println("3. Salir");
            System.out.println("Eliga el número de la opción deseada: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    try {
                        Paciente siguiente = cola.devolverMax();
                        System.out.println("\nSiguiente paciente: " + siguiente.toString());
                    }
                    catch (Exception e) {
                        System.err.println("\nError al devolver al siguiente paciente: " + e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        Paciente atendido = cola.eliminarMax();
                        System.out.println("\nPaciente atendido: " + atendido.toString());
                    }    
                    catch (Exception e) {
                        System.err.println("\nError al atender al paciente con prioridad máxima: " + e.getMessage());
                    }
                    break;
                case 3:
                    seguir = false;
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        }
        scanner.close();
    }
}
