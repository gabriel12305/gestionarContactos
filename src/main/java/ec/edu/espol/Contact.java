package ec.edu.espol;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Scanner;

import ec.edu.espol.Implements.ArrayList;

public abstract class Contact implements Serializable{
    private static final long serialVersionUID = 0071L;
    private String name;
    private String description; 
    private String direction;
    private ArrayList<Telephone> telephones;
    private String photo;
    private ArrayList<String> emails;
    private AssociatedContacts associativeContacts;
    private Events events;
    private SocialNetwork socialNetwork;
    private String etiqueta;

    
    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public Contact(String name, String number) {
        this.name = name;
        this.telephones = new ArrayList<>(Telephone.class);
        telephones.addLast(new Telephone(number, "Principal"));
        this.emails = new ArrayList<>(String.class);
        this.associativeContacts = new AssociatedContacts();
        this.events = new Events(); 
        this.socialNetwork = new SocialNetwork();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

     public String getDirection() {
        return direction;
    }

    public ArrayList<Telephone> getTelephones() {
        return telephones;
    }

    public String getPhotos() {
        return photo;
    }

    public ArrayList<String> getEmails() {
        return emails;
    }

    public SocialNetwork getSocialNetwork() {
        return socialNetwork;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDirection(String direction){
        this.direction=direction;
    }

    public void setTelephones(ArrayList<Telephone> telephones) {
        this.telephones = telephones;
    }

    public void setPhotos(String photos) {
        this.photo = photos;
    }

    public void setEmails(ArrayList<String> emails) {
        this.emails = emails;
    }

    public abstract String getContactType(); 

    //Métodos para manipular los teléfonos.
    public void addTelephone(String number, String description) {
        telephones.addLast(new Telephone(number, description));
    }

    public void removeTelephone(String number, String description) {
        for(Telephone telephone: telephones){
            if(telephone.getNumber().equals(number) && telephone.getDescription().equals(description)){
                telephones.removeByElement(telephone);
            }
        }
    }

    public void removeNumber(String number){
        for(Telephone telephone: telephones){
            if(telephone.getNumber().equals(number)){
                telephones.removeByElement(telephone);
            }
        }
    }

    public void removeDescription(String description){
        for(Telephone telephone: telephones){
            if(telephone.getDescription().equals(description)){
                telephones.removeByElement(telephone);
            }
        }
    }
    
    public void viewTelephones() {
        if (telephones.isEmpty()) {
            System.out.println("No hay teléfonos registrados.");
        } else {
            System.out.println("Teléfonos:");
            for (Telephone tel : telephones) {
                System.out.println(tel);
            }
        }
    }

    //EMAILS
    public void addEmail(String email) {
        emails.addLast(email);
    }

    public void removeEmail(String email) {
        emails.removeByElement(email);
    }

    public void viewEmails() {
        if (emails.isEmpty()) {
            System.out.println("No hay emails registrados.");
        } else {
            System.out.println("Emails:");
            for (String email : emails) {
                System.out.println("-"+ email);
            }
        }
    }
    
    //EVENTOS
    public void addEvent(String description, LocalDate date) {
        events.addEvent(description, date);
    }

    public void removeEvent(String description) {
        events.removeEvent(description);
    }

    public void searchEvent(String description) {
        events.searchEvent(description);
    }

    public void viewUpcomingEvents(){
        events.viewUpcomingEvents();
    }

    public void viewPastEvents(){
        events.viewPastEvents();
    }

    //REDES SOCIALES
    public void addSocialNetwork(String platform, String user) {
        socialNetwork.addSocialNetwork(platform, user);
    }

    public void editSocialNetwork(String platform, String newUser) {
        socialNetwork.editUser(platform, newUser);
    }

    public void removeSocialNetwork(String platform) {
        socialNetwork.removeSocialNetwork(platform);
    }

    public void viewSocialNetworks() {
        socialNetwork.viewSocialNetwork();
    }

    //RELACIONES DE CONTACTOS
    public void addAssociativeContact(String relationship, Contact contact) {
        associativeContacts.addAC(relationship, contact);
    }

    public void editAssociativeContact(String relationship, Contact newContact) {
        associativeContacts.editAC(relationship, newContact);
    }

    public void removeAssociativeContact(String relationship, Scanner sc) {
        associativeContacts.removeAC(relationship, sc);
    }

    public void searchAssociativeContact(String relationship) {
        associativeContacts.searchAC(relationship);
    }

    public void viewAssociativeContacts() {
        associativeContacts.viewAllAC();
    }

    private void appendIfNotEmpty(StringBuffer sb, String n, ArrayList<?> value){
        if (value != null && !value.toString().isEmpty()) {
            if(value.size()!=0){
                sb.append("\n"+n+": "+ value);
            }else{
                sb.append("\n"+n+": Ninguno registrado");
            }
        }
    }

    private void appendIfNotNull(StringBuffer sb, String n, String value){
        if (value != null && !value.isEmpty()) {
            sb.append("\n"+n+": "+ value);
        }
    }

    
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        appendIfNotNull(sb, "Descripción", description);
        appendIfNotNull(sb, "Dirección", direction);
        appendIfNotEmpty(sb, "Teléfonos", telephones);
        appendIfNotNull(sb, "Etiqueta", etiqueta);
        appendIfNotEmpty(sb, "Emails", emails);
        //No hize un solo metodo para estos por que solo se me ocurria usando Object y el profe dijo que no utilizemos eso.
        //AssociativeContacts
        if (associativeContacts != null && !associativeContacts.toString().equals("{}")) {
            sb.append(", Relación de Contactos: ").append(associativeContacts.toString());
        }
        
        // Events
        if (events != null && !events.toString().equals("{}")) {
            sb.append(", Eventos: ").append(events.toString());
        }
        
        // SocialNetwork
        if (socialNetwork != null && !socialNetwork.toString().equals("{}")) {
            sb.append(", Redes Sociales: ").append(socialNetwork.toString());
        }
        
        return sb.toString();
    }
}
