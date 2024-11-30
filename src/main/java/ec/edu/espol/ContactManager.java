package ec.edu.espol;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;
import ec.edu.espol.Functions.ContactComparators;
import ec.edu.espol.Functions.FilterContacts;
import ec.edu.espol.Implements.ArrayList;
import ec.edu.espol.Implements.CircleLinkedList;
import ec.edu.espol.Implements.DoublyCircularNode;
import ec.edu.espol.ManagerFunctions.EventManager;
import ec.edu.espol.ManagerFunctions.ManagerEmails;
import ec.edu.espol.ManagerFunctions.SocialNetworkManager;
import ec.edu.espol.ManagerFunctions.TelephoneManager;



public class ContactManager implements Serializable {
    private static final long serialVersionUID = 001L;
    CircleLinkedList<Contact> list;
    
    public ContactManager(String archivo){
        CircleLinkedList<Contact> tempList = traer(archivo);
        if (tempList != null) {
            list = tempList;
        } else {
            list = new CircleLinkedList<>();
        }
    }
    
    public void now(String archivo){
        System.out.println("==============================================");
        System.out.println("|            BIENVENIDO A SU CUENTA           |");
        System.out.println("==============================================");
        System.out.println();
        System.out.println("           !Identificación exitosa! "); 
        System.out.println("                     ...\nDatos de usuario cargados correctamente.");
        System.out.println("----------------------------------------------");
        iniciar(archivo);
    }

    public void iniciar(String archivo){
        Scanner sc = new Scanner(System.in);
        int eleccion = 0;
        do {
            try {
                System.out.println("\nMenú Principal:");
                System.out.println("1) Ver contactos");
                System.out.println("2) Navegar entre contactos");
                System.out.println("3) Añadir nuevo contacto");
                System.out.println("4) Gestionar contacto");
                System.out.println("5) Ordenar contactos");
                System.out.println("6) Filtrar contactos");
                System.out.println("7) Eliminar contacto");
                System.out.println("8) Salir");
                System.out.println("----------------------------------------------");
                System.out.print("Seleccione el número de la opción deseada: ");
                eleccion = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingresa un número.");
                sc.nextLine();
            }
        } while (eleccion != 1 && eleccion != 2 && eleccion != 3 && eleccion != 4 && eleccion != 5 && eleccion != 6 && eleccion != 7 && eleccion != 8);
        switch (eleccion) {
            case 1:
                verContactos();
                iniciar(archivo);
                break;
            case 2:
                NavegarEntreUsuario(sc);
                iniciar(archivo);
                break;
            case 3:
                crearContacto(sc);
                iniciar(archivo);
                break;
            case 4:
                gestionarContacto(sc);
                iniciar(archivo);
                break;
            case 5:
                ordenarBucle(sc);
                iniciar(archivo);
                break;
            case 6:
                filtrarContactos(sc);
                iniciar(archivo);
                break;
            case 7:
                removerContacto(sc);
                iniciar(archivo);
                break;
            case 8 :
                guardar(archivo);
                System.out.println("Gracias por preferirnos");
                break;
            default:
                break;
        }
        sc.close();
    }
 
    private void removerContacto(Scanner sc){
        verContactos();
        int indice;
        do {
            System.out.println("----------------------------------------------");
            System.out.println("               REMOVER CONTACTO            ");
            System.out.println("----------------------------------------------");
            System.out.println();
            System.out.println("Escriba el número del contacto que desea eliminar: ");
            indice = sc.nextInt();
            sc.nextLine();
        } while (indice<0 || indice> list.size()+1);
        Contact nuevo = list.get(indice -1);
        System.out.println(nuevo.getName());
        list.removeByElement(nuevo);
        System.out.println("Contacto eliminado con éxito. ");
    }
    
    private void crearContacto(Scanner sc){
        String result;
        do {
            System.out.println("----------------------------------------------");
            System.out.println("             AÑADIR NUEVO CONTACTO            ");
            System.out.println("----------------------------------------------");
            System.out.println();

            System.out.println("¿Qué tipo de contacto desea agregar?");
            System.out.println("> Persona");
            System.out.println("> Empresa");
            System.out.print(">> Ingrese su opción: ");
            result = sc.nextLine();
            if (!esTextoValido(result)) {
                System.out.println("Error: Solo debe ingresa: (Persona o Empresa). Intente de nuevo.");
            }
        } while (!esTextoValido(result));
        if (result.equalsIgnoreCase("Persona")) {
            String nombre;
            do{
                System.out.print("Ingrese el nombre del contacto (solo letras): ");
                nombre = sc.nextLine();
                if (!esTextoValido(nombre)) {
                    System.out.println("El nombre no puede contener números ni caracteres especiales. Intente de nuevo.");
                }
            }while(!esTextoValido(nombre));
            
            String apellido;
            do{
                System.out.print("Ingrese el apellido del contacto (solo letras): ");
                apellido = sc.nextLine();
                if (!esTextoValido(apellido)) {
                    System.out.println("El apellido no puede contener números ni caracteres especiales. Intente de nuevo.");
                }
            }while(!esTextoValido(apellido));

            String numero;
            do{
                System.out.print("Ingrese el número de teléfono (10 dígitos): ");
                numero = sc.nextLine();
                if (!esNumeroValido(numero)) {
                    System.out.println("El número debe contener exactamente 10 dígitos. Intente de nuevo.");
                }
            }while(!esNumeroValido(numero));
            Contact c1= new Person(firstInUpperCaseString(nombre), firstInUpperCaseString(apellido), numero);
            list.addLast(c1);
            System.out.println();
            System.out.println("----------------------------------------------");
            System.out.println("Contacto creado exitosamente:");
            System.out.println("  Tipo      : Persona");
            System.out.println(c1.toString());
            System.out.println("----------------------------------------------");
        } else if (result.equalsIgnoreCase("Empresa")) {
            String nombre;
            do{
                System.out.print("Ingrese el nombre del contacto (solo letras): ");
                nombre = sc.nextLine();
                if (!esTextoValido(nombre)) {
                    System.out.println("El nombre no puede contener números ni caracteres especiales. Intente de nuevo.");
                }
            }while(!esTextoValido(nombre));

            String numero;
            do{
                System.out.print("Ingrese el número de teléfono (10 dígitos): ");
                numero = sc.nextLine();
                if (!esNumeroValido(numero)) {
                    System.out.println("El número debe contener exactamente 10 dígitos. Intente de nuevo.");
                }
            }while(!esNumeroValido(numero));
            Contact c2= new Company(firstInUpperCaseString(nombre), numero);
            list.addLast(c2);
            System.out.println();
            System.out.println("----------------------------------------------");
            System.out.println("Contacto creado exitosamente:");
            System.out.println("  Tipo      : Empresa");
            System.out.println(c2.toString());
            System.out.println("----------------------------------------------");
        }else{
            System.out.println("No existe ese tipo de contacto.");
        }
    }
    
    private boolean esTextoValido(String texto) {
        for (char c : texto.toCharArray()) {
            if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
                return false; 
            }
        }
        return true; 
    }

    private boolean esNumeroValido(String numero) {
        
        for (char c : numero.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false; 
            }
        }
        if (numero.length() != 10) {
            return false; 
        }
        return true; 
    }

    private void verContactos(){
        System.out.println("----------------------------------------------");
        System.out.println("           LISTADO DE CONTACTOS              ");
        System.out.println("----------------------------------------------");
        for (int i = 1; i < list.size()+1; i++) {
            if (list.get(i-1) instanceof Person) {
                Person nuevo = (Person) list.get(i-1);
                System.out.println("  "+i+") "+nuevo.getName()+" "+nuevo.getLastName());
            }else if (list.get(i-1) instanceof Company){
                Company nuevo = (Company) list.get(i-1);
                System.out.println("  "+i+") "+nuevo.getName());
            }
        }
        System.out.println("----------------------------------------------");
    }

    private int verContactosRA(Contact c){
        System.out.println("----------------------------------------------");
        System.out.println("           LISTADO DE CONTACTOS              ");
        System.out.println("----------------------------------------------");
        int devolver= 0;
        int count = 0;
        for (int i = 1; i < list.size()+1; i++) {
            count++;
            if (list.get(i-1) instanceof Person) {
                Person nuevo = (Person) list.get(i-1);
                if (nuevo.equals(c)) {
                    devolver = i;
                    count--;
                }else{
                    System.out.println("  "+count+") "+nuevo.getName()+" "+nuevo.getLastName());
                }
            }else if (list.get(i-1) instanceof Company){
                Company nuevo = (Company) list.get(i-1);
                if (nuevo.equals(c)) {
                    devolver = i;
                    count--;
                }else{
                    System.out.println("  "+count+") "+nuevo.getName());
                }
            }
        }
        System.out.println("----------------------------------------------");
        return devolver;
    }

    private void guardar(String archivo){
       return;
    }

    @SuppressWarnings("unchecked")
    private CircleLinkedList<Contact> traer(String archivo){
        File file = new File(archivo);
        if (!file.exists()) {
            System.out.println("El archivo no existe: " + archivo);
            return null;
        }

        
        CircleLinkedList<Contact> nuevo = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))){
            nuevo = (CircleLinkedList<Contact>) ois.readObject();
        } catch (ClassNotFoundException e) {
            System.out.println("Clase no encontrada al deserializar: " + e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + archivo);
        } catch (IOException e) {
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
        return nuevo;
    }

    private void gestionarEtiqueta(Contact contact, Scanner sc){
        int opcion;
        do {
            System.out.println("----------------------------------------------");
            System.out.println("               GESTIÓN DE ETIQUETAS           ");
            System.out.println("----------------------------------------------");
        
            if (contact.getEtiqueta() != null) {
                System.out.println("Etiqueta actual del contacto: "+contact.getEtiqueta());
                System.out.println("----------------------------------------------");
            }
            System.out.println("Seleccione una opción para gestionar la etiqueta:");
            System.out.println();
            System.out.println("  1) Agregar o Cambiar etiqueta");
            System.out.println("  2) Eliminar etiqueta");
            System.out.println("  3) Volver al menú principal");
            System.out.println("==============================================");
            System.out.print(">> Ingrese el número de la opción que desea: ");
            opcion = sc.nextInt();
            sc.nextLine();
            
            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre de la etiqueta que le desea agregar: ");
                    String etiqueta = sc.nextLine();
                    contact.setEtiqueta(firstInUpperCaseString(etiqueta));
                    System.out.println("Etiqueta agregada con exito.");
                    break;
                case 2:
                    if (contact.getEtiqueta() == null) {
                        System.out.println("Este contacto no tiene ninguna etiqueta asignada.");
                    }else{
                        contact.setEtiqueta(null);
                        System.out.println("Etiqueta eliminada con éxito.");
                    }
                    break;
                case 3:
                    System.out.println("Volviendo al menú de gestión de contacto...");
                    break;
                default:
                    System.out.println("Eliga una opción válida.");
                    break;
            }
        } while (opcion != 3);
        
        
    }
    private void gestionarNombres(Contact contacto, Scanner sc){
        String nuevonombre;
        do {
            System.out.println("Editar nombre actual: " + contacto.getName());
            System.out.print("Ingrese el nuevo nombre: ");
            nuevonombre= sc.nextLine();
        } while (!esTextoValido(nuevonombre));
        contacto.setName(firstInUpperCaseString(nuevonombre));
        System.out.println("Nombre actualizado.");
    }

    private void gestionarApellidos(Contact contacto, Scanner sc){
        if(contacto instanceof Person){
            Person persona= (Person) contacto;
            String nuevoApellido;
            System.out.println("Editar apellido actual: " + persona.getLastName());
            do {
                System.out.print("Ingrese el nuevo apellido: ");
                nuevoApellido = sc.nextLine();
                if (! esTextoValido(nuevoApellido)) {
                    System.out.println("El apellido no puede contener números ni caracteres especiales. Intente de nuevo.");
                }
            } while (! esTextoValido(nuevoApellido));
            persona.setLastname(firstInUpperCaseString(nuevoApellido));
            System.out.println("Apellido actualizado.");
        }else{
            System.out.println("Esta opción es solo para contactos de tipo persona.");
        }
    }

   private void gestionarFechaNacimiento(Contact contacto, Scanner sc) {
        if (contacto instanceof Person) {
            Person persona = (Person) contacto;
            LocalDate nuevaFecha = null;
            LocalDate hoy = LocalDate.now();

            if (persona.getBirthDate() == null) {
                do {
                    try {
                        System.out.print("Ingrese su fecha de nacimiento (YYYY-MM-DD): ");
                        String fechaInput = sc.nextLine();
                        nuevaFecha = LocalDate.parse(fechaInput);
                        if (nuevaFecha.isAfter(hoy)) {
                            System.out.println("La fecha no puede ser posterior al día de hoy. Intente de nuevo.");
                            nuevaFecha = null; // Reinicia la variable para continuar en el bucle
                        } else {
                            persona.setBirthDate(nuevaFecha);
                        }
                    } catch (DateTimeParseException e) {
                        System.out.println("Formato de fecha incorrecto. Intente de nuevo.");
                    }
                } while (nuevaFecha == null);
            } else {
                do {
                    try {
                        System.out.println("Fecha de nacimiento actual: " + persona.getBirthDate());
                        System.out.print("Ingrese la nueva fecha de nacimiento (YYYY-MM-DD): ");
                        String fechaInput = sc.nextLine();
                        nuevaFecha = LocalDate.parse(fechaInput);
                        if (nuevaFecha.isAfter(hoy)) {
                            System.out.println("La fecha no puede ser posterior al día de hoy. Intente de nuevo.");
                            nuevaFecha = null; // Reinicia la variable para continuar en el bucle
                        } else {
                            persona.setBirthDate(nuevaFecha);
                        }
                    } catch (DateTimeParseException e) {
                        System.out.println("Formato de fecha incorrecto. Intente de nuevo.");
                    }
                } while (nuevaFecha == null);
            }
            System.out.println("Fecha de nacimiento actualizada.");
        } else {
            System.out.println("Esta opción es solo para contactos de tipo persona.");
        }
    }

    public static String firstInUpperCaseString(String texto){
        if (texto == null || texto.isEmpty()) {
            return texto;
        }
        return texto.substring(0, 1).toUpperCase() + texto.substring(1).toLowerCase();
    }

    private void gestionarFoto(Contact contacto, Scanner sc){
        if(contacto.getPhotos()==null){
            System.out.print("Ingrese la nueva foto (ruta/URL): ");
            String nuevafoto= sc.nextLine();
            contacto.setPhotos(nuevafoto);
        }else{
            System.out.println("Foto actual: "+ contacto.getPhotos());
            System.out.println("Ingrese la nueva foto (ruta/URL): ");
            String nuevafoto= sc.nextLine();
            contacto.setPhotos(nuevafoto);
            System.out.println("Foto actualiza con éxito.");
        }
    }

    private void gestionarDireccion(Contact contacto, Scanner sc){
        if(contacto.getDirection()==null){
            System.out.print("Ingrese la nueva dirección: ");
            String nuevaDireccion= sc.nextLine();
            contacto.setDirection(nuevaDireccion);
        }else{
            System.out.println("Dirección actual: "+ contacto.getDirection());
            System.out.println("Ingrese la nueva dirección: ");
            String nuevaDireccion= sc.nextLine();
            contacto.setDirection(nuevaDireccion);                        
        }
        System.out.println("Dirección de contacto actualizada.");
    }

    private void gestionarContacto(Scanner sc){
        verContactos();
        int i = 0;
        do{
            try {
                i= sc.nextInt();
                sc.nextLine();
                if(i<1 || i>list.size()) 
                System.out.println("Selección inválida, ingrese de nuevo.");
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un numero válido.");
                sc.nextLine();
            }
        }while(i<1 || i>list.size());

        Contact contacto = list.get(i - 1);
        int opcion = 0;
        do{
            try {
                System.out.println();
                System.out.println("----------------------------------------------");
                System.out.println("         OPCIONES DE GESTIÓN DEL CONTACTO     ");
                System.out.println("----------------------------------------------");
                System.out.println("1) Nombres");
                System.out.println("2) Apellidos (solo para Persona)");
                System.out.println("3) Fecha de nacimiento (solo para Persona)");
                System.out.println("4) Foto");
                System.out.println("5) Teléfonos");
                System.out.println("6) Dirección");
                System.out.println("7) Emails");
                System.out.println("8) Eventos");
                System.out.println("9) Contactos relacionados");
                System.out.println("10) Etiqueta");
                System.out.println("11) Redes sociales");
                System.out.println("12) Volver al menú principal");
                System.out.println("----------------------------------------------");
                System.out.print("Ingrese la opción que desea: ");
                opcion= sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingresar número.");
                sc.nextLine();
            }
            switch (opcion) {
                case 1:
                    gestionarNombres(contacto, sc);
                    break;
                case 2:
                    gestionarApellidos(contacto, sc);
                    break;
                case 3:
                    gestionarFechaNacimiento(contacto, sc);
                    break;
                case 4:
                    gestionarFoto(contacto, sc);
                    break;
                case 5:
                    TelephoneManager.gestionarTelefonos(contacto, sc);
                    break;
                case 6:
                    gestionarDireccion(contacto, sc);
                    break;
                case 7:
                    ManagerEmails.gestionarEmails(contacto, sc);
                    break;
                case 8:
                    EventManager.gestionarEventos(contacto, sc);
                    break;
                case 9:
                    gestionarCRelacionados(contacto, sc);
                    break;
                case 10:
                    gestionarEtiqueta(contacto, sc);
                    break;
                case 11:
                    SocialNetworkManager.gestionarRSociales(contacto, sc);
                    break;
                case 12:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }while(opcion!=12);
    }

    

    private void gestionarCRelacionados(Contact contacto, Scanner sc){
        int opcion =0;
        do{
            System.out.println("----------------------------------------------");
            System.out.println("         GESTIÓN DE CONTACTOS RELACIONADOS    ");
            System.out.println("----------------------------------------------");
            try {
                
                System.out.println("Opciones disponibles:");
                System.out.println("  1) Ver contactos relacionados");
                System.out.println("  2) Agregar un contacto relacionado");
                System.out.println("  3) Eliminar un contacto relacionado");
                System.out.println("  4) Volver al menú principal");
                System.out.println("----------------------------------------------");
                System.out.print(">> Ingrese el número de la opción que desea: ");
                opcion= sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese una opción válida.");
            }catch(Exception e){
                System.out.println("Error: Ingrese una opción válida.");
            }
            sc.nextLine();
            switch (opcion) {
                case 1:
                    contacto.viewAssociativeContacts();
                    break;
                case 2:
                    System.out.print("Ingrese la relación (ej. amigo, colega): ");
                    String relacion = sc.nextLine();
                    System.out.println("A continuación verás la lista de contactos: ");
                    int posicion = verContactosRA(contacto);
                    int elegido = 0;
                    try {
                        System.out.print("Elige el contacto con que deseas realacionar: ");
                        elegido= sc.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Ingrese un numero valido.");
                    }
                    sc.nextLine();
                    if (posicion > elegido) {
                        Contact contact= list.get(elegido-1);
                        contacto.addAssociativeContact(relacion, contact);
                    }else{
                        Contact contact= list.get(elegido);
                        contacto.addAssociativeContact(relacion, contact);
                    }
                    break;
                case 3:
                    System.out.print("Ingresa la relación del contacto que deseas eliminar: ");
                    String relacionEliminar = sc.nextLine();
                    contacto.removeAssociativeContact(relacionEliminar, sc);
                    break;
                case 4:
                    System.out.println("Volviendo al menú de gestión de contacto...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }while(opcion !=4);
    }

    private void ordenarBucle(Scanner sc){
        int eleccion = 0;
        System.out.println("----------------------------------------------");
        System.out.println("       OPCIONES PARA ORDENAR CONTACTOS     ");
        System.out.println("----------------------------------------------");
        do {
            do {
                System.out.println("  1) Fecha de cumpleaños");
                System.out.println("  2) Apellido y Nombre");
                System.out.println("  3) Tipo de contacto");
                System.out.println("  4) Volver al menú principal");
                System.out.println("----------------------------------------------");
                System.out.print(">> Ingrese el número de la opción que desea: ");
                eleccion = sc.nextInt();
            } while (eleccion != 1 && eleccion !=2 && eleccion !=3 && eleccion!=4);
            ordenar(eleccion);
            verContactos();
        } while (eleccion !=4);
        sc.nextLine();
    }

    private void ordenar(int eleccion){
        if (eleccion == 1) {
            CircleLinkedList<Person> personas = new CircleLinkedList<>();
            CircleLinkedList<Company> empresa = new CircleLinkedList<>();
            CircleLinkedList<Contact> union = new CircleLinkedList<>();
            for (Contact contact : list) {
                if(contact instanceof Person){
                    personas.addLast((Person)contact);
                }else if( contact instanceof Company){
                    empresa.addLast((Company)contact);
                }
            }
            ContactComparators.orderHappyBrithday(personas);
            for (Person contact : personas) {
                union.addLast((Contact)contact);
            }
            for(Company c: empresa){
                union.addLast((Contact)c);
            }
            list = union;
        }else if (eleccion == 2) {
            CircleLinkedList<Person> personas = new CircleLinkedList<>();
            CircleLinkedList<Company> empresa = new CircleLinkedList<>();
            CircleLinkedList<Contact> union = new CircleLinkedList<>();
            for (Contact contact : list) {
                if(contact instanceof Person){
                    personas.addLast((Person)contact);
                }else if( contact instanceof Company){
                    empresa.addLast((Company)contact);
                }
            }
            ContactComparators.orderLastNameName(personas);
            for (Person contact : personas) {
                union.addLast((Contact)contact);
            }
            for(Company c: empresa){
                union.addLast((Contact)c);
            }
            list = union;  
        }else if(eleccion == 3){
            ContactComparators.ordenTypeContact(list);
        }
    }

    private void filtrarContactos(Scanner sc){
        int eleccion=0;
        do {
            do{
                System.out.println("----------------------------------------------");
                System.out.println("       OPCIONES PARA FILTRAR CONTACTOS     ");
                System.out.println("----------------------------------------------");
                System.out.println("  1) Nombre");
                System.out.println("  2) Red Social");
                System.out.println("  3) Tipo de contacto");
                System.out.println("  4) Etiqueta");
                System.out.println("  5) Volver al menú principal");
                System.out.println("----------------------------------------------");
                System.out.print(">> Ingrese el número de la opción que desea: ");
                eleccion= sc.nextInt();
            }while(eleccion != 1 && eleccion !=2 && eleccion !=3 && eleccion !=4 && eleccion!=5);
            filtrar(eleccion, sc);
        }while (eleccion !=5);
    }

    private void filtrar(int eleccion, Scanner sc){
        sc.nextLine();
        if(eleccion==1){
            System.out.print("Ingrese el nombre por el cuál quiere filtrar: ");
            String nombre= sc.nextLine();
            CircleLinkedList<Contact> contactosFiltrados= FilterContacts.filterbyname(list,nombre);
            if( contactosFiltrados.isEmpty()){
                System.out.println("No hay contactos guardados con el nombre: '"+nombre+"'");
            }
            
            for(Contact c: contactosFiltrados){
                System.out.println("----------------------------------------------");
                System.out.println("                 CONTACTO                     ");
                System.out.println("----------------------------------------------");
                System.out.println(c.toString());
                System.out.println();
            }
        }else if( eleccion==2){
            String redSocial= sc.nextLine();
            CircleLinkedList<Contact> contactosFiltrados= FilterContacts.filterbySocialN(list, firstInUpperCaseString(redSocial));
                if( contactosFiltrados.isEmpty()){
                    System.out.println("No hay contactos guardados que utilicen la red social: '"+redSocial+"'");
                }
                for(Contact c: contactosFiltrados){
                    System.out.println("----------------------------------------------");
                    System.out.println("                 CONTACTO                     ");
                    System.out.println("----------------------------------------------");
                    System.out.println(c.toString());
                    System.out.println();
                }
        }else if(eleccion==3){
            String tipoC= sc.nextLine();
            if(tipoC.equalsIgnoreCase("Empresa")|| tipoC.equalsIgnoreCase("Persona")){
                CircleLinkedList<Contact> contactosFiltrados= FilterContacts.filterbytype(list, tipoC);
                if( contactosFiltrados.isEmpty()){
                    System.out.println("No hay contactos guardados del tipo: '"+tipoC+"'");
                }
                for(Contact c: contactosFiltrados){
                    System.out.println("----------------------------------------------");
                    System.out.println("                 CONTACTO                     ");
                    System.out.println("----------------------------------------------");
                    System.out.println(c.toString());
                    System.out.println();
                }
            }else{
                System.out.println("No existe este tipo de contacto.");
            }
        }else if (eleccion == 4) {
            System.out.print("Ingrese por cual etiqueta desea filtrar: ");
            String tipoE = sc.nextLine();
            ArrayList<Contact> contactosFiltrados = FilterContacts.filterByEtiqueta(list, tipoE);
            if (contactosFiltrados.isEmpty()) {
                System.out.println("No hay contactos guardados con el nombre: '"+tipoE+"'");
            }else{
                for(Contact c: contactosFiltrados){
                    System.out.println("----------------------------------------------");
                    System.out.println("                 CONTACTO                     ");
                    System.out.println("----------------------------------------------");
                    System.out.println(c.toString());
                    System.out.println();
                }
            }
        }
    }
    
    private DoublyCircularNode<Contact> mostrarContactoDetalles(int caso, DoublyCircularNode<Contact> node){
        switch (caso) {
            case 0:
                System.out.println(node.getContent().toString());
                return node;
            case 1:
                node = node.getNext();
                System.out.println(node.getContent().toString());
                return node;
            case -1:
                node = node.getPrevious();
                System.out.println(node.getContent().toString());
                return node;
            default:
                return null;
        }
    
    }

    private void NavegarEntreUsuario(Scanner sc){
        String msg;
        int casoContacto = 0;
        DoublyCircularNode<Contact> nodo = list.getHeader();
        if (nodo == null) {
            System.out.println("No tiene ningún contacto");
            return;
        }
        do {
            System.out.println("----------------------------------------------");
            System.out.println("                 CONTACTO                     ");
            System.out.println("----------------------------------------------");
            nodo = mostrarContactoDetalles(casoContacto, nodo);        
            do {
                System.out.println("Escriba:");
                System.out.println("  [Next] para avanzar al siguiente contacto");
                System.out.println("  [Prev] para retroceder al contacto anterior");
                System.out.println("  [Exit] para salir al menú principal");
                System.out.print(">> Ingrese su opción: ");
                msg = sc.nextLine(); 
            } while (!msg.equalsIgnoreCase("Next") && !msg.equalsIgnoreCase("Exit") && !msg.equalsIgnoreCase("Prev"));
            
            if(msg.equalsIgnoreCase("Next"))
                casoContacto = 1;
            if(msg.equalsIgnoreCase("Prev"))
                casoContacto = -1; 
        } while (!msg.equalsIgnoreCase("Exit"));
    }
}
