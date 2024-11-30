package ec.edu.espol.ManagerFunctions;

import java.util.Scanner;
import ec.edu.espol.Contact;

public class ManagerEmails {
    public static void gestionarEmails(Contact contacto, Scanner sc) {
        int opcion;
        do {
            System.out.println("----------------------------------------------");
            System.out.println("               GESTIÓN DE EMAILS              ");
            System.out.println("----------------------------------------------");
            System.out.println("1) Ver emails registrados");
            System.out.println("2) Agregar un nuevo email");
            System.out.println("3) Eliminar un email existente");
            System.out.println("4) Volver al menú principal");
            System.out.println("----------------------------------------------");
            System.out.print(">> Ingrese el número de la opción que desea: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    contacto.viewEmails();
                    break;
                case 2:
                    System.out.print("Ingrese el nuevo email: ");
                    String nuevoEmail = sc.nextLine();
                    if(!contacto.getEmails().contains(nuevoEmail)){
                        contacto.addEmail(nuevoEmail);
                        System.out.println("Email agregado.\n");
                    }
                    break;
                case 3:
                    System.out.print("Ingrese el email a eliminar: ");
                    String emailEliminar = sc.nextLine();
                    contacto.removeEmail(emailEliminar);
                    System.out.println("Email eliminado.");
                    break;
                case 4:
                    System.out.println("Volviendo al menú de gestión de contacto...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        } while (opcion != 4);
    }
}
