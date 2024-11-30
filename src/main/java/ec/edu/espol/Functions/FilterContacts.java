package ec.edu.espol.Functions;

import java.io.Serializable;

import ec.edu.espol.Contact;
import ec.edu.espol.Implements.ArrayList;
import ec.edu.espol.Implements.CircleLinkedList;

public class FilterContacts implements Serializable{
    private static final long serialVersionUID = 003L;
    //FILTRAR POR NOMBRE 
    public static CircleLinkedList<Contact> filterbyname(CircleLinkedList<Contact> contacts, String name){
        CircleLinkedList<Contact> contactsfilter= new CircleLinkedList<>();
        for(Contact c: contacts){
            if(name.equalsIgnoreCase(c.getName())){
                contactsfilter.addLast(c);
            }
        }
        return contactsfilter;
    }

    //FILTRAR POR PERSONAS QUE UTILICEN UNA RED SOCIAL 
    public static CircleLinkedList<Contact> filterbySocialN(CircleLinkedList<Contact> contacts, String social){
        CircleLinkedList<Contact> contactsfilter= new CircleLinkedList<>();
        for(Contact c: contacts){
            if(c.getSocialNetwork().searchUser(social)!=null){
                contactsfilter.addLast(c);
            }
        }
        return contactsfilter;
    }

    //FILTRAR POR TIPO
    public static CircleLinkedList<Contact> filterbytype(CircleLinkedList<Contact> contacts, String tipo){
        CircleLinkedList<Contact> contactsfilter= new CircleLinkedList<>();
        for(Contact c: contacts){
            if(tipo.equalsIgnoreCase(c.getContactType())){
                contactsfilter.addLast(c);
            }
        }
        return contactsfilter;
    }

    //FILTRAR POR ETIQUETA
    public static ArrayList<Contact> filterByEtiqueta(CircleLinkedList<Contact> contacts, String tipo){
        ArrayList<Contact> contactsFilter = new ArrayList<>(Contact.class);
        for(Contact c: contacts){
            if (tipo.equalsIgnoreCase(c.getEtiqueta())) {
                contactsFilter.addLast(c);
            }
        } 
        return contactsFilter;
    }
}
