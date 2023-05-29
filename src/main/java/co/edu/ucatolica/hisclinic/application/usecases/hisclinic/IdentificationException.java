package co.edu.ucatolica.hisclinic.application.usecases.hisclinic;

public class IdentificationException extends RuntimeException {
    public IdentificationException(){
        super("El usuario ya esta registrado con la identificacion");
    }
    public IdentificationException(String message){
        super(message);
    }

}
