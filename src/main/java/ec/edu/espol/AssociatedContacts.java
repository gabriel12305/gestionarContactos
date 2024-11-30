package ec.edu.espol;

import java.io.Serializable;
import java.util.Map;
import java.util.Scanner;

import ec.edu.espol.Implements.CircleLinkedList;

import java.util.HashMap;
import java.util.InputMismatchException;


public class AssociatedContacts implements Serializable{
    private static final long serialVersionUID = 007L;
    Map<String, CircleLinkedList<Contact>> relation;

    public AssociatedContacts (){
        this.relation= new HashMap<>();
    }
    
    //Agregar Contactos Asociados
    public void addAC(String relationship, Contact c) {  
        //////////////////VALIDAR QUE STRING SEA TEXTO////////////////////////
        if (relation.containsKey(relationship) != false && relation.get(relationship).contains(c) != false) {
            System.out.println("Ya existe esa relacion con ese contacto");
            return;
        }
        if (relationship != null && c!= null) {
            if (relation.containsKey(relationship)) {
                relation.get(relationship).addLast(c);
                System.out.println("Contacto relacionado agregado con éxito.");
            }else{
                CircleLinkedList<Contact> list = new CircleLinkedList<>();
                list.addLast(c);
                relation.put(relationship, list);
                System.out.println("Contacto relacionado agregado con éxito.");
            }
        } else {
            System.out.println("Relación y contacto no pueden ser nulos."); 
        }
    }
    //Editar los contactos Asociados
    public void editAC(String relationship, Contact newC) {
        // Comprobar que el mapa contenga al key.
        if (relation.containsKey(relationship)) {
            addAC(relationship, newC);
        } else {
            System.out.println("No se encontró la relación: " + relationship);
        }
    }
    // Remover un contacto Asociado.
    public void removeAC(String relationship, Scanner sc) {
        // Comprobar que el mapa contenga al key.
        if (relation.containsKey(relationship)) {
            if(relation.get(relationship).size() == 1){
                relation.remove(relationship);
                System.out.println("Contacto relacionado eliminado.");
            }else{
                int opcion = 0;
                for (int i = 1; i <= relation.get(relationship).size(); i++) {
                    System.out.println(i+") "+ relation.get(relationship).get(i-1).getName());
                }
                do {
                    try {
                        System.out.print("Tienes mas de un contacto con esa relacion. Ingresa el numero de la opcion del cual deseas eliminar: ");
                        opcion = sc.nextInt(); 
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Tiene que ingresar un numero, no se aceptan letras.");
                        sc.nextLine();
                    }
                } while (opcion<0 || opcion > relation.get(relationship).size());
                relation.get(relationship).remove(opcion -1);
                System.out.println("Contacto relacionado eliminado.");
            }
        } else {
            System.out.println("No se encontró la relación: " + relationship);
        }
    }

    // Buscar un contacto Asociado.
    public void searchAC(String relationship) {
        // Buscar el value asociado a la key.
        for (Contact c : relation.get(relationship)) {
            if (c != null) {
                System.out.println("Es'" + relationship + "'de: " + c.getName());
            } else {
                System.out.println("No se encontró el nombre del contacto de la relación: " + relationship);
            }
        }
        
    }
    //Mostrar todos los contactos Asociados.
    public void viewAllAC() {
        if (relation.isEmpty()) { // En caso de un mapa vacio.
            System.out.println("No hay contactos relacionados.");
        } else {
            System.out.println("Los contactos relacionados son:");
            for (String relationship : relation.keySet()){ // Recorrer todos los valores key's del mapa.
                for (Contact c : relation.get(relationship)) {
                    System.out.println(" - " +relationship+" : " +c.getName());
                }
                
            }
        }
    }

    @Override
    public String toString(){

        StringBuffer sb = new StringBuffer();
        sb.append("{");
        if (relation.isEmpty()) {
            return "{}";
        }else{
            for(Map.Entry<String, CircleLinkedList<Contact>> entry : relation.entrySet()){
                for(Contact c: entry.getValue()){
                    sb.append(" "+entry.getKey()+"="+ c.getName()+",");
                }
            }
        }
        String result = sb.toString().substring(0, sb.toString().length() -1);
        result = result +" }";
        return result;
    }
}
