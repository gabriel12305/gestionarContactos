package ec.edu.espol.Functions;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Comparator;

import ec.edu.espol.Contact;
import ec.edu.espol.Person;
import ec.edu.espol.Implements.CircleLinkedList;

public class ContactComparators implements Serializable {
    private static final long serialVersionUID = 002L;
    //ORDENAR POR APELLIDO Y NOMBRE
    public static void orderLastNameName(CircleLinkedList<Person> persons) {

        
        Comparator<Person> cmp1= new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                int lastNameComparison = p1.getLastName().compareToIgnoreCase(p2.getLastName());
                if (lastNameComparison != 0) {
                    return lastNameComparison;
                } else {
                    return p1.getName().compareToIgnoreCase(p2.getName());
                }
            }
        };
        persons.order(cmp1);
    }

    //ORDENAR FECHA DE CUMPLEAÑOS MAS CERCANA
    public static void orderHappyBrithday(CircleLinkedList<Person> persons){
        Comparator<Person> cmp2= new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                LocalDate today = LocalDate.now();
                
                if (o1.getBirthDate() == null && o2.getBirthDate() == null) {
                    return 0;
                }
                if (o1.getBirthDate() == null) {
                    return 1; 
                }
                if (o2.getBirthDate() == null) {
                    return -1; 
                }

                LocalDate nextBirthday1 = o1.getBirthDate().withYear(today.getYear());
                LocalDate nextBirthday2 = o2.getBirthDate().withYear(today.getYear());

                if (nextBirthday1.isBefore(today)) {
                    nextBirthday1 = nextBirthday1.plusYears(1);
                }
                if (nextBirthday2.isBefore(today)) {
                    nextBirthday2 = nextBirthday2.plusYears(1);
                }
                return nextBirthday1.compareTo(nextBirthday2);
            }
        };
        persons.order(cmp2);
    }

    //ORDENAR POR TIPO DE CONTACTO
    public static void ordenTypeContact(CircleLinkedList<Contact> contacts){
        Comparator<Contact> cmp3= new Comparator<Contact>(){
            @Override
            public int compare(Contact o1, Contact o2) {
                return o1.getContactType().compareTo(o2.getContactType());
            }

        };
        contacts.order(cmp3);
    }
}
