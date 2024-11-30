package ec.edu.espol;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Events implements Serializable{
    private static final long serialVersionUID = 0072L;
    private Map<String, LocalDate> events;
    
    public Events (){
        this.events= new HashMap<>();
    }

    public void addEvent(String description, LocalDate date) {
        if (description != null && date != null) {
            events.put(description, date);
        } else {
            System.out.println("Descripción y fecha no pueden ser nulos.");
        }
    }

    public void removeEvent(String description) {
        if (events.containsKey(description)) {
            events.remove(description);
        } else {
            System.out.println("No se encontró el evento: " + description);
        }
    }

    public void searchEvent(String description) {
        LocalDate date = events.get(description);
        if (date != null) {
            System.out.println("El evento '" + description + "' está previsto para el: " + date);
        } else {
            System.out.println("No se encontró el evento con descripción: " + description);
        }
    }

    public void viewUpcomingEvents() {
        if (events.isEmpty()) {
            System.out.println("No hay eventos planificados.");
        } else {
            LocalDate today = LocalDate.now();
            for (String description : events.keySet()) {
                LocalDate eventDate = events.get(description);
                if (eventDate.isAfter(today)) {
                    System.out.println(" - " + description + " - Se realizará: " + eventDate);
                }
            }
        }
    }

    public void viewPastEvents() {
        if (events.isEmpty()) {
            System.out.println("No hay eventos pasados.");
        } else {
            LocalDate today = LocalDate.now();
            for (String description : events.keySet()) {
                LocalDate eventDate = events.get(description);
                if (eventDate.isBefore(today)) {
                    System.out.println(" - " + description + " - Fue realizado: " + eventDate);
                   
                }
            }
        }
    }
    
    @Override
    public String toString(){
        return events.isEmpty() ? "{}" : events.toString();
    }
}
