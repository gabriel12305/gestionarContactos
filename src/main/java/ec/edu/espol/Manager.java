package ec.edu.espol;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Manager implements Serializable{
    private static final long serialVersionUID = 0073L;
    Map<String, String> data;
    public Manager(){
        data = new HashMap<>();
    }

    public boolean crearUsuario(String contra){
        if(contra != null && data.containsKey(contra) == false){
            data.put(contra, "src/main/java/ec/edu/espol/data/"+contra+"_archivo"); 
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(data.get(contra)))) {
                ContactManager nuevoUsuario = new ContactManager(data.get(contra)); // Inicializar el ContactManager
                oos.writeObject(nuevoUsuario); // Guardar en el archivo
                System.out.println("----------------------------------------------");
                System.out.println("|        ¡Usuario creado exitosamente!       |");
                System.out.println("|     Información guardada correctamente.    |");
                System.out.println("----------------------------------------------");
                System.out.println();
                System.out.println("Ahora puede iniciar sesión con sus credenciales.");
                System.out.println("==============================================");
                System.out.println();
            } catch (IOException e) {
                System.out.println("Error al crear el archivo del usuarpepeio: " + e.getMessage());
                return false;
            }
            return true;

        }else if (contra == null) {
            System.out.println("Error: Tienes que ingresar una contraseña");
            return false;
        }else{
            System.out.println("Error: Contraseña ya existente, ingresar otra");
            return false;
        }
    }

    public ContactManager accederUsuario(String contra){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(data.get(contra)))){
            ContactManager conManager = (ContactManager) ois.readObject();
            return conManager;
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch(IOException e){
            System.out.println(e.getMessage());
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public void inicializarCambios(String contra, ContactManager manager){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(data.get(contra)))) {
            // Inicializar el ContactManager
            oos.writeObject(manager); // Guardar en el archivo
            System.out.println("Datos guardados exitosamente: " + data.get(contra));
        } catch (IOException e) {
            System.out.println("Error al crear el archivo del usuarpepeio: " + e.getMessage());
        }
    }

    public boolean thisUserExits(String contra){
        return data.containsKey(contra);
    }

}
