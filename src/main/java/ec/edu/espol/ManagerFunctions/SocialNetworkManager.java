package ec.edu.espol.ManagerFunctions;

import java.util.Scanner;

import ec.edu.espol.Contact;
import ec.edu.espol.ContactManager;

public class SocialNetworkManager {
    public static void gestionarRSociales(Contact contacto, Scanner sc) {
        int opcion;
        do {
            System.out.println("----------------------------------------------");
            System.out.println("            GESTIÓN DE REDES SOCIALES         ");
            System.out.println("----------------------------------------------");
            System.out.println("Seleccione la opción que desea gestionar:");
            System.out.println();
            System.out.println("  1) Ver redes sociales");
            System.out.println("  2) Agregar red social");
            System.out.println("  3) Editar red social");
            System.out.println("  4) Eliminar red social");
            System.out.println("  5) Volver al menú principal");
            System.out.println("----------------------------------------------");
            System.out.print(">> Ingrese el número de la opción que desea: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    contacto.viewSocialNetworks();
                    break;
                case 2:
                    System.out.print("Ingrese la plataforma (ej. Facebook, Instagram, Tiktok):");
                    String plataforma = sc.nextLine();
                    System.out.print("Ingrese el nombre del usuario: ");
                    String usuario = sc.nextLine();
                    contacto.addSocialNetwork(ContactManager.firstInUpperCaseString(plataforma), usuario);
                    System.out.println("Red social agregada.");
                    break;
                case 3:
                    System.out.print("Ingrese la plataforma a editar: ");
                    String plataformaEditar = sc.nextLine();
                    System.out.print("Ingrese el nuevo nombre de usuario: ");
                    String nuevoUsuario = sc.nextLine();
                    contacto.editSocialNetwork(ContactManager.firstInUpperCaseString(plataformaEditar), nuevoUsuario.toLowerCase());
                    System.out.println("Red social actualizada.");
                    break;
                case 4:
                    System.out.print("Ingrese la plataforma a eliminar: ");
                    String plataformaEliminar = sc.nextLine();
                    contacto.removeSocialNetwork(ContactManager.firstInUpperCaseString(plataformaEliminar));
                    System.out.println("Red social eliminada.");
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
