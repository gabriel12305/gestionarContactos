package ec.edu.espol;

public class Company extends Contact {
    private static final long serialVersionUID = 0061L;
    public Company( String companyName, String number) {
        super(companyName, number);
    }

    @Override
    public String getContactType() {
        return "Empresa";
    }

    @Override
    public String toString() {
        return  "Nombre de la empresa: "+ super.getName() + super.toString();
    }
    
}

