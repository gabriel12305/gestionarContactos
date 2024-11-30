package ec.edu.espol.ManagerFunctions;

import java.time.LocalDate;
import java.util.Scanner;
import ec.edu.espol.Contact;

public class EventManager {
    public static void gestionarEventos(Contact contacto, Scanner sc) {
        int opcion;
        do {
            System.out.println("----------------------------------------------");
            System.out.println("               GESTIÓN DE EVENTOS      ");
            System.out.println("----------------------------------------------");
            System.out.println("Opciones disponibles:");
            System.out.println("  1) Ver eventos actuales");
            System.out.println("  2) Agregar ueventos");
            System.out.println("  3) Eliminar evento");
            System.out.println("  4) Historial de eventos");
            System.out.println("  5) Volver al menú principal");
            System.out.println("----------------------------------------------");
            System.out.print(">> Ingrese el número de la opción que desea: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                System.out.println();
                System.out.println("----------------------------------------------");
                System.out.println("           EVENTOS PRÓXIMOS REGISTRADOS       ");
                System.out.println("----------------------------------------------");
                    contacto.viewUpcomingEvents();
                    break;
                case 2:
                    System.out.print("Ingrese la descripción del evento: ");
                    String descripEvento = sc.nextLine();
                    LocalDate fecha = null;
                    do {
                        try {
                            System.out.print("Ingrese la fecha del evento (YYYY-MM-DD): ");
                            String fechaEvento = sc.nextLine();
                            fecha = LocalDate.parse(fechaEvento);
                        } catch (Exception e) {
                            System.out.println("Formato de fecha incorrecto. Debe ser YYYY-MM-DD. Intente de nuevo.");
                        }
                    } while (fecha == null);
                    contacto.addEvent(descripEvento, fecha);
                    System.out.println("Evento agregado.");
                    break;
                case 3:
                    System.out.println();
                    System.out.println("----------------------------------------------");
                    System.out.println("          EVENTOS PRÓXIMOS REGISTRADOS       ");
                    System.out.println("----------------------------------------------");
                    contacto.viewUpcomingEvents();
                    System.out.println();
                    System.out.println("----------------------------------------------");
                    System.out.println("          EVENTOS PASADOS REGISTRADOS         ");
                    System.out.println("----------------------------------------------");
                    contacto.viewPastEvents();
                    System.out.print("Ingrese la descripción del evento que desee eliminar: ");
                    String eventoEliminar = sc.nextLine();
                    contacto.removeEvent(eventoEliminar.toLowerCase());
                    System.out.println("Evento eliminado.");
                    break;
                case 4:
                    contacto.viewPastEvents();
                    break;
                case 5:
                    System.out.println("Volviendo al menú de gestión de contacto...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        } while (opcion != 5);
    }

}
