package co.edu.ucatolica.hisclinic.application.usecases.hisclinic;

public class IdentificationNotFoundException extends RuntimeException {
    public IdentificationNotFoundException(){
        super("Usuario no encontrado");
    }
    public IdentificationNotFoundException(String message){
        super(message);
    }

}