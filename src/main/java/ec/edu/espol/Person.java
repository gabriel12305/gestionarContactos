package ec.edu.espol;
import java.time.LocalDate;

public class Person extends Contact {
    private static final long serialVersionUID = 0063L;
    private String lastname;
    private LocalDate birthDate;

    public Person(String name, String lastname, String number) {
        super(name, number);
        this.lastname=lastname;
    }

    public void addBirthDate(LocalDate birthDate){
        this.birthDate=birthDate;
    }
    
    public String getLastName(){
        return lastname;
    }
    
    public LocalDate getBirthDate(){
        return birthDate;
    }

    public int calcularEdad(LocalDate birthDate) {
        return LocalDate.now().getYear() - birthDate.getYear();
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Nombre: "+ super.getName()+"\nApellido: " +lastname);
        if (birthDate != null) {
            sb.append("\nFecha de cumpleaños: "+ birthDate);
        }
        sb.append(super.toString());
        return sb.toString();
    }

    @Override
    public String getContactType() {
        return "Persona Natural";
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    
}
