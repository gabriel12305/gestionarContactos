package ec.edu.espol;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        iniciar2();
        
    }

    public static void iniciar2(){
        Manager manager = cargarManager();
        if(manager == null){
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("manager.ser"))){
                oos.writeObject(new Manager());
            } catch (IOException e) {
                e.printStackTrace();
            }
        manager = cargarManager();    
        }
        System.out.println("==============================================");
        System.out.println("|                                            |");
        System.out.println("|        Bienvenido a User Administrator     |");
        System.out.println("|                                            |");
        System.out.println("==============================================");
        System.out.println();
        System.out.println("Seleccione una acción para continuar:");
        System.out.println("  -> Escriba 'Iniciar' para iniciar sesión");
        System.out.println("  -> Escriba 'Registro' para registrarse");
        System.out.println();
        Scanner sc = new Scanner(System.in);
        String acto;
        String contraseña = null;
        do {
            do {
                System.out.print("Ingrese su opción ('Iniciar' o 'Registro'): ");
                acto = sc.nextLine();
            } while (!acto.equalsIgnoreCase("iniciar") && !acto.equalsIgnoreCase("registro"));
            if (acto.equalsIgnoreCase("iniciar")) {
                System.out.print("Identifíquese -> Escriba su contraseña: ");
                contraseña = sc.nextLine();
                if (!manager.thisUserExits(contraseña)) {
                    System.out.println("Error: Esa contraseña no existe.\n");
                }
            }
        } while (!manager.thisUserExits(contraseña) && !acto.equalsIgnoreCase("registro"));
        
        

        if(acto.equalsIgnoreCase("iniciar")){
            ContactManager cm = manager.accederUsuario(contraseña);
            cm.now(contraseña);
            manager.inicializarCambios(contraseña, cm);
            ReescribirManager(manager);
        }else{ 
            System.out.println("\n==============================================");
            System.out.println("|           REGISTRO DE NUEVO USUARIO         |");
            System.out.println("==============================================");
            System.out.println();
            System.out.print("Cree una contraseña segura: ");
            contraseña = sc.nextLine();
            manager.crearUsuario(contraseña);
            ReescribirManager(manager);
            iniciar2();
        }
        sc.close();
    }

    public static Manager cargarManager() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("manager.ser"))) {
            return (Manager) ois.readObject();
        } catch (Exception e) {
        }
        return null;
    }
    
    public static void ReescribirManager(Manager manager){
        try (FileOutputStream fos = new FileOutputStream("manager.ser");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(manager);
        } catch (IOException e) {
            System.err.println("Error al serializar: " + e.getMessage());
        }
    
    
    
    }
}