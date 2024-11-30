package ec.edu.espol;

import java.io.Serializable;

public class Telephone implements Serializable{
    private static final long serialVersionUID = 0075L;
    private String number;
    private String description;

    public Telephone(String number, String description) {
        this.number = number;
        this.description=description;
    }

    public String getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description + ":"+ number;
    }
}