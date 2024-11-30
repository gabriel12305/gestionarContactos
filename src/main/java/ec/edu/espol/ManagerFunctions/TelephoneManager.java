package ec.edu.espol.ManagerFunctions;

import java.util.Scanner;
import ec.edu.espol.Contact;
import ec.edu.espol.Telephone;

public class TelephoneManager {
    public static void gestionarTelefonos(Contact contacto, Scanner sc){
        int opcion;
        do {
                System.out.println("----------------------------------------------");
                System.out.println("         OPCIONES DE GESTIÓN DE TELÉFONOS     ");
                System.out.println("----------------------------------------------");
                System.out.println("Seleccione la opción que desea gestionar:");
                System.out.println();
                System.out.println("  1) Ver teléfonos");
                System.out.println("  2) Agregar teléfono");
                System.out.println("  3) Eliminar teléfono");
                System.out.println("  4) Volver al menú principal");
                System.out.println("----------------------------------------------");
                System.out.print(">> Ingrese el número de la opción que desea: ");
                opcion = sc.nextInt();
                sc.nextLine();
                switch (opcion) {
                    case 1:
                        int contar = 1;
                        for(Telephone t: contacto.getTelephones()){
                            System.out.println(contar+") Descripción: "+ t.getDescription()+" - Número: " + t.getNumber());
                            contar++;
                        }
                        break;
                    case 2:
                        System.out.print("Ingrese la descripción del número: ");
                        String descripcion = sc.nextLine();
                        System.out.print("Ingrese el número de teléfono: ");
                        String numero = sc.nextLine();
                        Telephone nuevo = new Telephone(numero, descripcion);
                        contacto.getTelephones().addLast(nuevo);
                        break;
                    case 3:
                        if(contacto.getTelephones().isEmpty() != true){
                            int count = 1;
                            int eleccion;
                            for(Telephone t: contacto.getTelephones()){
                                System.out.println(count+") Descripción: "+ t.getDescription()+", Número: " + t.getNumber());
                                count++;
                            }
                            do {
                                System.out.print("Ingrese número del teléfono que desea eliminar: ");
                                eleccion = sc.nextInt();
                                sc.nextLine();
                            } while (eleccion < 0 || eleccion> contacto.getTelephones().size());
                            contacto.getTelephones().remove(eleccion-1);
                            System.out.println("Número de teléfono eliminado con éxito.");
                        }else{
                            System.out.println("No hay teléfonos para eliminar.");
                        }
                        break;
                    case 4:
                        System.out.println("Volviendo al menú...");
                        break;
                    default:
                        System.out.println("Opción inválida, ingrese nuevamente.");
                        break;
                }
        } while (opcion != 4);
        System.out.println("Volviendo al menú de gestión de contacto...");
    }
}
