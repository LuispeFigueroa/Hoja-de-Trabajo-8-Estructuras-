import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
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
        catch (Exception e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        boolean seguir = true;
        while (seguir) {
            System.out.println("1. Mostrar siguiente en cola");
            System.out.println("2. Atender paciente");
            System.out.println("3. Salir");
            System.out.println("Eliga el número de la opción deseada: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    Paciente siguiente = cola.devolverMax();
                    if (siguiente!= null) {
                        System.out.println("Siguiente paciente: " + siguiente.toString());
                    }
                    else {
                        System.out.println("No hay pacientes en la cola");
                    }
                    break;
                case 2:
                    Paciente atendido = cola.eliminarMax();
                    if (atendido!= null) {
                        System.out.println("Paciente atendido: " + atendido.toString());
                    }
                    else {
                        System.out.println("No hay pacientes en la cola");
                    }
                    break;
                case 3:
                    seguir = false;
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        }
        scanner.close();
    }
}
