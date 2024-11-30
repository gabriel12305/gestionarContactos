package ec.edu.espol;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SocialNetwork implements Serializable{
    private static final long serialVersionUID = 0074L;
    Map<String, String> data;

    public SocialNetwork(){
        data = new HashMap<>();
    }
 
    public void addSocialNetwork(String socialNetwork, String user) {
        if (socialNetwork != null && user != null) {
            data.put(socialNetwork, user);
        } else {
            System.out.println("La red social y el usuario no pueden ser nulos.");
        }
    }
 
    public void editUser(String socialNetwork, String nuevoUser) {
        if (data.containsKey(socialNetwork)) {
            data.put(socialNetwork, nuevoUser);
        } else {
            System.out.println("La red social " + socialNetwork + " no existe.");
        }
    }
 
    public void removeSocialNetwork(String socialNetwork){
        data.remove(socialNetwork);
    }
 
    public String searchUser(String socialNetwork){
        String user = data.get(socialNetwork);
        if (user != null) {
            return user;
        } else {
            return null;
        }
    }
 
    public void viewSocialNetwork() {
        if (data.isEmpty()) {
            System.out.println("No hay redes sociales añadidas.");
        } else {
            System.out.println("Redes sociales y usuarios:");
            for (String key : data.keySet()) {
                System.out.println(" - "+ key + " - Usuario: " + data.get(key).toLowerCase());
            }
        }
    }

    @Override
    public String toString(){
        return data.isEmpty() ? "{}" : data.toString();
    }
}
